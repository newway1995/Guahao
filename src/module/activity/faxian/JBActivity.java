package module.activity.faxian;

import java.util.ArrayList;
import java.util.HashMap;

import module.activity.R;
import module.adapter.JBNewsAdapter;
import module.model.FaxianModel; 
import module.view.RefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import common.receiver.NetResultInterface;
import common.receiver.RefreshLoadInterface;

import constant.AppCode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * 下午12:57:30
 * 疾病与营养
 */
public class JBActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener{
	private final static String TAG = "JBActivity";
	
	private RefreshLayout swipeRefreshLayout;
	private ListView mListView;
	private JBNewsAdapter jbNewsAdapter;
	private ArrayList<HashMap<String, String >> list;	
	private int pageCount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_mingyi);
		super.onCreate(savedInstanceState);
		initView();
		initData();
	}
	
	private void initView(){
		getActionBar().setDisplayHomeAsUpEnabled(true);//设置ActionBar		
		swipeRefreshLayout = (RefreshLayout)findViewById(R.id.mingyi_list);
		mListView = (ListView)findViewById(R.id.mingyi_listview);
		list = new ArrayList<HashMap<String,String>>();
	}
	
	private void initData(){
		pageCount = 0;
		swipeRefreshLayout.setOnRefreshListener(this);
		swipeRefreshLayout.setColorScheme(android.R.color.holo_green_dark, android.R.color.holo_green_light,
				android.R.color.holo_orange_light, android.R.color.holo_red_light);
		swipeRefreshLayout.setOnLoadListener(new RefreshLoadInterface() {
			
			@Override
			public void onLoad() {
				// TODO Auto-generated method stub
				asyncGetData(pageCount,++pageCount,false);
				swipeRefreshLayout.setLoading(false);
			}
		});
		asyncGetData(pageCount,++pageCount,true);
	}
	
	//异步获取数据
	private void asyncGetData(int pageFrom,int pageCount,final boolean isClear){
		FaxianModel.getInstance().getNews(JBActivity.this, pageFrom, pageCount, AppCode.ACTION_HEALTH_NEWS, new NetResultInterface() {
			
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
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally{
					swipeRefreshLayout.setRefreshing(false);
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
			shareApp();
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
	
	private void shareApp(){
		Intent sendIntent = new Intent(); 
		sendIntent.setAction(Intent.ACTION_SEND);  
		sendIntent.putExtra(Intent.EXTRA_TEXT, "2014/2015年度大手笔制作,你还在因为担心挂号难而通宵排队吗?你还在由于挂号不上而被老婆责怪吗?你还在为了给朋友挂号而" +
				"拿出自己的休息时间吗? !哈! 你还在由于什么,挂号网App,你一生的医生,你值得拥有.");  
		sendIntent.setType("text/plain");  
		startActivity(Intent.createChooser(sendIntent, "Share"));  
	}
}
