package module.model;



import java.util.ArrayList;

import org.apache.http.NameValuePair;

import common.receiver.NetResultInterface;
import common.util.AsyncInter;
import common.util.MyAsyncTask;
import constant.AppCode;
import constant.AppNet;

import android.content.Context;
import android.util.Log;

public class CommonModel {
	private final String TAG = "CommonModel";
	
	private static CommonModel instance;
	/**
	 * 单例模式
	 * */
	public static CommonModel getInstance(){
		if(instance == null){
			synchronized (CommonModel.class) {
				if (instance == null) {
					instance = new CommonModel();
				}
			}
		}
		return instance;
	}
	
	/**
	 * 获取省份信息
	 * */
	public void getProvince(final Context context,final NetResultInterface resultInterface){
		Log.d(TAG, "getProvince");
		AsyncInter inter = new AsyncInter() {
			String result = "";
			@Override
			public void onPreExecute() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPostExecute() {
				Log.d(TAG, "province " + result);
				resultInterface.parseResult(result);
			}
			
			@Override
			public void interruptTask() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void doInBackground() {
				result = AppCode.getData(context, AppCode.GET_PROVINCE, null, AppNet.Access.GET);				
			}
		};
		new MyAsyncTask(inter, true, context).execute();
	}
	
	
	/**
	 * 获取城市消息
	 * */
	public void getCityByProID(final Context context,final int id,final NetResultInterface resultInterface){
		Log.d(TAG, "getCityByProID");
		AsyncInter inter = new AsyncInter() {
			String result = "";
			@Override
			public void onPreExecute() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPostExecute() {
				Log.d(TAG, "city = "+result);
				resultInterface.parseResult(result);
			}
			
			@Override
			public void interruptTask() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void doInBackground() {
				ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
				result = AppCode.getData(context, AppCode.GET_CITY_BY_PRO_ID, params, AppNet.Access.GET);
			}
		};
		new MyAsyncTask(inter, true, context).execute();
	}
	
	public void getWeather(Context context , NetResultInterface resultInterface){
		AsyncInter inter = new AsyncInter() {
			String result = "";
			
			@Override
			public void onPreExecute() {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onPostExecute() {
				// TODO Auto-generated method stub
				Log.d(TAG, "result = " + result);
			}
			
			@Override
			public void interruptTask() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void doInBackground() {
				// TODO Auto-generated method stub
				
			}
		};
		new MyAsyncTask(inter, true, context).execute();
	}
}
