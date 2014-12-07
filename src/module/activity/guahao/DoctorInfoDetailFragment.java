package module.activity.guahao;

import module.activity.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:DoctorInfoDetailFragment.java
 * @Package:module.activity.guahao
 * @time:下午4:56:15 2014-12-7
 * @useage:医生详细信息
 */
public class DoctorInfoDetailFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View parentView = inflater.inflate(R.layout.fragment_doctorinfo_detail, null);
		return parentView;
	}
}	
