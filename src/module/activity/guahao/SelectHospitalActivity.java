package module.activity.guahao;

import module.activity.R;
import module.view.RefreshLayout;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

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
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
		setContentView(R.layout.activity_mingyi);
		initView();
		initData();
		asyncGet();
	}
	
	/**
	 * 初始化视图
	 * */
	private void initView(){
		getActionBar().setDisplayHomeAsUpEnabled(true);
		refreshLayout = (RefreshLayout)findViewById(R.id.mingyi_list);
		refreshLayout.setColorScheme(android.R.color.holo_green_dark, android.R.color.holo_green_light,
				android.R.color.holo_orange_light, android.R.color.holo_red_light);	
	}
	/**
	 * 初始化数据
	 * */
	private void initData(){
		refreshLayout.setOnRefreshListener(this);
	}
	
	/**
	 * 获取数据
	 * */
	private void asyncGet(){
		
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
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Toast.makeText(SelectHospitalActivity.this, "刷新一秒钟", Toast.LENGTH_SHORT).show();
				refreshLayout.setRefreshing(false);
			}
		}, 1000);
	}
}
