package module.activity.geren;

import module.activity.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * 下午1:13:08
 * 个人
 */
public class GerenFragment extends Fragment implements OnClickListener{

	private LinearLayout geren_all;
	private LinearLayout geren_my_order;
	private LinearLayout geren_my_zixun;
	private LinearLayout geren_my_bingli;
	private LinearLayout geren_guanzhu;
	private LinearLayout geren_setting;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View parentView = inflater.inflate(R.layout.fragment_geren, null);
		initView(parentView);
		initData();
		return parentView;
	}
	
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
	
	private void initData(){
		
	}
	
	public void updataActionBar(){
		getActivity().getActionBar().setTitle(getResources().getString(R.string.geren_center));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.geren_all:
			Toast.makeText(getActivity(), "名医吃饭去了,有事请拨110", Toast.LENGTH_SHORT).show();
			break;
		case R.id.geren_my_order:
			startActivity(new Intent(getActivity(),OrderActivity.class));
			break;
		case R.id.geren_my_bingli:
			Toast.makeText(getActivity(), "名医吃饭去了,有事请拨110", Toast.LENGTH_SHORT).show();
			break;
		case R.id.geren_my_zixun:
			Toast.makeText(getActivity(), "名医吃饭去了,有事请拨110", Toast.LENGTH_SHORT).show();
			break;
		case R.id.geren_guanzhu:
			Toast.makeText(getActivity(), "名医吃饭去了,有事请拨110", Toast.LENGTH_SHORT).show();
			break;
		case R.id.geren_setting:
			startActivity(new Intent(getActivity(),SettingActivity.class));
			break;
		default:
			break;
		}
	}
}
