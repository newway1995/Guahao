package module.activity.guahao;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import common.receiver.NetResultInterface;
import common.util.CacheHandler;
import constant.Constant;

import module.activity.R;
import module.adapter.DoctorAdapter;
import module.entity.Department;
import module.model.HospitalModel;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:SelectDoctorActivity.java
 * @Package:module.activity.guahao
 * @time:下午5:30:01 2014-12-17
 * @useage:选择医院
 */
public class SelectDoctorActivity extends Activity{

	@Override
	protected void onStop() {
		super.onStop();
		finish();
	}

	private final String TAG = "SelectDoctorActivity"; 
	@SuppressWarnings("unused")
	private Department clickDepartment;
	private ArrayList<HashMap<String, String >> list;
	private ListView mListView;
	private DoctorAdapter hospitalAdapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview_norefresh);
		initView();
		initData();
		asyncGet(CacheHandler.readCache(this, Constant.USER_INFO, Constant.USER_HOSPITAL_ID),
				CacheHandler.readCache(this, Constant.USER_INFO, Constant.USER_SECTION_ID));
	}
	
	//初始化视图
	private void initView(){
		getActionBar().setDisplayHomeAsUpEnabled(true);		
		mListView = (ListView)findViewById(R.id.listview_no_refresh);
		list = new ArrayList<HashMap<String,String>>();
		clickDepartment = (Department)getIntent().getSerializableExtra("click_department");
	}
	//初始化数据
	private void initData(){
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Constant.saveDoctor(SelectDoctorActivity.this, list.get(position));
				startActivity(new Intent(SelectDoctorActivity.this,DoctorInfoActivity.class));
				Log.d(TAG, "Click Doctor = " + list.get(position).toString());
			}
		});
	}

	private void asyncGet(String hid,String sid){
		HospitalModel.getInstance().getDoctorBySID(this, hid, sid, new NetResultInterface() {
			
			@Override
			public void parseResult(String result) {
				Log.d(TAG, "Result = " + result);
				JSONObject jObject;
				try {
					jObject = new JSONObject(result);
					JSONArray jArray = jObject.getJSONArray("doctor");
					for (int i = 0; i < jArray.length(); i++) {//循环便利
						JSONObject jsonObject = jArray.getJSONObject(i);
						HashMap< String, String> map = new HashMap<String, String>();
						map.put("id", jsonObject.getString("id"));//医生id
						map.put("name", jsonObject.getString("name"));
						map.put("hospital_id", jsonObject.getString("hospital_id"));
						map.put("section_id", jsonObject.getString("section_id"));
						map.put("img", jsonObject.getString("img"));
						map.put("ticket_num", jsonObject.getString("ticket_num"));
						map.put("level", jsonObject.getString("level"));
						map.put("favorite", jsonObject.getString("favorite"));
						list.add(map);
					}		
					Log.d(TAG, "List = " + list);
					hospitalAdapter = new DoctorAdapter(SelectDoctorActivity.this, list);		
					mListView.setAdapter(hospitalAdapter);
				} catch (JSONException e) {					
					e.printStackTrace();
				}
			}
		});
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
}
