package common.core;

import module.model.UserModel;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:BaseLoginActivity.java
 * @Package:module.activity
 * @time:下午3:31:24 2014-12-2
 * @useage:suo所有需要登录的activity需要继承这个类
 */
public abstract class BaseLoginActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		isLogin();
	}
	
	protected void initData(){}//初始化数据
	protected void initView(){}//初始化视图
	protected void skip(){}//跳转	
	public void widgetClick(View v) {}//执行点击事件
	@Override
    public void onClick(View v) {
        widgetClick(v);
    }
	
	protected void setRootView(){}
	
	protected void isLogin(){//判断是否登录并针对结果不同进行处理
		if (UserModel.getInstance().getIsLogin()) {//如果是已经登录,则不处理
			getActionBar().setDisplayHomeAsUpEnabled(true);
			setRootView();
			initView();
			initData();	
		}else {
			skip();
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) 
			finish();
		return super.onOptionsItemSelected(item);
	}
	
	
	@Override
	protected void onPause() {		
		super.onPause();
	}

	/**
	 * Activity 跳转
	 * */
	protected void showActivity(Context packageContext,Class<?>cls){
		Intent intent = new Intent(packageContext, cls);
		startActivity(intent);
	}
	
	/**
	 * Activity 跳转
	 * */
	protected void showActivity(Context packageContext,Class<?>cls,Bundle bundle){
		Intent intent = new Intent(packageContext, cls);
		intent.putExtras(bundle);
		startActivity(intent);
	}
	
	/**
	 * Activity 跳转
	 * */
	protected void showActivity(Intent intent){
		startActivity(intent);
	}
}
