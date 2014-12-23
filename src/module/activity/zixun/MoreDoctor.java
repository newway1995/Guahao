package module.activity.zixun;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import common.receiver.NetResultInterface;
import common.receiver.RefreshLoadInterface;

import module.activity.R;
import module.adapter.DoctorAdapter;
import module.entity.Doctor;
import module.model.HospitalModel;
import module.view.RefreshLayout;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:MoreDoctor.java
 * @Package:module.activity.zixun
 * @time:上午4:25:36 2014-12-23
 * @useage:咨询更多专家
 */
public class MoreDoctor extends Activity implements SwipeRefreshLayout.OnRefreshListener{
	public static final String TAG = "MoreDoctor";
		 
	private ListView mListView;
	private RefreshLayout swipeRefreshLayout;
	private DoctorAdapter adapter;
	private ArrayList<HashMap<String, String>> list;
	private Doctor doctor;
	private int pageCount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mingyi);
		initView();
		initData();
	}

	private void initView(){
		getActionBar().setDisplayHomeAsUpEnabled(true);
		swipeRefreshLayout = (RefreshLayout)findViewById(R.id.mingyi_list);
		mListView = (ListView)findViewById(R.id.mingyi_listview);
		list = new ArrayList<HashMap<String,String>>();
	}
	
	public void initData(){
		pageCount = 0;
		swipeRefreshLayout.setOnRefreshListener(this);
		swipeRefreshLayout.setColorScheme(android.R.color.holo_green_dark, android.R.color.holo_green_light,
				android.R.color.holo_orange_light, android.R.color.holo_red_light);		
		swipeRefreshLayout.setOnLoadListener(new RefreshLoadInterface() {
			//上拉监听器
			@Override
			public void onLoad() {
				// TODO Auto-generated method stub
				asyncGetData(pageCount,++pageCount,false);
				swipeRefreshLayout.setLoading(false);
			}
		});
		
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				HashMap<String, String> map = list.get(position);
				doctor = new Doctor(Integer.parseInt(map.get("id")), map.get("name"), map.get("section_name"), map.get("level"), map.get("hospital_name"), map.get("favorite"), map.get("img"));
				Intent intent = new Intent(MoreDoctor.this,ConsultActivity.class);
				intent.putExtra("consultDoctor", doctor);
				startActivity(intent);
			}
		});
		
		asyncGetData(pageCount,++pageCount,true);
	}
	
	
	private void asyncGetData(int pageFrom,int pageCount,final boolean isClear){
		HospitalModel.getInstance().getDoctor(this, true, pageCount, pageFrom, new NetResultInterface() {
			
			@Override
			public void parseResult(String result) {
				if (isClear) {
					list.clear();//先清空
				}
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
						map.put("hospital_name", jsonObject.getString("hospital_name"));
						map.put("section_id", jsonObject.getString("section_id"));
						map.put("section_name", jsonObject.getString("section_name"));
						map.put("img", jsonObject.getString("img"));
						map.put("ticket_num", jsonObject.getString("ticket_num"));
						map.put("level", jsonObject.getString("level"));
						map.put("favorite", jsonObject.getString("favorite"));
						list.add(map);
					}		
					Log.d(TAG, "List = " + list);
					adapter = new DoctorAdapter(MoreDoctor.this, list);		
					mListView.setAdapter(adapter);
				} catch (JSONException e) {					
					e.printStackTrace();
				} finally{
					swipeRefreshLayout.setRefreshing(false);
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

	
	@Override
	public void onRefresh() {
		asyncGetData(pageCount,++pageCount,false);
	}
	
}
