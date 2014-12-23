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
import module.entity.Department;
import module.entity.Hospital;
import module.model.HospitalModel;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:SelectSectionActivity.java
 * @Package:module.activity.guahao
 * @time:上午11:55:42 2014-12-3
 * @useage:选择医院科室
 */
public class SelectSectionActivity extends Activity{
	private final String TAG = "SelectSectionActivity"; 
	
	private ArrayList<HashMap<String, String >> list;
	private ListView mListView;
	private SimpleAdapter adapter;
	private int[] to;
	private String[] from;
	private Hospital clickHospital;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
		setContentView(R.layout.activity_mingyi);
		initView();
		initData();		
		//判断页面的来源--是直接从guahaoFragment跳转还是从医院列表当中点击进入的
		if (clickHospital == null) {
			getActionBar().setTitle(CacheHandler.readCache(this, Constant.USER_INFO, Constant.USER_HOSPITAL_NAME));
			asyncGet(CacheHandler.readCache(this, Constant.USER_INFO, Constant.USER_HOSPITAL_ID));
		}else {
			getActionBar().setTitle(clickHospital.getName());
			asyncGet(clickHospital.getId() + "");
		}		
	}
	
	private void initView(){
		getActionBar().setDisplayHomeAsUpEnabled(true);			
		mListView = (ListView)findViewById(R.id.mingyi_listview);
	}
	
	private void initData(){
		from = new String[]{"name"};//adapter使用
		to = new int[]{R.id.select_section_name};//adapter使用
		list = new ArrayList<HashMap<String,String>>();
		
		clickHospital = (Hospital)getIntent().getSerializableExtra("click_hospital");		
		Log.d(TAG, "ClickHospital = " + clickHospital);
		
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				HashMap<String, String> map = list.get(arg2);
				Department clickDepartment = new Department(Integer.parseInt(map.get("id")), map.get("name"),map.get("description"));
				CacheHandler.writeCache(SelectSectionActivity.this, Constant.USER_INFO, Constant.USER_SECTION_ID, clickDepartment.getId() + "");
				CacheHandler.writeCache(SelectSectionActivity.this, Constant.USER_INFO, Constant.USER_SECTION_NAME, clickDepartment.getName());
				Intent intent = new Intent(SelectSectionActivity.this,SelectDoctorActivity.class);
				intent.putExtra("click_department", clickDepartment);
				startActivity(intent);
			}
		});
	}
	
	private void asyncGet(String hid){
		//初始化list
		HospitalModel.getInstance().getSectionByHID(this, hid, new NetResultInterface() {
			
			@Override
			public void parseResult(String result) {
				JSONObject jObject;
				try {
					jObject = new JSONObject(result);
					JSONArray jArray = jObject.getJSONArray("section");
					for (int i = 0; i < jArray.length(); i++) {//循环便利
						JSONObject jsonObject = jArray.getJSONObject(i);
						HashMap< String, String> map = new HashMap<String, String>();
						map.put("id", jsonObject.getString("id"));
						map.put("name", jsonObject.getString("name"));
						map.put("description", jsonObject.getString("description"));
						list.add(map);
					}		
					Log.d(TAG, "List = " + list);
					adapter = new SimpleAdapter(SelectSectionActivity.this, list, R.layout.item_select_section, from, to);
					mListView.setAdapter(adapter);
				} catch (JSONException e) {

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
