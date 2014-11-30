package module.activity.faxian;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import common.receiver.ResultHandler;

import constant.AppCode;


import module.activity.R;
import module.adapter.MingyiNewsAdapter;
import module.model.FaxianModel;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * 上午8:48:24
 * 名医在线
 */
public class MingyiActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener{
	private final static String TAG = "MingyiActivity";
	
	private SwipeRefreshLayout swipeRefreshLayout;
	private ListView mListView;
	private MingyiNewsAdapter mingyiNewsAdapter;
	private ArrayList<HashMap<String, String >> list;	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_mingyi);
		super.onCreate(savedInstanceState);
		initView();
		initData();
	}
	
	private void initView(){
		getActionBar().setDisplayHomeAsUpEnabled(true);//设置ActionBar
		getActionBar().setTitle(getResources().getString(R.string.mingyizaixian));
		swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.mingyi_list);
		mListView = (ListView)findViewById(R.id.mingyi_listview);
		list = new ArrayList<HashMap<String,String>>();
	}
	
	private void initData(){
		swipeRefreshLayout.setOnRefreshListener(this);
		swipeRefreshLayout.setColorScheme(android.R.color.holo_green_dark, android.R.color.holo_green_light,
				android.R.color.holo_orange_light, android.R.color.holo_red_light);		
		asyncGetData();
	}
	
	//异步获取数据
	private void asyncGetData(){
		FaxianModel.getInstance().getNews(MingyiActivity.this, 0, 1, AppCode.ACTION_DOCTOR_ONLINE, new ResultHandler() {
			
			@Override
			public void parseResult(String result) {
				list.clear();//先清空
				Log.d(TAG, "result = "+result);
				JSONObject jObject;
				try {
					jObject = new JSONObject(result);
					JSONArray jArray = jObject.getJSONArray("news");
					for (int i = 0; i < jArray.length(); i++) {//循环便利
						JSONObject jsonObject = jArray.getJSONObject(i);
						HashMap< String, String> map = new HashMap<String, String>();
						map.put("id", jsonObject.getString("id"));
						map.put("title", jsonObject.getString("title"));
						map.put("content", jsonObject.getString("content"));
						map.put("time", jsonObject.getString("time"));
						map.put("img", jsonObject.getString("img"));
						list.add(map);
					}		
					mingyiNewsAdapter = new MingyiNewsAdapter(MingyiActivity.this,list);		
					mListView.setAdapter(mingyiNewsAdapter);
					swipeRefreshLayout.setRefreshing(false);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		});
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.share, menu);
        return true;
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

	@Override
	public void onRefresh() {	
		asyncGetData();
	}	
		
}
