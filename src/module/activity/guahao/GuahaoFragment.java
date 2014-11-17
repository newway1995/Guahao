package module.activity.guahao;

import module.activity.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * @author niuwei
 * @email nniuwei@163.com
 * 上午11:49:04
 *
 */
public class GuahaoFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View parentView = inflater.inflate(R.layout.fragment_guahao, null);
		initView(parentView);
		initData();
		return parentView;
	}
	
	private void initView(View parentView){
		
	}
	
	private void initData(){
		
	}
	
	public void updataActionBar(){
		getActivity().getActionBar().setTitle(getResources().getString(R.string.guahao));
	}
}
