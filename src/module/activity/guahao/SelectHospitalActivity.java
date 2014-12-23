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
import module.adapter.HospitalAdapter;
import module.entity.Hospital;
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
 * @ClassName:SelectHospitalActivity.java
 * @Package:module.activity.guahao
 * @time:上午9:48:08 2014-12-3
 * @useage:医院选择
 */
public class SelectHospitalActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener{
	private final String TAG = "SelectHospitalActivity";
	private RefreshLayout refreshLayout;
	private HospitalAdapter hospitalAdapter;
	private ArrayList<HashMap<String, String >> list;
	private int pageCount;
	private ListView mListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
		setContentView(R.layout.activity_mingyi);
		initView();
		initData();
		asyncGet(pageCount,++pageCount,false);
	}
	
	/**
	 * 初始化视图
	 * */
	private void initView(){
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle("北京");
		refreshLayout = (RefreshLayout)findViewById(R.id.mingyi_list);
		refreshLayout.setColorScheme(android.R.color.holo_green_dark, android.R.color.holo_green_light,
				android.R.color.holo_orange_light, android.R.color.holo_red_light);	
		mListView = (ListView)findViewById(R.id.mingyi_listview);
		list = new ArrayList<HashMap<String,String>>();
	}
	/**
	 * 初始化数据
	 * */
	private void initData(){
		refreshLayout.setOnRefreshListener(this);
		pageCount = 0;
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				HashMap<String, String> map = list.get(arg2);
				//被点击的hospital,用于传递
				Hospital clickHospital = new Hospital(Integer.parseInt(map.get("id")), map.get("name"), map.get("level"), map.get("img"), map.get("street"));
				//保存当前用户选择的医院id
				CacheHandler.writeCache(SelectHospitalActivity.this, Constant.USER_INFO, Constant.USER_HOSPITAL_ID, clickHospital.getId()+"");
				CacheHandler.writeCache(SelectHospitalActivity.this, Constant.USER_INFO, Constant.USER_HOSPITAL_NAME, clickHospital.getName());
				Intent intent = new Intent(SelectHospitalActivity.this,SelectSectionActivity.class);
				intent.putExtra("click_hospital", clickHospital);
				startActivity(intent);
			}
		});
	}
	
	/**
	 * 获取数据
	 * */
	private void asyncGet(int pageFrom,int pageCount,final boolean isClear){
		if (isClear) {
			list = new ArrayList<HashMap<String,String>>();
		}
		HospitalModel.getInstance().getHospitalByCID(this, "1108",0,1,new NetResultInterface(){
		
			@Override
			public void parseResult(String result) {
				Log.d(TAG, "Result = " + result);
				JSONObject jObject;
				try {
					jObject = new JSONObject(result);
					JSONArray jArray = jObject.getJSONArray("hospital");
					for (int i = 0; i < jArray.length(); i++) {//循环便利
						JSONObject jsonObject = jArray.getJSONObject(i);
						HashMap< String, String> map = new HashMap<String, String>();
						map.put("id", jsonObject.getString("id"));
						map.put("name", jsonObject.getString("name"));
						map.put("level", jsonObject.getString("level"));
						map.put("type", jsonObject.getString("type"));
						map.put("province", jsonObject.getString("province"));
						map.put("city", jsonObject.getString("city"));
						map.put("street", jsonObject.getString("street"));
						map.put("img", jsonObject.getString("img"));
						list.add(map);
					}		
					Log.d(TAG, "List = " + list);
					hospitalAdapter = new HospitalAdapter(SelectHospitalActivity.this, list);		
					mListView.setAdapter(hospitalAdapter);
				} catch (JSONException e) {					
					e.printStackTrace();
				} finally{
					refreshLayout.setRefreshing(false);
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
	protected void onStop() {
		finish();
		super.onStop();
	}
	
	@Override
	public void onRefresh() {
		asyncGet(pageCount, ++pageCount, true);
	}
}
