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
	
	private static final String HTTP = "http://";
	public static final String HTTPS = "https://";
	private static final String DOMIN = "192.168.169.4:8888/";
	private static final String APIV1 = "SystemDesign/index.php";//接口
	private static final String URL = HTTP + DOMIN + APIV1;
	
	public static class Access{
		public static final int POST = 0;
		public static final int GET = 1;
	}	
	
	
	//控制网络访问
	public static class NetUrl{
		/**
		 * 登录接口
		 */
		private static final String login = URL;
		public static String login(ArrayList<NameValuePair> params){
			String result = login;
			String[] paramList = new String[] {"username","password","action"};
			return getUrl(result, params, paramList);
		}
		
		/**
		 * 修改密码
		 * */
		private static final String changePwd = URL;
		public static String changePwd(ArrayList<NameValuePair> params){
			String result = changePwd;
			String[] paramList = new String[] {"old_password","new_password","user_id","action"};
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
					if (result.endsWith("index.php")) 
						result += "?" + param + "=" + map.get(param);					
					else
						result += "&" + param + "=" + map.get(param);
				}
			}
		}
		return result;
	}
}
