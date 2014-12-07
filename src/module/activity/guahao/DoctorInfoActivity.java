package module.activity.guahao;

import common.core.BaseLoginActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;
import module.activity.LoginActivity;
import module.activity.R;
import module.view.SegmentedGroup;
import android.widget.RadioGroup;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:DoctorInfoActivity.java
 * @Package:module.activity.guahao
 * @time:下午2:59:38 2014-12-6
 * @useage:医生详细信息,病人挂号界面,显示残余号码
 */
public class DoctorInfoActivity extends BaseLoginActivity implements RadioGroup.OnCheckedChangeListener{
	
	private SegmentedGroup segmentedGroup;//控件
	private FragmentManager fragmentManager;//管理Fragment
	private DoctorInfoDetailFragment detailFragment;//显示医生的详细信息
	private DoctorInfoRemainFragment remainFragment;//显示剩余票数
	
	@Override
	protected void setRootView() {
		setContentView(R.layout.activity_doctor_info);
		super.setRootView();
	}

	@Override
	protected void initData() {//初始化数据
		super.initData();		
		fragmentManager = getFragmentManager();			
		segmentedGroup.check(R.id.doctor_info_doctor_detail);//初始化选中事件
	}
	
	@Override
	protected void initView() {//初始化视图
		super.initView();
		segmentedGroup = (SegmentedGroup)findViewById(R.id.doctor_info_segment);
		segmentedGroup.setOnCheckedChangeListener(this);		
	}
	
	@Override
	protected void skip() {//跳转activity
		showActivity(DoctorInfoActivity.this, LoginActivity.class);
		super.skip();
	}

	@Override
	public void widgetClick(View v) {//点击事件
		super.widgetClick(v);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {		
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		hiddenFragment(transaction);//先隐藏再显示
		if (checkedId == R.id.doctor_info_doctor_detail) {//显示医生详细信息			
			if (detailFragment == null) {//没有初始化
				detailFragment = new DoctorInfoDetailFragment();
				transaction.add(R.id.doctor_info_container, detailFragment);
			}else {
				transaction.show(detailFragment);
			}
		}else if (checkedId == R.id.doctor_info_doctor_remain) {//显示医生剩余的票数			
			if (remainFragment == null) {
				remainFragment = new DoctorInfoRemainFragment();
				transaction.add(R.id.doctor_info_container, remainFragment);
			}else {
				transaction.show(remainFragment);
			}		
		}
		transaction.commit();
	}
	
	/**
	 * 隐藏Fragment,防止多个fragment显示的情况
	 * */
	private void hiddenFragment(FragmentTransaction transaction){
		if (detailFragment != null) 
			transaction.hide(detailFragment);
		if(remainFragment != null)
			transaction.hide(remainFragment);
	}
}
