package core.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class DateUtils extends org.apache.commons.lang.time.DateUtils {
	private DateUtils() {
		super();
	}
	
	public static String getCurrentDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}

	public static String getCurrentDateTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
	
	/**
	 * 格式化
	 *  
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String parseDate(Date date, String parsePatterns) {
		SimpleDateFormat sdf = new SimpleDateFormat(parsePatterns);
		return sdf.format(date);
	}

	/**
	 * 默认格式化(yyyy-MM-dd HH:mm:ss)
	 * 
	 * @param date
	 * @return String
	 */
	public static String parseDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	public static String parseTimestamp(Timestamp date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//定义格式，不显示毫秒
		String str = df.format(date);
		return str;
	}
	public static String parseTimestampToFull(Timestamp date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
		String str = df.format(date);
		return str;
	}
	
	

	/**
	 * 默认格式化(yyyy-MM-dd HH:mm:ss)
	 * 
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String str, String parsePatterns) throws ParseException {
		return DateUtils.parseDate(str, new String[] { parsePatterns });
	}

	/**
	 * 默认格式化(yyyy-MM-dd HH:mm:ss)
	 * 
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String str) throws ParseException {
		return DateUtils.parseDate(str, new String[] { "yyyy-MM-dd HH:mm:ss" });
	}
	
	public static Timestamp parseDateToTimestamp(String str) throws ParseException {
		SimpleDateFormat formatTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat formatTimestamp1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatTimestamp1.parse(str);
        String str1 = formatTimestamp.format(date);
        Timestamp time = Timestamp.valueOf(str1); 
        return time;
	}
	
	public static Timestamp getCurrentTimestamp(){
		SimpleDateFormat formatTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str1 = formatTimestamp.format(new Date());
        Timestamp time = Timestamp.valueOf(str1); 
        return time;
	}
	
	public static String parseTimestampAfter5Days(Timestamp date) {
		Calendar today = Calendar.getInstance();
		today.setTime(date);
		today.add(Calendar.DATE, 5);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//定义格式，不显示毫秒
		Date tmp=today.getTime();
		String str = df.format(tmp);
		return str;
	}
	public static String parseTimestampAfterOrBeforeDays(Timestamp date, int day) {
		Calendar today = Calendar.getInstance();
		today.setTime(date);
		today.add(Calendar.DATE, day);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//定义格式，不显示毫秒
		Date tmp=today.getTime();
		String str = df.format(tmp);
		return str;
	}
	
	/**
	 * 获取一月的开始时刻
	 * 
	 * @return
	 * 	yyyy-MM-01 00:00:00
	 */
	public static String  getBeginOfMonth(Date date)
	{
		 
		Calendar today = Calendar.getInstance();
		today.setTime(date);
		Date dd= truncate(today, Calendar.MONTH).getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(dd);
	}
	/**
	 * 获取一月的结束时刻
	 * @return
	 * 	yyyy-MM-dd 23:59:59
	 * dd 取值为28,30,31
	 */
	public static String getEndOfMonth(Date date)
	{
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.setTime(date);
		tomorrow.add(Calendar.MONTH, 1);
		tomorrow = truncate(tomorrow, Calendar.MONTH);
		tomorrow.add(Calendar.SECOND, -1);
		Date dd= tomorrow.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(dd);
	}
	
	public static boolean ifToday(Timestamp time) {
		Date date=time;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if(df.format(new Date()).equals(df.format(date))){
			return true;
		}
		return false;
	}
	
	/**获取当前日期格式
	 * yyyyMMddHHmmss
	 * 
	 * @param date
	 * @return String
	 */
	public static String getCurrentFormatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}
	
	/**获取当前日期几个小时以后的日期格式
	 * yyyyMMddHHmmss
	 * 
	 * @param date
	 * @return String
	 */
	public static String getCurrentAfterHourDate(int hour) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		
		Calendar cd = Calendar.getInstance();
		cd.add(Calendar.HOUR, 1);
		String date  = sdf.format(cd.getTime());
		return date;
	}
	
	public static String getCommentTime(String time) throws ParseException{
		String commentTime=time.replace(".0", "");
		String result="";
		String now=DateUtils.getCurrentDateTime();
		Integer yearNow=Integer.parseInt(now.substring(0,4));
		Integer yearComment=Integer.parseInt(commentTime.substring(0, 4));
		
		long nowTime=new Date().getTime();
		long commentT=DateUtils.parseDate(commentTime).getTime();
		
		long hourCount=(nowTime-commentT)/(1000*60*60);
		long minuteCount=(nowTime-commentT)/(1000*60);
		
		//今日零点时刻
		String nowZeroStr=now.substring(0,11)+"00:00:00";
		double  nowZero=DateUtils.parseDate(nowZeroStr).getTime();
		double dayFlag=(nowZero-commentT)/(1000*60*60*24);
		if(yearComment<yearNow){
			result=commentTime.substring(0,16);
		}else if(dayFlag>1){
			result=commentTime.substring(5,16);
		}else if(dayFlag<=1&&dayFlag>0){
			result="昨天"+commentTime.substring(10,16);
		}else if(hourCount>=1){
			result=hourCount+"小时前";
		}else{
			if(minuteCount > 0){
				result=minuteCount+"分钟前";
			}else{
				result = "刚刚";
			}
		}
		
		//System.out.println(result);
		return result;
	}
	
	
	public static int getWhichWeekOfMonth(String str){
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setFirstDayOfWeek(RANGE_WEEK_MONDAY);
//		System.out.println(calendar.getActualMaximum(Calendar.WEEK_OF_MONTH));
		return calendar.get(Calendar.WEEK_OF_MONTH);
	}
	
	public static int getWhichWeekOfYear(String str){
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		try {
			if(!"".equals(str))
				date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setFirstDayOfWeek(RANGE_WEEK_MONDAY);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}
	public static int getWhichWeekOfYear(){
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setFirstDayOfWeek(RANGE_WEEK_MONDAY);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}
	
	public static int getWhichMonthOfYear(String str){
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}
	public static int getWhichMonthOfYear(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.get(Calendar.MONTH) + 1;
	}
	public static int getWhichDayOfYear(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	public static int getWhichWeekOfMonth(){
		int result=0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.setFirstDayOfWeek(RANGE_WEEK_MONDAY);
		result= calendar.get(Calendar.WEEK_OF_MONTH);
		return result;
	}
	public static String getYearAndMon(Integer year,Integer mon){
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy");
		SimpleDateFormat format= new SimpleDateFormat("MM");
		String result="";
		String y="";
		String m="";
		if(year != null && year != -1){
			y=year+"";
		}else{
			y=result=sdf.format(new Date());
		}
		
		if(mon != null && mon != -1){
			if(mon<10){
				m="0"+mon;
			}else{
				m=mon+"";
			}
			
		}else{
			m=format.format(new Date());
		}
		result=y+"-"+m;
		return result;
	}
	
	public static int getCountOfWeek(String year,String month){
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(RANGE_WEEK_MONDAY);
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy");
		if(!"".equals(year)&&null!=year&&!"-1".equals(year)){
			calendar.set(Calendar.YEAR, Integer.parseInt(year));
		}else{
			Integer nowYear=Integer.parseInt(sdf.format(new Date()));
			calendar.set(Calendar.YEAR, nowYear);
		}
		if(!"".equals(month)&&null!=month&&!"-1".equals(month)){
			calendar.set(Calendar.MONTH, Integer.parseInt(month));
		}else{
			calendar.set(Calendar.MONTH, Integer.parseInt(month));
		}
		return calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
	}
	
	/**
     * 获取当前日期是星期几<br>
     * 
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
	
	public static void main(String[] args) throws ParseException {
		
//		System.out.println("第"+DateUtils.getWhichWeekOfMonth("2016-01-18")+"周");
//		System.out.println("第"+DateUtils.getWhichWeekOfMonth()+"周");
		System.out.println(DateUtils.getWhichWeekOfYear("2016-01-08"));
		//System.out.println(DateUtils.getWhichWeekOfYear("2016-01-31"));
		//System.out.println(DateUtils.getYearAndMon(2015, 01));
		
		//System.out.println(DateUtils.getYearAndMon(-1,02));
//		System.out.println("====fdsf===="+getCurrentDate(new Date()));
//		System.out.println("=====ffff==="+getCurrentAfterHourDate(1));
//		@SuppressWarnings("unused")
//		Timestamp time=Timestamp.valueOf("2015-09-12 10:10:00");
//		System.out.println(parseDate("2015-09-12 10:10:00").compareTo(new Date()));

//		System.out.println( Timestamp.valueOf("2014-04-05 00:00:00"));
//		try {
//			System.out.println(parseDateToTimestamp("2013-4-5"));
//			//SimpleDateFormat formatTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	        // Date date = formatTimestamp.parse("2013-4-5");
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		Date date=new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String str2 = sdf.format(date);
//		Timestamp ts=Timestamp.valueOf(str2);
//		System.out.println(ts.getSeconds());
//		ts.setTime(ts.getTime()+1000);
//		System.out.println(ts.getSeconds());
	}

}
