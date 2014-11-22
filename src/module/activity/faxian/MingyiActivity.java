package module.activity.faxian;

import java.util.ArrayList;
import java.util.HashMap;

import org.kymjs.aframe.http.KJHttp;
import org.kymjs.aframe.http.StringCallBack;

import module.activity.R;
import module.adapter.MingyiNewsAdapter;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
	private KJHttp kjHttp; // 异步网络数据的获取
	
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
		kjHttp = new KJHttp();
		list = new ArrayList<HashMap<String,String>>();
	}
	
	private void initData(){
		swipeRefreshLayout.setOnRefreshListener(this);
		swipeRefreshLayout.setColorScheme(android.R.color.holo_green_dark, android.R.color.holo_green_light,
				android.R.color.holo_orange_light, android.R.color.holo_red_light);
		createLoadingDialog(this).show();
	}
	
	//异步获取数据
	@SuppressWarnings("unused")
	private void asyncGetData(String url){
		kjHttp.urlGet(url, new StringCallBack() {
			
			@Override
			public void onSuccess(String json) {
				// TODO Auto-generated method stub
				try { 					
														
				} catch (Exception e) {
					Log.d(TAG, "----------------------asyncGetData(kjdb)-------------------------");
				}
				jsonParser(json);
			}
		});
	}
	
	//解析数据
	private void jsonParser(String json){
		mingyiNewsAdapter = new MingyiNewsAdapter(this,list);		
		mListView.setAdapter(mingyiNewsAdapter);
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
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "刷新加载完毕", Toast.LENGTH_SHORT).show();
				swipeRefreshLayout.setRefreshing(false);
			}
		}, 1000);
	}	
	
	/** 
     * 得到自定义的progressDialog 
     * @param context 
     * @param msg 
     * @return 
     */ 
	private Dialog createLoadingDialog(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);  
		View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view  
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.loading_view);// 加载布局
        // main.xml中的ImageView  
        ImageView spaceshipImage = (ImageView) v.findViewById(R.id.loading_img);  
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(  
                context, R.anim.loading_animation);
        spaceshipImage.startAnimation(hyperspaceJumpAnimation); 
        
        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog  
        
        loadingDialog.setCancelable(true);// 不可以用“返回键”取消  
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(  
                LinearLayout.LayoutParams.MATCH_PARENT,  
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局  
        return loadingDialog;  
	}
}
