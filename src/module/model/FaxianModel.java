package module.model;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.util.Log;
import common.receiver.ResultHandler;
import common.util.AsyncInter;
import common.util.MyAsyncTask;
import constant.AppCode;
import constant.AppNet;
import constant.Constant;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:FaxianModel.java
 * @Package:module.model
 * @time:下午10:16:12 2014-11-28
 * @useage:发现model
 */
public class FaxianModel {
	private static final String TAG = "FaxianModel";
	
	private static FaxianModel instance;
	/**
	 * 单例模式
	 * */
	public static FaxianModel getInstance(){
		if (instance == null) {
			synchronized (FaxianModel.class) {
				if (instance == null) {
					instance = new FaxianModel();
				}
			}
		}
		return instance;
	} 
	
	/**
	 * 获取名医在线消息
	 * @param pageFrom 第几页开始获取数据
	 * @param pageCount 页面总数
	 * */
	public void getNews(final Context context,final int pageFrom,final int pageCount,final String action,final ResultHandler resultHandler){
		AsyncInter inter = new AsyncInter() {
			String result = "";
			@Override
			public void onPreExecute() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPostExecute() {
				// TODO Auto-generated method stub
				Log.d(TAG, "result = "+result);
				resultHandler.parseResult(result);
			}
			
			@Override
			public void interruptTask() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void doInBackground() {
				ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair(Constant.PAGE_COUNT,pageCount+""));
				params.add(new BasicNameValuePair(Constant.PAGE_FROM,pageFrom+""));
				params.add(new BasicNameValuePair("action",action));
				result = AppCode.getData(context, AppCode.DOCTOR_ONLINE, params, AppNet.Access.GET);
				
				Log.d(TAG, "doInBackground result = " + result);
			}
		};
		new MyAsyncTask(inter, true, context).execute();
	}
}
