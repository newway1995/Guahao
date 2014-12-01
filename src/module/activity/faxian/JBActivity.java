package module.activity.faxian;

import java.util.ArrayList;
import java.util.HashMap;

import module.activity.R;
import module.adapter.JBNewsAdapter;
import module.model.FaxianModel; 

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import common.receiver.ResultHandler;

import constant.AppCode;

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
 * 下午12:57:30
 * 疾病与营养
 */
public class JBActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener{
	private final static String TAG = "JBActivity";
	
	private SwipeRefreshLayout swipeRefreshLayout;
	private ListView mListView;
	private JBNewsAdapter jbNewsAdapter;
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
		FaxianModel.getInstance().getNews(this, 0, 1, AppCode.ACTION_HEALTH_NEWS, new ResultHandler() {
			
			@Override
			public void parseResult(String result) {
				Log.d(TAG, "result = "+result);
				list.clear();
				JSONObject jObject;
				try {
					jObject = new JSONObject(result);
					JSONArray jArray = jObject.getJSONArray("news");
					for (int i = 0; i < jArray.length(); i++) {//循环便利
						JSONObject jsonObject = jArray.getJSONObject(i);
						HashMap< String, String> map = new HashMap<String, String>();
						map.put("title", jsonObject.getString("title"));//编码有点问题
						map.put("id", jsonObject.getString("id"));
						map.put("content", jsonObject.getString("content"));
						map.put("time", jsonObject.getString("time"));
						map.put("img", jsonObject.getString("img"));
						list.add(map);
					}		
					jbNewsAdapter = new JBNewsAdapter(JBActivity.this,list);		
					mListView.setAdapter(jbNewsAdapter);
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
