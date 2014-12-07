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
 * @ClassName:DoctorInfoRemainFragment.java
 * @Package:module.activity.guahao
 * @time:下午4:55:50 2014-12-7
 * @useage:在医生界面实现挂号,显示医生挂号现在剩余的票数
 */
public class DoctorInfoRemainFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View parentView = inflater.inflate(R.layout.fragment_doctorinfo_remain, null);
		return parentView;
	}
}
