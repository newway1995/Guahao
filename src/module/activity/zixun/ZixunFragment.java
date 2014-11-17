package module.activity.zixun;

import module.activity.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * @author niuwei
 * @email nniuwei@163.com
 * 下午1:14:52
 * 咨询
 */
public class ZixunFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View parentView = inflater.inflate(R.layout.fragment_zixun, null);
		initView(parentView);
		initData();
		return parentView;
	}
	
	private void initView(View parentView){
		updataActionBar();
	}
	
	private void initData(){
		
	}
	
	public void updataActionBar(){
		getActivity().getActionBar().setTitle(getResources().getString(R.string.zixun));
	}
	
}
