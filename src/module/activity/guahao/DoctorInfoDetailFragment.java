package module.activity.guahao;

import module.activity.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:DoctorInfoDetailFragment.java
 * @Package:module.activity.guahao
 * @time:下午4:56:15 2014-12-7
 * @useage:医生详细信息
 */
public class DoctorInfoDetailFragment extends Fragment implements OnClickListener{

	private TextView descText;//医院简介 
	private TextView jobText;//医生描述
	private boolean isJobShowing = false;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View parentView = inflater.inflate(R.layout.fragment_doctorinfo_detail, null);
		initView(parentView);
		return parentView;
	}
	
	/**
	 * 初始化视图
	 * */
	private void initView(View parentView){
		descText = (TextView)parentView.findViewById(R.id.doctorinfo_detail_desc);
		jobText = (TextView)parentView.findViewById(R.id.doctorinfo_detail_job);
		descText.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.doctorinfo_detail_desc) {
			if (isJobShowing) 
				jobText.setText(getString(R.string.hospital_desc) + "\n简介在显示");
			else 
				jobText.setText(getString(R.string.hospital_desc));			
			isJobShowing = !isJobShowing;
		}
	}
}	
