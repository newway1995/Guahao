package constant;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.NameValuePair;

import common.util.NetHandler;

import android.content.Context;
import android.util.Log;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:AppCode.java
 * @Package:constant
 * @time:下午4:36:28 2014-12-5
 * @useage:面向用户的
 */
public class AppCode {
	public static final String TAG = "AppCode";
	
	public static final String ACTION_CHANGE_PWD = "API_GH_CHANGE_PWD";
	public static final String ACTION_LOGIN = "API_GH_LOGIN";
	public static final String ACTION_DOCTOR_ONLINE = "API_GH_GET_DOCTOR_ONLINE";
	public static final String ACTION_DOCTOR_DYNAMIC = "API_GH_GET_DOCTOR_DYNAMIC";
	public static final String ACTION_HEALTH_NEWS = "API_GH_GET_HEALTHY_NEWS";
	public static final String ACTION_GET_PROVINCE = "API_GH_GET_PROVINCE";
	public static final String ACTION_GET_CITY_BY_PROID = "API_GH_GET_CITY_BY_PRO_ID";
	public static final String ACTION_REGISTER = "API_GH_REGISTER";
	public static final String ACTION_GET_HOSPITAL = "API_GH_GET_HOSPITAL_BY_CITY_ID";
	public static final String ACTION_GET_SECTION = "API_GH_GET_SECTION_BY_HID";
	public static final String ACTION_GET_DOCTOR = "API_GH_GET_DOCTOR_BY_ID";
	public static final String ACTION_GET_TICKET ="API_GH_GET_TICKET_BY_DID";
	public static final String ACTION_GUAHAO ="API_GH_GUAHAO";
	public static final String ACTION_GET_ORDER = "API_GH_GET_ORDER_BY_USER_ID";
	public static final int LOGIN = 1;
	public static final int CHANGE_PWD = 2;
	public static final int DOCTOR_ONLINE = 3;
	public static final int ISLOGIN = -2;
	public static final int GET_PROVINCE = 4;
	public static final int GET_CITY_BY_PRO_ID= 5;
	
	/** 
	 * 通过逻辑标签code 来获取 或者 提交数据
	 * @param AppCode 逻辑标签  【使用AppCode的静态变量】
	 * @param params  联网提交的参数 【使用BasicNameValuePair的列表】
	 * @param netCate  get方法/post方法 AppNet.Access.GET/POST
	 * @return
	 */
	public static String getData(Context mContext,ArrayList<NameValuePair> params, int netCate){
		String result = null;
		String urlName = null;
		urlName = getUrl(AppNet.URL, params);
		Log.d(TAG, urlName);
		if(netCate == AppNet.Access.POST){
			result = NetHandler.netPost(urlName, params, mContext);
		}else if (netCate == AppNet.Access.GET){
			result = NetHandler.netGet(urlName, mContext);
		}
		return result;
	}
	
	
	//返回完整的api地址
	public static String getUrl(String defaultUrl, ArrayList<NameValuePair> params){
		String result = defaultUrl;
		if(params == null ){
			result = result.substring(0, result.length() - 1);
			return result;
		}else{
			HashMap<String, String> map = new HashMap<String, String>();
			for(int i=0; i<params.size(); i++){
				NameValuePair nvp = params.get(i);
				String name = nvp.getName();
				String value = nvp.getValue();
				map.put(name, value);
				if (result.endsWith("index.php")) 
					result += "?" + name + "=" + value;					
				else
					result += "&" + name + "=" + value;
			}			
		}
		return result;
	}
}
