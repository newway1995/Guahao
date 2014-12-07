package common.core;

import org.kymjs.aframe.ui.ViewInject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:BaseActivity.java
 * @Package:common.core
 * @time:下午11:38:06 2014-12-6
 * @useage:基础的Activity
 */
public abstract class BaseActivity extends Activity implements OnClickListener{
	// 是否隐藏ActionBar 默认为显示
    private boolean mHiddenActionBar = false;
    //当activity隐藏之后是否销毁
    private boolean mDestroyActivity = false;
    //当点击返回按钮的时候是否弹出退出框
    private boolean mPopExitWindow = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (mHiddenActionBar) {
			getActionBar().setDisplayHomeAsUpEnabled(false);
		}
		super.onCreate(savedInstanceState);
	}
	
	/**
	 * 设置layout
	 * */
	protected void setRootView(){}
	
	/**
	 * 初始化视图
	 * */
	protected void initView(){}
	/**
	 * 初始化数据
	 * */
	protected void initData(){}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) 
			finish();
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * 点击事件
	 * */
	protected void widgetClick(View v){}
	
	@Override
    public void onClick(View v) {
        widgetClick(v);
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

	@Override
	protected void onStop() {
		if (mDestroyActivity) {//如果销毁
			finish();
		}
		super.onStop();
	}
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ( keyCode == KeyEvent.KEYCODE_BACK && mPopExitWindow) {
            ViewInject.create().getExitDialog(this);
        }
        return super.onKeyDown(keyCode, event);
    }

	public boolean ismHiddenActionBar() {
		return mHiddenActionBar;
	}

	public void setmHiddenActionBar(boolean mHiddenActionBar) {
		this.mHiddenActionBar = mHiddenActionBar;
	}

	public boolean ismDestroyActivity() {
		return mDestroyActivity;
	}

	public void setmDestroyActivity(boolean mDestroyActivity) {
		this.mDestroyActivity = mDestroyActivity;
	}

	public boolean ismPopExitWindow() {
		return mPopExitWindow;
	}

	public void setmPopExitWindow(boolean mPopExitWindow) {
		this.mPopExitWindow = mPopExitWindow;
	}
}
