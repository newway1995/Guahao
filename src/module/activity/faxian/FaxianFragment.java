package module.activity.faxian;

import module.activity.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * 上午11:54:27
 * 发现
 */
public class FaxianFragment extends Fragment implements OnClickListener{

	@Override
	public void onResume() {
		initView(parentView);
		initData();
		super.onResume();
	}

	private RelativeLayout mingyizaixian;
	private RelativeLayout yishengdongtai;
	private RelativeLayout jibing;
	private View parentView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.fragment_faxian, null);
		initView(parentView);
		initData();
		return parentView;
	}
	
	
	
	private void initView(View parentView){
		mingyizaixian = (RelativeLayout)parentView.findViewById(R.id.faxian_mingyizaixian);
		yishengdongtai = (RelativeLayout)parentView.findViewById(R.id.faxian_yisheng_dynamic);
		jibing = (RelativeLayout)parentView.findViewById(R.id.faxian_jibing);
		
		mingyizaixian.setOnClickListener(this);
		yishengdongtai.setOnClickListener(this);
		jibing.setOnClickListener(this);
	}
	
	private void initData(){
		
	}
	
	public void updataActionBar(){
		getActivity().getActionBar().setTitle(getResources().getString(R.string.faxian));
	}
	
	/**
	 * 点击事件
	 * */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.faxian_mingyizaixian:
			startActivity(new Intent(getActivity(),MingyiActivity.class));
			break;
		case R.id.faxian_yisheng_dynamic:
			startActivity(new Intent(getActivity(),YishengDynamic.class));
			break;
		case R.id.faxian_jibing:
			startActivity(new Intent(getActivity(),JBActivity.class));
			break;
		default:
			break;
		}		
	}
}
