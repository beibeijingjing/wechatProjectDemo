package weixin.server.utils;


public class StringUtils {

	public static boolean isNotEmpty(Object str){
		if(str!=null){
			if(str instanceof String){
				String str1=(String)str;
				if(!str.equals("null")&&!str1.trim().isEmpty()){
					return true;
				}else{
					return false;
				}
			}else{
				return true;
			}
		}else{
			return false;
		}
		
	}
	
	public static boolean isEmpty(Object str){
		if(str==null){
			return true;
		}else if(str instanceof String){
			String str1=(String)str;
			if(str.equals("null")||str1.trim().isEmpty()){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		StringUtils stringUtils=new StringUtils();
		String sql=" ";
		//System.out.println(isEmpty(stringUtils));
		System.out.println(isNotEmpty(sql));
	}
}
