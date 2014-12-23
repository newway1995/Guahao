package module.activity.geren;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import common.receiver.NetResultInterface;
import common.util.AsyncInter;
import common.util.CacheHandler;
import common.util.MyAsyncTask;

import constant.AppCode;
import constant.AppNet;
import constant.Constant;
import module.activity.LoginActivity;
import module.activity.R;
import module.activity.guahao.DoctorInfoActivity;
import module.activity.guahao.SelectDoctorActivity;
import module.adapter.DoctorAdapter;
import module.adapter.OrderAdapter;
import module.entity.Department;
import module.model.HospitalModel;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * 下午1:20:39
 * 我的预约单
 */
public class OrderActivity extends Activity{
	private final static String TAG = "OrderActivity";
	private ArrayList<HashMap<String, String >> list = new ArrayList<HashMap<String,String>>();;
	private ListView mListView;
	private OrderAdapter OrderAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		asyncGet();
		
		
	}
	
	private void initView(){
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		
	}
	//初始化数据
	private void initData(){
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Constant.saveOrderHistory(OrderActivity.this, list.get(position));//所选择的医生的信息已经保存到cache中
				startActivity(new Intent(OrderActivity.this,MyOrderActivity.class));
				Log.d(TAG, "Click Doctor = " + list.get(position).toString());
			}
		});
	}

	private void asyncGet(){
		AsyncInter inter = new AsyncInter() {
			String result;
			@Override
			public void onPreExecute() {
				
			}
			
			@Override
			public void onPostExecute() {
				// TODO Auto-generated method stub
					try {
						JSONObject jObject = new JSONObject(result);
						if(Integer.parseInt(jObject.getString("success"))==1)
						{
							setContentView(R.layout.activity_order);
							mListView = (ListView)findViewById(R.id.order_listview);
							JSONArray jArray = jObject.getJSONArray("order");
							for (int i = 0; i < jArray.length(); i++) {//循环便利
								JSONObject jsonObject = jArray.getJSONObject(i);
								HashMap< String, String> map = new HashMap<String, String>();
								map.put("doctor_name", jsonObject.getString("doctor_name"));//医生id
								map.put("hospital_name", jsonObject.getString("hospital_name"));
								map.put("section_name", jsonObject.getString("section_name"));
								map.put("order_info", jsonObject.getString("order_info"));
								map.put("order_time", jsonObject.getString("order_time"));
								list.add(map);
							}		
							Log.d(TAG, "List = " + list);
							initView();
							initData();
							OrderAdapter = new OrderAdapter(OrderActivity.this, list);		
							mListView.setAdapter(OrderAdapter);
						}else
						{
							setContentView(R.layout.activity_noorder);

						}
						
					} catch (JSONException e) {					
						e.printStackTrace();
					}
		
			}
			
			@Override
			public void interruptTask() {
				 
			}
			
			@Override
			public void doInBackground() {
				// TODO Auto-generated method stub
				Log.d(TAG, "doInBackground");
				ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair(Constant.USER_ID, CacheHandler.readCache(OrderActivity.this, Constant.USER_INFO, Constant.USER_ID)));
				params.add(new BasicNameValuePair("action", AppCode.ACTION_GET_ORDER));
				result = AppCode.getData(OrderActivity.this, params, AppNet.Access.GET);
			}
		};
		new MyAsyncTask(inter, true, OrderActivity.this).execute();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();
			break;
		case R.id.actionbar_share:
			Toast.makeText(this, "分享还没做好,不要着急哦,亲", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
