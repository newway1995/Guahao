package module.activity.guahao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import module.activity.R;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * 下午2:07:57
 * 右侧的fragment
 */
public class FenZhenRightFragment extends Fragment{
	private final static String TAG = "FenZhenRightFragment"; 
	
	private ListView listView;
	private SimpleAdapter simpleAdapter;
	private List<HashMap<String, String>> data;
	private String from[];
	private int to[];
	private Resources res;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView");
		View parentView = inflater.inflate(R.layout.list_fenzhen_right, null);
		initView(parentView);
		initData();
		return parentView;
	}
	
	private void initView(View parentView){
		listView = (ListView)parentView.findViewById(R.id.list_fenzhen_right_list);
		from = new String[]{"item_fenzhen_right_text"};
		to = new int[]{R.id.item_fenzhen_right_text};
		res = getActivity().getResources();
	}
	
	private void setData(){
		data = new ArrayList<HashMap<String,String>>();
		String []body_list = res.getStringArray(R.array.body_list);
		for (int i = 0; i < body_list.length; i++) {
			HashMap<String , String> map = new HashMap<String, String>();
			map.put("item_fenzhen_right_text", body_list[i]);
			data.add(map);
		}
	}
	
	private void initData(){
		setData();
		simpleAdapter = new SimpleAdapter(getActivity(), data, R.layout.item_fenzhen_right_text, from, to);
		listView.setAdapter(simpleAdapter);
		//设置点击监听事件
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(getActivity(), "Position = "+arg2, Toast.LENGTH_SHORT).show();
				switch (arg2) {
				case 0:
					
					break;

				default:
					break;
				}
			}
		});
	}	
}
