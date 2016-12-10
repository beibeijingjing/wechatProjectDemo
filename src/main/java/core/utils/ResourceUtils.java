package core.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ResourceUtils extends Object {
	
	private static final Log log = LogFactory.getLog(ResourceUtils.class);
	
	private static final String CONFIG_FILE = "wx_config";
	
	private static ClassLoader defaultClassLoader;
	
	private static Charset charset;
	
	/**
	 * 私有构造子，防止被调用者new
	 */
	private ResourceUtils() {
		
	}
	
	public static ClassLoader getDefaultClassLoader() {
        return defaultClassLoader;
    }
	
	public static void setDefaultClassLoader(ClassLoader defaultClassLoader) {
		ResourceUtils.defaultClassLoader = defaultClassLoader;
    }
	
    public static Charset getCharset() {
        return charset;
    }
    
    public static void setCharset(Charset charset) {
    	ResourceUtils.charset = charset;
    }
	
    public static URL getResourceURL(String resource) throws IOException {
        return getResourceURL(getClassLoader(), resource);
    }
    
    public static URL getResourceURL(ClassLoader loader, String resource) throws IOException {
        URL url = null;
        if (loader != null) url = loader.getResource(resource);
        if (url == null) url = ClassLoader.getSystemResource(resource);
        if (url == null) throw new IOException("Could not find resource " + resource);
        return url;
    }
    
    public static InputStream getResourceAsStream(String resource) throws IOException {
        return getResourceAsStream(getClassLoader(), resource);
    }
    
    public static InputStream getResourceAsStream(ClassLoader loader, String resource) throws IOException {
        InputStream in = null;
        if (loader != null) in = loader.getResourceAsStream(resource);
        if (in == null) in = ClassLoader.getSystemResourceAsStream(resource);
        if (in == null) throw new IOException("Could not find resource " + resource);
        return in;
    }
    
    public static Properties getResourceAsProperties(String resource) throws IOException {
        Properties props = new Properties();
        InputStream in = null;
        String propfile = resource;
        in = getResourceAsStream(propfile);
        props.load(in);
        in.close();
        return props;
    }
    
    public static Properties getResourceAsProperties(ClassLoader loader, String resource) throws IOException {
        Properties props = new Properties();
        InputStream in = null;
        String propfile = resource;
        in = getResourceAsStream(loader, propfile);
        props.load(in);
        in.close();
        return props;
    }
    
    public static Reader getResourceAsReader(String resource) throws IOException {
        Reader reader;
        if (charset == null) {
            reader = new InputStreamReader(getResourceAsStream(resource));
        } else {
            reader = new InputStreamReader(getResourceAsStream(resource), charset);
        }
        return reader;
    }
    
    public static Reader getResourceAsReader(ClassLoader loader, String resource) throws IOException {
        Reader reader;
        if (charset == null) {
            reader = new InputStreamReader(getResourceAsStream(loader, resource));
        } else {
            reader = new InputStreamReader(getResourceAsStream(loader, resource), charset);
        }
        return reader;
    }
    
    public static File getResourceAsFile(String resource) throws IOException {
        return new File(getResourceURL(resource).getFile());
    }
    
    public static File getResourceAsFile(ClassLoader loader, String resource) throws IOException {
        return new File(getResourceURL(loader, resource).getFile());
    }
    
    public static InputStream getUrlAsStream(String urlString) throws IOException {
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();
        return conn.getInputStream();
    }
    
    public static Reader getUrlAsReader(String urlString) throws IOException {
        return new InputStreamReader(getUrlAsStream(urlString));
    }
    
    public static Properties getUrlAsProperties(String urlString) throws IOException {
        Properties props = new Properties();
        InputStream in = null;
        String propfile = urlString;
        in = getUrlAsStream(propfile);
        props.load(in);
        in.close();
        return props;
    }
    
    public static Class<?> classForName(String className) throws ClassNotFoundException {
        Class<?> clazz = null;
        try {
            clazz = getClassLoader().loadClass(className);
        } catch (Exception e) {
            // Ignore. Failsafe below.
        }
        if (clazz == null) {
            clazz = Class.forName(className);
        }
        return clazz;
    }
    
    public static Object instantiate(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
    	return instantiate(classForName(className));
    }
    
    public static Object instantiate(Class<?> clazz) throws InstantiationException, IllegalAccessException {
        return clazz.newInstance();
    }

    private static ClassLoader getClassLoader() {
        if (defaultClassLoader != null) {
            return defaultClassLoader;
        } else {
            return Thread.currentThread().getContextClassLoader();
        }
    }
    
	public static String getContentByInputStream(InputStream is, String charset) {	
		BufferedInputStream buffer = null;
		InputStreamReader reader = null;
//		BufferedReader br = null;
		try {
    		 buffer = new BufferedInputStream(is);
    		 reader = new InputStreamReader(buffer, charset);  
//    		 br = new BufferedReader(reader, 5 * 1024);  // 设置读取文件内容缓存为5KB
    		 StringBuffer content = new StringBuffer();  
    		 char[] ch = new char[8192];  // 缓存8KB
    		 int totalCount = 0;
    		 int readCount = 0;  
    		 while ((readCount = reader.read(ch)) != -1) {  
    			 totalCount += readCount;
    			 if (totalCount > 1000000) {
    				 log.debug("爬取到的目标数据字节数超过1MB阀值:" + totalCount);
    				 return null;
    			 }
    			 content.append(ch, 0, readCount); 
    	     }
    		 return content.toString();
        } catch (IOException e) {
			 e.printStackTrace();
        } catch (Exception e) {
			 e.printStackTrace();
        } 

		return null;
	}

    public static byte[] getBytesByInputStream(InputStream is) throws IOException {	
		byte[] content = null;
		byte[] temp = new byte[1024];
		int num = is.read(temp);
		while (num != -1) {
			int length = num;
			if (content != null) {
				byte[] contentTemp = content;
				length = num + contentTemp.length;
				content = new byte[length];
				System.arraycopy(temp, 0, content, contentTemp.length, num);
				System.arraycopy(contentTemp, 0, content, 0, contentTemp. length);
			} else {
				content = new byte[length];
				System.arraycopy(temp, 0, content, 0, num);
			}
			num = is.read(temp);
		}
    	return content;
    }
    
	/**
	 * @param requestName
	 * @return
	 */
	public static String getResource(String requestName) {
		return getResource(null, requestName);
	}
	
	private static final Map<String, Long> cacheMap = new ConcurrentHashMap<String, Long>(); 
	
	/**
	 * 从指定的资源文件中依据给定key获取value
	 * @param fileName
	 * @param requestName
	 * @return
	 */
	public static String getResource(String fileName, String requestName) {
		try {
			File file = null;
			if (null == fileName || "".equals(fileName)) {
				file = getResourceAsFile(CONFIG_FILE+".properties");
			} else {
				if (fileName.indexOf(".") > -1) {
					file = getResourceAsFile(fileName);
				} else {
					file = getResourceAsFile(fileName+".properties");
				}
			}
			String pathname = file.getAbsolutePath();
			if ( (null == cacheMap.get(pathname)) || (file.lastModified() != cacheMap.get(pathname).longValue()) ) {

				cacheMap.remove(pathname); // 清缓存过的编辑时间
				cacheMap.put(pathname, new Long(file.lastModified())); 
				
				ResourceBundle.clearCache(getClassLoader());;  // 清缓存过的资源
				
				if (null != fileName && !"".equals(fileName)) {
					if (fileName.indexOf(".") > -1) {
						fileName = fileName.substring(0, fileName.indexOf("."));
						return ResourceBundle.getBundle(fileName).getString(requestName);
					} else {
						return ResourceBundle.getBundle(fileName).getString(requestName);
					}
				} else {
					return ResourceBundle.getBundle(CONFIG_FILE).getString(requestName);	
				}
				
			} else {

				if (null != fileName && !"".equals(fileName)) {
					if (fileName.indexOf(".") > -1) {
						fileName = fileName.substring(0, fileName.indexOf("."));
						return ResourceBundle.getBundle(fileName).getString(requestName);
					} else {
						return ResourceBundle.getBundle(fileName).getString(requestName);
					}
				} else {
					return ResourceBundle.getBundle(CONFIG_FILE).getString(requestName);	
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 从fast-spider.properties资源文件中获取key-value
	 * @return props
	 * @throws IOException
	 */
    public static Properties getPropertiesValues() {
        try {
            Properties props = new Properties();
            for (Enumeration<URL> e = getClassLoader().getResources(CONFIG_FILE+".properties"); e.hasMoreElements();) {
                URL url = e.nextElement();
                Properties property = new Properties();
                InputStream is = url.openStream();
                property.load(is);
                props.putAll(property);
            } 
            return props;
        } catch (IOException e) {
        	e.printStackTrace();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return null;
    }
    
	/**
	 * 从指定的资源文件中获取禁词配置
	 * @return
	 */
	public static List<String> getKeywords(String fileName) {
		List<String> keywords = new ArrayList<String>();
		InputStream is = null;
        try {
				if (fileName.indexOf(".") > -1) {
					is = getResourceAsStream(fileName);
				} else {
					is = getResourceAsStream(fileName+".properties");
				}
	        	Properties prop = new Properties();
	        	prop.load(is);
	        	is.close();
	        	@SuppressWarnings("unchecked")
				Enumeration<String> e = (Enumeration<String>) prop.propertyNames();
	        	while (e.hasMoreElements()) {
	        		 String keyword = (String) e.nextElement();
	        		 keyword = new String(keyword.getBytes("ISO8859-1"), "UTF-8");
	              keywords.add(keyword);
	        	}
	        	return keywords;
        } catch (IOException e) {
        	e.printStackTrace();
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	if (null != is) {
        		try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	} 
        }
        
      return null;
	}
	
    /**
     * 创建文件（带文件名的路径、只有路径不带文件名）
     * @param fileName
     */
	public static void createFile(String fileName) {
		delDirAndFile(fileName);
		createDir(fileName);
		File file = new File(fileName);
		try {
			if (!file.isFile()) {
				file.createNewFile();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void createDir(String fileName) {
		File file = new File(fileName);
		if (!file.isFile()) {
			if (fileName.lastIndexOf(File.separator) > -1) {
				fileName = fileName.substring(0, fileName.lastIndexOf(File.separator));
			}
			file = new File(fileName);
			if (!file.exists() && !file.isDirectory()) {
				file.mkdirs();
			}
		}
	}

	public static void delDirAndFile(String fileName) {
		File file = new File(fileName);
		if (file.exists() && file.isDirectory()) {
			if (file.listFiles().length == 0) {
				file.delete();
			} else {
				File[] delFile = file.listFiles();
				for (int i = 0; i < delFile.length; i++) {
					if (delFile[i].isDirectory()) {
						delDirAndFile(delFile[i].getAbsolutePath());
					}
					delFile[i].delete();
				}
			}
			delDirAndFile(fileName);
		} else {
			if (file.isFile()) {
				file.delete();
			}
		}
	}
	
	/**
	 * 生成32位序列号
	 * @return
	 */
	public static String getSequence() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		int millisecond = calendar.get(Calendar.MILLISECOND);
		return "" + year + (month < 10 ? "0" + month : month)
				+ (day < 10 ? "0" + day : day)
				+ (hour < 10 ? "0" + hour : hour)
				+ (minute < 10 ? "0" + minute : minute)
				+ (second < 10 ? "0" + second : second)
				+ (millisecond < 10 ? "0" + millisecond : millisecond);
	}
	
	/**
	 * 获取服务器物理文件存储路径
	 * @param path
	 * @param sc
	 * @return
	 * @throws IOException
	 */
	public static String getPhysicalPath(String path, ServletContext sc) throws IOException {
		String sep = System.getProperty("file.separator");	
	    if (null == path || "".equals(path)) {
	    	throw new IllegalArgumentException("Path is not null");
	    }
	    
	    boolean flag = false;
	    String realPath = null;
	    String tmpPath = null;
	    
	    if (path.lastIndexOf("\\") > -1) {
	    	realPath = path.substring(0, path.lastIndexOf("\\"));
	    	tmpPath = path.substring(path.lastIndexOf("\\") + 1);
	    }
		
	    if (path.lastIndexOf("/") > -1) {
	    	realPath = path.substring(0, path.lastIndexOf("/"));
	    	tmpPath = path.substring(path.lastIndexOf("/") + 1);
        }
		
	    realPath = realPath.length() != 0 ? realPath : "/";
        java.io.File file = new java.io.File(realPath);
        if (file.exists()) {
            flag = true;
        }
        
        if (isVirtual(realPath, sc)) {
        	realPath = sc.getRealPath(realPath);
            if (realPath.endsWith(sep)) {
            	realPath = realPath + tmpPath;
            } else {
            	realPath = realPath + sep + tmpPath;
            }
            return realPath;
        }
        
        if (flag) {
        	return path;
        } else {
        	return null;
        } 
	}
	
    private static boolean isVirtual(String path, ServletContext sc) {
        if (null != sc.getRealPath(path)) {
            java.io.File file = new java.io.File(sc.getRealPath(path));
            return file.exists();
        } else {
            return false;
        }
    }

    /**
     * 解析以boundary作为分割符的字节数组，将结果放入Map并返回
     * @param content
     * @param boundary
     * @return
     */
    public static Map<String, byte[]> parseData(byte[] content, String boundary) {
    	
    	Map<String, byte[]> rsMap = new HashMap<String, byte[]>();
    	List<Integer> boundaryIndex = new ArrayList<Integer>();
    	
    	int keyIndex = 0;
    	String key = null;
    	
    	for (int i = 0; i < content.length;) {
    		// -:45
    		// \r:13 回车
    		// \n:10 换行
    		// 44: boundary.length()
    		// 42: boundary.length() - 2
    		// 43: boundary.length() - 1
    		if (content[i] == 45 && content[i + 1] == 45 && content[i + boundary.length() - 2] == 13 && content[i + boundary.length() - 1] == 10) {
    			byte[] _boundary = new byte[boundary.length()];
    			// System.arraycopy(content, i, _boundary, 0, boundary.length()); // 存在native deep copy性能问题
    			fastCopy(content, i, _boundary, 0, boundary.length());
    			if (new String(_boundary).equals(boundary)) {
    				boundaryIndex.add(i + boundary.length());
    				i += (boundary.length() - 1);
    			}	
    		} else {
    			i++;
    		}
    	}
    	
    	for (int j = 0; j < boundaryIndex.size(); j++) {
    		if (j + 1 >= boundaryIndex.size()) {
    			break;
    		} else {
    			byte[] result = new byte[boundaryIndex.get(j + 1) - boundaryIndex.get(j) - boundary.length() - 2];
    		    // System.arraycopy(content, boundaryIndex.get(j), result, 0, boundaryIndex.get(j + 1) - boundaryIndex.get(j) - boundary.length() - 2); // 存在native deep copy性能问题
    			fastCopy(content, boundaryIndex.get(j), result, 0, boundaryIndex.get(j + 1) - boundaryIndex.get(j) - boundary.length() - 2);
    			if (keyIndex%2 == 0) { 
    				key = new String(result);
    			} else { 
    				rsMap.put(key, result);
    			}
    			keyIndex++;	
    		}
    	}

    	return rsMap;
    }
    
    private static void fastCopy(byte[] src, int srcPos, byte[] dest, int destPos, int length) {
    	int count = 0;
    	for (int i = srcPos; i < srcPos + length; i++) {
    		dest[count] = src[i];
    		count++;
    	}
    }

	/**
	 * 获取输入流中还剩的字节数
	 * @param in
	 * @return
	 */
	public static int getAvailableBytes(InputStream in) {
		try {
			return in.available();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

}