package module.activity.guahao;

import constant.Constant;
import module.activity.R;
import module.entity.Doctor;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:DoctorInfoDetailFragment.java
 * @Package:module.activity.guahao
 * @time:下午4:56:15 2014-12-7
 * @useage:医生详细信息
 */
public class DoctorInfoDetailFragment extends Fragment{

	private TextView descText;//医院简介 
	private TextView jobText;//医生描述
	private Doctor doctor;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View parentView = inflater.inflate(R.layout.fragment_doctorinfo_detail, null);
		initView(parentView);
		initData();
		return parentView;
	}
	
	/**
	 * 初始化视图
	 * */
	private void initView(View parentView){
		descText = (TextView)parentView.findViewById(R.id.doctorinfo_detail_desc);
		jobText = (TextView)parentView.findViewById(R.id.doctorinfo_detail_job);
	}
	/**
	 * 初始化数据
	 * */
	private void initData(){
		doctor = Constant.getDoctor(getActivity());
		jobText.setText(doctor.getLevel().equals("0") ? "主治医师" : "专家");
		descText.setText(doctor.getAdept());
	}
}	
