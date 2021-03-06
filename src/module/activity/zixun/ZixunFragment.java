package module.activity.zixun;

import java.util.ArrayList;
import java.util.List;

import common.util.CacheHandler;
import common.util.LocationUtils;
import constant.Constant;


import module.activity.R;
import module.activity.guahao.SelectLocationActivity;
import module.view.ZixunDoctorLayout;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * @author niuwei
 * @email nniuwei@163.com
 * 下午1:14:52
 * 咨询
 */
public class ZixunFragment extends Fragment implements OnClickListener{
	private final static String TAG = "ZixunFragment";
	private TextView zixun_search;//咨询搜索
	private ImageView zixun_consult;
	private TextView zixun_more_zhuanjia;//咨询更多专家
	
	/**
	 * 下面滑动图片导航
	 * */
	private ViewPager viewPager;
	private List<View> dots;
	private List<View> mViews;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View parentView = inflater.inflate(R.layout.fragment_zixun, null);
		Log.d(TAG, "onCreateView");
		
		initViewPager();//调用顺序不能随便改
		
		//mViews.add(inflater.inflate(R.layout.item_zixun_scroll_layout, null));
		//mViews.add(inflater.inflate(R.layout.item_zixun_scroll_layout, null));
		//mViews.add(inflater.inflate(R.layout.item_zixun_scroll_layout, null));
		
		initView(parentView);
		initData();
		return parentView;
	}
	
	private void initViewPager(){
		mViews = new ArrayList<View>();			
		ZixunDoctorLayout sView1 = new ZixunDoctorLayout(getActivity(),1);
		ZixunDoctorLayout sView2 = new ZixunDoctorLayout(getActivity(),2);
		ZixunDoctorLayout sView3= new ZixunDoctorLayout(getActivity(),3);
		mViews.add(sView1.getView());
		mViews.add(sView2.getView());
		mViews.add(sView3.getView());
		//初始化数据
	}
	
	private void initView(View parentView){
		updataActionBar();
		zixun_search = (TextView)parentView.findViewById(R.id.zixun_search);
		zixun_consult = (ImageView)parentView.findViewById(R.id.zixun_consult);
		zixun_more_zhuanjia = (TextView)parentView.findViewById(R.id.zixun_more_zhuanjia);
		zixun_search.setOnClickListener(this);
		zixun_consult.setOnClickListener(this);
		zixun_more_zhuanjia.setOnClickListener(this);
		
		viewPager = (ViewPager)parentView.findViewById(R.id.zixun_vp);
		viewPager.setAdapter(new MyAdapter());// 设置填充ViewPager页面的适配器
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
		
		dots = new ArrayList<View>();
		dots.add(parentView.findViewById(R.id.v_dot0));
		dots.add(parentView.findViewById(R.id.v_dot1));
		dots.add(parentView.findViewById(R.id.v_dot2));
	}
	
	private void initData(){
		//提示当前位置
		String locationStr = "";
		if (!CacheHandler.readCache(getActivity(), Constant.APP_NAME, Constant.CITY).equals("")) {
			locationStr = CacheHandler.readCache(getActivity(), Constant.APP_NAME, Constant.CITY);
		}else {
			locationStr = String.format(getActivity().getString(R.string.zixun_hint), LocationUtils.cityName);
		}
		zixun_search.setText(locationStr);
	}
	
	public void updataActionBar(){
		getActivity().getActionBar().setTitle(getResources().getString(R.string.zixun));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.zixun_search:
			startActivity(new Intent(getActivity(),SelectLocationActivity.class));
			getActivity().overridePendingTransition(R.anim.activity_open_bottom_in, R.anim.activity_open_bottom_out);
			break;
		case R.id.zixun_consult:
			startActivity(new Intent(getActivity(),ConsultActivity.class));
			break;
		case R.id.zixun_more_zhuanjia:
			startActivity(new Intent(getActivity(),MoreDoctor.class));
			break;
		default:
			break;
		}
	}
	
	
	/**
	 * 填充ViewPager页面的适配器
	 * 
	 * @author Administrator
	 * 
	 */
	private class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mViews.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(mViews.get(arg1));
			return mViews.get(arg1);
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView((View) arg2);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}	
	}
	
	//更新数据
	@Override
	public void onResume() {
		super.onResume();
		if (zixun_search != null) {
			initData();
		}
	}
}
