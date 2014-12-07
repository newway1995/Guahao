package module.activity;


import common.util.LocationUtils;

import module.activity.faxian.FaxianFragment;
import module.activity.geren.GerenFragment;
import module.activity.guahao.GuahaoFragment;
import module.activity.guahao.MessageActivity;
import module.activity.zixun.ZixunFragment;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnClickListener{

	private final static String TAG = "MainActivity";
	private GuahaoFragment guahaoFragment;
	private ZixunFragment zixunFragment;
	private FaxianFragment faxianFragment;
	private GerenFragment gerenFragment;
	private long exitTime = 0;//第一次点击退出的时间
	
	/**
	 * 用于对Fragment进行管理
	 */
	private FragmentManager fragmentManager;
	private Resources resources;
	
	/**
	 * 底部四个按钮
	 */
	private TextView guahaoText;
	private TextView zixunText;
	private TextView faxianText;
	private TextView gerenText;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView(){
    	guahaoText = (TextView)findViewById(R.id.main_guahao);
    	zixunText = (TextView)findViewById(R.id.main_zixun);
    	faxianText = (TextView)findViewById(R.id.main_faxian);
    	gerenText = (TextView)findViewById(R.id.main_geren_center);
    	
    	guahaoText.setOnClickListener(this);
    	zixunText.setOnClickListener(this);
    	faxianText.setOnClickListener(this);
    	gerenText.setOnClickListener(this);
    	
    	resources = getResources();
    }
    
    private void initData(){
    	fragmentManager = getSupportFragmentManager();
    	LocationUtils.getCNBylocation(this);//地理位置判断
    	Log.d(TAG, "location city = " + LocationUtils.cityName);
		setTabSelection(0);
    }    

    /**
	 * 根据传入的index参数来设置选中的tab页。
	 * 
	 */
    private void setTabSelection(int index){
    	// 重置按钮
    	resetBtn();
    	Drawable drawable;
  		// 开启一个Fragment事务
   		FragmentTransaction transaction = fragmentManager.beginTransaction();
    	// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
   		hideFragments(transaction);
   		switch (index) {
		case 0:
			// 当点击了消息tab时，改变控件的图片和文字颜色
			drawable = resources.getDrawable(R.drawable.ic_tab_guahao_checked);
			drawable.setBounds(0, 0, drawable.getMinimumWidth(),drawable.getMinimumHeight());
			guahaoText.setCompoundDrawables(null, drawable, null, null);
			setTextColor(guahaoText);
			if (guahaoFragment == null){
				// 如果MessageFragment为空，则创建一个并添加到界面上
				guahaoFragment = new GuahaoFragment();
				transaction.add(R.id.main_content, guahaoFragment);
			} else{
				// 如果MessageFragment不为空，则直接将它显示出来
				guahaoFragment.updataActionBar();
				transaction.show(guahaoFragment);
			}
			break;
		case 1:
			drawable = resources.getDrawable(R.drawable.ic_tab_consult_checked);
			drawable.setBounds(0, 0, drawable.getMinimumWidth(),drawable.getMinimumHeight());
			zixunText.setCompoundDrawables(null, drawable, null, null);
			setTextColor(zixunText);
			if (zixunFragment == null){
				zixunFragment = new ZixunFragment();
				transaction.add(R.id.main_content, zixunFragment);
			} else{
				zixunFragment.updataActionBar();
				transaction.show(zixunFragment);
			}
			break;
		case 2:
			drawable = resources.getDrawable(R.drawable.ic_tab_discovery_checked);
			drawable.setBounds(0, 0, drawable.getMinimumWidth(),drawable.getMinimumHeight());
			faxianText.setCompoundDrawables(null, drawable, null, null);
			setTextColor(faxianText);
			if (faxianFragment == null){
				faxianFragment = new FaxianFragment();
				transaction.add(R.id.main_content, faxianFragment);
			} else{
				faxianFragment.updataActionBar();
				transaction.show(faxianFragment);
			}
			break;
		case 3:
			drawable = resources.getDrawable(R.drawable.ic_tab_me_checked);
			drawable.setBounds(0, 0, drawable.getMinimumWidth(),drawable.getMinimumHeight());
			gerenText.setCompoundDrawables(null, drawable, null, null);
			setTextColor(gerenText);
			if (gerenFragment == null){
				gerenFragment = new GerenFragment();
				transaction.add(R.id.main_content, gerenFragment);
			} else{
				gerenFragment.updataActionBar();
				transaction.show(gerenFragment);
			}
			break;
			
		default:
			break;
		}
   		transaction.commit();
    }
    
    /**
     * 设置被点击之后的颜色
     * */
    private void setTextColor(TextView t){
    	t.setTextColor(resources.getColor(R.color.tab_click_blue));
    }
    
    /**
     * 设置灰色
     * */
    private void unSetTextColor(TextView t){
    	t.setTextColor(resources.getColor(R.color.gray));
    }
    
    /**
	 * 清除掉所有的选中状态。
	 */
    private void resetBtn(){
    	Drawable drawable = resources.getDrawable(R.drawable.ic_tab_guahao_unchecked);
    	drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
    	guahaoText.setCompoundDrawables(null, drawable, null, null);
    	unSetTextColor(guahaoText);
    	drawable = resources.getDrawable(R.drawable.ic_tab_consult_unchecked);
    	drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
    	zixunText.setCompoundDrawables(null, drawable, null, null);
    	unSetTextColor(zixunText);
    	drawable = resources.getDrawable(R.drawable.ic_tab_discovery_unchecked);
    	drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
    	faxianText.setCompoundDrawables(null,drawable , null, null);
    	unSetTextColor(faxianText);
    	drawable = resources.getDrawable(R.drawable.ic_tab_me_unchecked);
    	drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
    	gerenText.setCompoundDrawables(null, drawable, null, null);
    	unSetTextColor(gerenText);
    }
    
    /**
	 * 将所有的Fragment都置为隐藏状态。
	 * 
	 * @param transaction
	 *            用于对Fragment执行操作的事务
	 */
    private void hideFragments(FragmentTransaction transaction){
    	if (guahaoFragment != null)
		{
			transaction.hide(guahaoFragment);
		}
		if (zixunFragment != null)
		{
			transaction.hide(zixunFragment);
		}
		if (faxianFragment != null)
		{
			transaction.hide(faxianFragment);
		}
		if (gerenFragment != null)
		{
			transaction.hide(gerenFragment);
		}
    }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.main_guahao:
			setTabSelection(0);
			break;
		case R.id.main_zixun:
			setTabSelection(1);
			break;
		case R.id.main_faxian:
			setTabSelection(2);
			break;
		case R.id.main_geren_center:
			setTabSelection(3);
			break;
		default:
			break;
		}
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.actionbar_message:
			Intent intent = new Intent(MainActivity.this,MessageActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}	
	
	@Override
	public boolean onKeyDown(int keyCode,KeyEvent event){
		if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {//弹出确认
//			final Dialog dialog = new Dialog(MainActivity.this,R.style.ExistDialog);
//			            
//            LayoutInflater inflater = getLayoutInflater();
//            View view = inflater.inflate(R.layout.exist_dialog, null);  
//            //设置它的ContentView
//            dialog.setContentView(view); 
//            dialog.show();
//            ((Button)view.findViewById(R.id.exist_sure)).setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View arg0) {
//					MainActivity.this.finish();
//				}
//			});
//            ((Button)view.findViewById(R.id.exist_cancel)).setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View arg0) {
//					dialog.dismiss();
//				}
//			});        
			
			/**
			 * 第二种方式 
			 * 1000ms 内连续点击退出
			 * */
			if (System.currentTimeMillis() - exitTime > 1000) {
				Toast.makeText(this, "再按一次退出应用程序", Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			}else {
				exit();
			}
		}
		return true;
	}	
	
	private void exit(){
		Intent intent = new Intent(Intent.ACTION_MAIN);  
        intent.addCategory(Intent.CATEGORY_HOME);  
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
        startActivity(intent);  
        android.os.Process.killProcess(android.os.Process.myPid());
	}
}
