package constant;

import java.util.ArrayList;

import org.apache.http.NameValuePair;

import common.util.NetHandler;

import android.content.Context;
import android.util.Log;

public class AppCode {
	public static final String TAG = "AppCode";
	
	public static final String ACTION_CHANGE_PWD = "API_GH_CHANGE_PWD";
	public static final String ACTION_LOGIN = "API_GH_LOGIN";
	
	public static final int LOGIN = 1;
	public static final int CHANGE_PWD = 2;
	public static final int ISLOGIN = -2;
	
	/**
	 * 通过逻辑标签code 来获取 或者 提交数据
	 * @param AppCode 逻辑标签  【使用AppCode的静态变量】
	 * @param params  联网提交的参数 【使用BasicNameValuePair的列表】
	 * @param netCate  get方法/post方法 AppNet.Access.GET/POST
	 * @return
	 */
	public static String getData(Context mContext, int AppCode, ArrayList<NameValuePair> params, int netCate){
		String result = null;
		String urlName = null;
		switch (AppCode) {
		case LOGIN://登陆			
			urlName = AppNet.NetUrl.login(params);
			Log.d(TAG, "url = " + urlName);
			break;
		case CHANGE_PWD://修改密码
			urlName = AppNet.NetUrl.changePwd(params);
		default:
			break;
		}
		if (urlName != null && !urlName.equals("")) 
			Log.d(TAG, urlName);
		if(netCate == AppNet.Access.POST){
			result = NetHandler.netPost(urlName, params, mContext);
		}else if (netCate == AppNet.Access.GET){
			result = NetHandler.netGet(urlName, mContext);
		}
		return result;
	}
}
