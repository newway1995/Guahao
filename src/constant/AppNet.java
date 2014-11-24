package constant;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.NameValuePair;


/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:AppNet.java
 * @Package:constant
 * @time:下午8:34:32
 * @useage:网络访问接口
 */
public class AppNet {
	private static class SingletonHolder{
		private static AppNet instance = new AppNet();
	}
	
	/**
	 * 单例模式
	 * */
	public static AppNet getInstance(){
		return SingletonHolder.instance;
	}
	
	public static String HTTP = "http://";
	public static String HTTPS = "https://";
	
	public static class Access{
		public static final int POST = 0;
		public static final int GET = 1;
	}
	
	//只允许在内部被访问	
	public static class UrlPrefixLogin{
		private static String DOMIN = "";//域
		private static String APIV1 = "";//接口		
		private static String prefixLogin = HTTP + DOMIN + APIV1;
	}
	
	
	//控制网络访问
	public static class NetUrl{
		/**
		 * 登录接口
		 */
		private static final String login = UrlPrefixLogin.prefixLogin + "";
		public static String login(ArrayList<NameValuePair> params){
			String result = login;
			String[] paramList = new String[] {"username","userpwd"};
			return getUrl(result, params, paramList);
		}
	}
	
	//返回完整的api地址
	private static String getUrl(String defaultUrl, ArrayList<NameValuePair> params, String[] paramList){
		String result = defaultUrl;
		if(params == null || paramList == null){
			result = result.substring(0, result.length() - 1);
			return result;
		}else{
			HashMap<String, String> map = new HashMap<String, String>();
			for(int i=0; i<params.size(); i++){
				NameValuePair nvp = params.get(i);
				String name = nvp.getName();
				String value = nvp.getValue();
				map.put(name, value);
			}
			for(int i=0; i<paramList.length; i++){
				String param = paramList[i];
				if(map.containsKey(param)){
					if (result.endsWith("/")) {
						result = result.substring(0, result.length() - 1);
						result += "?" + param + "=" + map.get(param);
					}
					else
						result += "&" + param + "=" + map.get(param);
				}
			}
		}
		return result;
	}
}
