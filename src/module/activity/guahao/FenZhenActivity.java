/**
 * FenZhenActivity.java
 * module.activity.guahao
 * niuwei
 * 2014-11-20上午12:46:22
 */
package module.activity.guahao;

import java.util.ArrayList;
import java.util.List;

import module.activity.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * 上午12:46:22
 * 智能导诊
 */
public class FenZhenActivity extends FragmentActivity{
	private static final String TAG = "FenZhenActivity";
	private ViewPager viewPager;
	private List<View> dots;
	private List<Fragment> mViews;
	private FenZhenLeftFragment fenZhenLeftFragment;
	private FenZhenRightFragment fenZhenRightFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fenzhen);
		initView();
		initData();
	}
	
	private void initView(){
		getActionBar().setDisplayHomeAsUpEnabled(true);
		viewPager = (ViewPager)findViewById(R.id.fenzhen_vp);
		mViews = new ArrayList<Fragment>();
		fenZhenLeftFragment = new FenZhenLeftFragment();//实例化对象
		fenZhenRightFragment = new FenZhenRightFragment();
		mViews.add(fenZhenLeftFragment);
		mViews.add(fenZhenRightFragment);		
		
		dots = new ArrayList<View>();
		dots.add(findViewById(R.id.fenzhen_dot0));
		dots.add(findViewById(R.id.fenzhen_dot1));
	}
	
	private void initData(){
		viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			private int oldPosition = 0;
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
				dots.get(position).setBackgroundResource(R.drawable.dot_focused);
				oldPosition = position;
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
		viewPager.setCurrentItem(0);
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
	
	/**
	 * 填充ViewPager页面的适配器
	 * 
	 * @author Administrator
	 * 
	 */
	private class MyAdapter extends FragmentPagerAdapter {

		public MyAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mViews.size();
		}		

		@Override
		public Fragment getItem(int arg0) {
			return mViews.get(arg0);
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			return super.instantiateItem(container, position);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return "测试";
			//return super.getPageTitle(position);
		}
	}
}
