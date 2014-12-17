package module.activity.guahao;

import common.util.CacheHandler;
import common.util.LocationUtils;
import constant.Constant;

import module.activity.R;
import module.activity.geren.GuanzhuActivity;
import module.activity.geren.OrderActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * @author niuwei
 * @email nniuwei@163.com
 * 上午11:49:04
 *
 */
public class GuahaoFragment extends Fragment implements View.OnClickListener{

	private final static String TAG = "GuahaoFragment";
	private TextView guahao_search;
	private TextView main_fenzhen;
	private TextView main_attention;
	private TextView main_order;
	private RelativeLayout guahao_choose_hospital;
	private RelativeLayout guahao_choose_dept;
	private TextView guahao_choose_hospital_text;//医院text
	private TextView guahao_choose_dept_text;//医院科室text
	private Button guahao_go_guahao;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView");
		View parentView = inflater.inflate(R.layout.fragment_guahao, null);
		initView(parentView);
		initData();
		return parentView;
	}
	
	private void initView(View parentView){
		guahao_search = (TextView)parentView.findViewById(R.id.guahao_search);
		main_fenzhen = (TextView)parentView.findViewById(R.id.main_fenzhen);
		main_attention = (TextView)parentView.findViewById(R.id.main_attention);
		main_order = (TextView)parentView.findViewById(R.id.main_order);
		guahao_choose_hospital = (RelativeLayout)parentView.findViewById(R.id.guahao_choose_hospital);
		guahao_choose_dept = (RelativeLayout)parentView.findViewById(R.id.guahao_choose_dept);
		guahao_go_guahao = (Button)parentView.findViewById(R.id.guahao_go_guahao);
		guahao_choose_hospital_text = (TextView)parentView.findViewById(R.id.guahao_choose_hospital_text);
		guahao_choose_dept_text = (TextView)parentView.findViewById(R.id.guahao_choose_dept_text);
		
		guahao_search.setOnClickListener(this);
		main_fenzhen.setOnClickListener(this);
		main_attention.setOnClickListener(this);
		main_order.setOnClickListener(this);
		guahao_choose_hospital.setOnClickListener(this);
		guahao_choose_dept.setOnClickListener(this);
		guahao_go_guahao.setOnClickListener(this);
	}
	
	private void initData(){
		//提示当前位置	
		String locationStr = "";
		if (!CacheHandler.readCache(getActivity(), Constant.APP_NAME, Constant.CITY).equals("")) {
			locationStr = CacheHandler.readCache(getActivity(), Constant.APP_NAME, Constant.CITY);
		}else {
			locationStr = String.format(getActivity().getString(R.string.zixun_hint), LocationUtils.cityName);
		}
		guahao_search.setText(locationStr);
	}
	
	public void updataActionBar(){
		getActivity().getActionBar().setTitle(getResources().getString(R.string.guahao));
	}
	
	/**
	 * 当用户选择完医院和科室之后需要更新界面
	 * */
	public void updateHospitalAndSection(){
		String h_name = CacheHandler.readCache(getActivity(), Constant.USER_INFO, Constant.USER_HOSPITAL_NAME);
		String s_name = CacheHandler.readCache(getActivity(), Constant.USER_INFO, Constant.USER_SECTION_NAME);
		
		if (!h_name.equals("")) {
			guahao_choose_hospital_text.setTextColor(getResources().getColor(R.color.red_dark));
			guahao_choose_hospital_text.setText(h_name);
			if (!s_name.equals("")) {
				guahao_choose_dept_text.setTextColor(getResources().getColor(R.color.red_dark));
				guahao_choose_dept_text.setText(s_name);
			}
		}				
	} 
	

	@Override
	public void onStart() {
		super.onStart();
		updateHospitalAndSection();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_fenzhen:
			startActivity(new Intent(getActivity(),FenZhenActivity.class));
			break;
		case R.id.main_attention:
			startActivity(new Intent(getActivity(),GuanzhuActivity.class));
			break;
		case R.id.main_order:
			startActivity(new Intent(getActivity(),OrderActivity.class));
			break;
		case R.id.guahao_choose_hospital:
			startActivity(new Intent(getActivity(),SelectHospitalActivity.class));
			break;
		case R.id.guahao_choose_dept:
			if (!CacheHandler.readCache(getActivity(), Constant.USER_INFO, Constant.USER_HOSPITAL_NAME).equals("")) {
				startActivity(new Intent(getActivity(),SelectSectionActivity.class));
			}else {
				Toast.makeText(getActivity(), "请先选择医院", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.guahao_go_guahao:
			startActivity(new Intent(getActivity(),DoctorInfoActivity.class));
			break;
		case R.id.guahao_search:
			startActivity(new Intent(getActivity(),SelectLocationActivity.class));
			getActivity().overridePendingTransition(R.anim.activity_open_bottom_in, R.anim.activity_open_bottom_out);
			break;

		default:
			break;
		}
	}
	
	//更新数据
	@Override
	public void onResume() {
		super.onResume();
		if (guahao_search != null) {
			initData();
		}
	}
}
