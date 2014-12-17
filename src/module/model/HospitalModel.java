package module.model;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.util.Log;

import common.receiver.NetResultInterface;
import common.util.AsyncInter;
import common.util.MyAsyncTask;
import constant.AppCode;
import constant.AppNet;
import constant.Constant;

public class HospitalModel {
	private final String TAG = "HospitalModel";
	
	private static HospitalModel instance;
	private HospitalModel(){}
	/**
	 * 单例模式
	 * */
	public static HospitalModel getInstance(){
		if(instance == null){
			synchronized (CommonModel.class) {
				if (instance == null) {
					instance = new HospitalModel();
				}
			}
		}
		return instance;
	}
	
	
	/**
	 * @param context
	 * @param city_id
	 * @param pageFrom
	 * @param resultInterface
	 * TODO获取医院信息
	 */
	public void getHospitalByCID(final Context context,final String city_id,final int pageFrom,final int pageCount,final NetResultInterface resultInterface){
		Log.d(TAG, "getHospitalByCID");
		AsyncInter inter = new AsyncInter() {
			String result = "";
			@Override
			public void onPreExecute() {

				
			}
			
			@Override
			public void onPostExecute() {
				Log.d(TAG, "getHospitalByCID " + result);
				resultInterface.parseResult(result);
			}
			
			@Override
			public void interruptTask() {

				
			}
			
			@Override
			public void doInBackground() {
				ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair(Constant.PAGE_COUNT,pageCount+""));
				params.add(new BasicNameValuePair(Constant.PAGE_FROM,pageFrom+""));
				params.add(new BasicNameValuePair(Constant.CITY_ID,city_id));
				params.add(new BasicNameValuePair("action",AppCode.ACTION_GET_HOSPITAL));
				result = AppCode.getData(context, params, AppNet.Access.GET);
				
				Log.d(TAG, "doInBackground result = " + result);
			}
		};
		new MyAsyncTask(inter, true, context).execute();
	}
	
	/**
	 * @param context
	 * @param hid
	 * @param resultInterface
	 * 获取医院的部门
	 */
	public void getSectionByHID(final Context context , final String hid,final NetResultInterface resultInterface){
		Log.d(TAG, "getSectionByHID");
		AsyncInter inter = new AsyncInter() {
			String result = "";
			@Override
			public void onPreExecute() {

				
			}
			
			@Override
			public void onPostExecute() {

				Log.d(TAG, "getSectionByHID " + result);
				resultInterface.parseResult(result);
			}
			
			@Override
			public void interruptTask() {

			}
			
			@Override
			public void doInBackground() {
				ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("action",AppCode.ACTION_GET_SECTION));
				params.add(new BasicNameValuePair(Constant.HID, hid));
				result = AppCode.getData(context, params, AppNet.Access.GET);
			}
		};
		new MyAsyncTask(inter, true, context).execute();
	}
	
	public void getDoctorBySID(final Context context , final String hid,final String sid,final NetResultInterface resultInterface){
		Log.d(TAG, "getSectionByHID");
		AsyncInter inter = new AsyncInter() {
			String result = "";
			@Override
			public void onPreExecute() {
			}
			
			@Override
			public void onPostExecute() {
				Log.d(TAG, "getSectionByHID " + result);
				resultInterface.parseResult(result);
			}
			
			@Override
			public void interruptTask() {

			}
			
			@Override
			public void doInBackground() {
				ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("action",AppCode.ACTION_GET_DOCTOR));
				params.add(new BasicNameValuePair(Constant.HID, hid));
				params.add(new BasicNameValuePair(Constant.SID, sid));
				result = AppCode.getData(context, params, AppNet.Access.GET);
			}
		};
		new MyAsyncTask(inter, true, context).execute();
	}
}
