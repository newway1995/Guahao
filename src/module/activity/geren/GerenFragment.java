package module.activity.geren;

import common.util.CacheHandler;

import constant.Constant;
import module.activity.LoginActivity;
import module.activity.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * 下午1:13:08
 * 个人
 */
public class GerenFragment extends Fragment implements OnClickListener{
	private final String TAG = "GerenFragment"; 
	
	private LinearLayout geren_all;
	private LinearLayout geren_my_order;
	private LinearLayout geren_my_zixun;
	private LinearLayout geren_my_bingli;
	private LinearLayout geren_guanzhu;
	private LinearLayout geren_setting;
	
	/**
	 * 父视图
	 * */
	private View parentView;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.fragment_geren, null);
		Log.d(TAG, "GerenFragment ----- onCreateView");
		isLogin(parentView);
		return parentView;
	}
	
	
	
	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "GerenFragment ----- onResume");
		initView(parentView);
		initData();
	}

	@Override
	public void onStop(){
		super.onStop();
		Log.d(TAG, "GerenFragment ----- onStop");
	}
	
	/**
	 * 初始化数据 
	 * */
	private void initView(View parentView){
		updataActionBar();
		geren_all = (LinearLayout)parentView.findViewById(R.id.geren_all);
		geren_my_order = (LinearLayout)parentView.findViewById(R.id.geren_my_order);
		geren_my_zixun = (LinearLayout)parentView.findViewById(R.id.geren_my_zixun);
		geren_my_bingli = (LinearLayout)parentView.findViewById(R.id.geren_my_bingli);
		geren_guanzhu = (LinearLayout)parentView.findViewById(R.id.geren_guanzhu);
		geren_setting = (LinearLayout)parentView.findViewById(R.id.geren_setting);
		
		geren_all.setOnClickListener(this);
		geren_my_order.setOnClickListener(this);
		geren_my_bingli.setOnClickListener(this);
		geren_my_zixun.setOnClickListener(this);
		geren_guanzhu.setOnClickListener(this);
		geren_setting.setOnClickListener(this);
	}
	
	/**
	 * 初始化视图
	 * */
	private void initData(){}
	
	/**
	 * 判断是否登录,若是：则不变;若否：则跳转到Login
	 * 
	 * */
	private void isLogin(View parentView){
		if (CacheHandler.readCache(getActivity().getApplicationContext(), Constant.USER_INFO, Constant.IS_LOGIN).equals(Constant.LOGGED)) {//如果是已经登录,则不处理
			initView(parentView);
			initData();	
		}else {
			startActivity(new Intent(getActivity(),LoginActivity.class));
		}
	}
	
	public void updataActionBar(){
		getActivity().getActionBar().setTitle(getResources().getString(R.string.geren_center));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.geren_all:
			//startActivity(new Intent(getActivity(),UserInfoActivity.class));
			startActivity(new Intent(getActivity(),LoginActivity.class));
			break;
		case R.id.geren_my_order:
			startActivity(new Intent(getActivity(),OrderActivity.class));
			break;
		case R.id.geren_my_bingli:
			startActivity(new Intent(getActivity(),BingliActivity.class));
			break;
		case R.id.geren_my_zixun:
			startActivity(new Intent(getActivity(),ZixunActivity.class));
			break;
		case R.id.geren_guanzhu:
			startActivity(new Intent(getActivity(),GuanzhuActivity.class));
			break;
		case R.id.geren_setting:
			startActivity(new Intent(getActivity(),SettingActivity.class));
			break;
		default:
			break;
		}
	}
}
