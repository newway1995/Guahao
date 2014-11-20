package constant;

import java.util.ArrayList;

import org.apache.http.NameValuePair;

import android.content.Context;

public class AppCode {
	public static final String TAG = "AppCode";
	
	/**
	 * 通过逻辑标签code 来获取 或者 提交数据
	 * @param AppCode 逻辑标签  【使用AppCode的静态变量】
	 * @param params  联网提交的参数 【使用BasicNameValuePair的列表】
	 * @param netCate  get方法/post方法 AppNet.Access.GET/POST
	 * @return
	 */
	public static String getData(Context mContext, int AppCode, ArrayList<NameValuePair> params, int netCate){
		String result = "";
		return result;
	}
}
