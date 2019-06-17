package kr.or.ddit.util;

public class CookieUtil {
	
	public static String cookieString; // 분석할 쿠키 문자열
	
	/**
	 * 
	* Method : setCookieString
	* 작성자 : PC06
	* 변경이력 :
	* @param cookieString
	* Method 설명 : 분석할 쿠키 문자열을 받는다
	 */
	public static void setCookieString(String cookieString) {
		CookieUtil.cookieString = cookieString;
	}
	
	/**
	 * 
	* Method : getCookie
	* 작성자 : PC06
	* 변경이력 :
	* @param cookie
	* @return
	* Method 설명 : 쿠키 문자열에서 특정 쿠키 값을 가져온다
	 */
	public static String getCookie(String cookie) {
		String[] cookieArray = cookieString.split("; ");
//		cookieArray[0] = "userId=brown"
//		cookieArray[1] = "rememberme=true"
//		cookieArray[2] = "test=testValue"
		
		String cookieValue = "";
		
		for(String str : cookieArray){
			if(str.startsWith(cookie+"=")) {
				String[] cookieStr = str.split("=");
//				cookieStr[0] = "userId"
//				cookieStr[1] = "brown"
				cookieValue = cookieStr[1];
				break;
			}
		}
		
		return cookieValue;
	}
	
	
	/**
	 * 
	* Method : getCookie
	* 작성자 : PC06
	* 변경이력 :
	* @param cookie
	* @return
	* Method 설명 : 쿠키 문자열에서 특정 쿠키 값을 가져온다
	 */
//	public static String getCookie(String cookie) {
//		//cookieString = "userId=brown; rememberme=true; test=testValue"
//		//cookie = "userId"
//		String[] cookies = cookieString.split("; ");
//		//split 결과
//		//userId=brown
//		//rememberme=true
//		//test=testValue
//		String result = "";
//		for (int i = 0; i < cookies.length; i++) {
//			if(cookies[i].contains(cookie)){
//				result = cookies[i].substring(cookies[i].indexOf("=")+1);
//				break;
//			}
//		}
//		return result;
//	}
	
	
}
