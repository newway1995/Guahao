package module.activity.guahao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import constant.Constant;

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
	
	private ListView listView;//listView
	private SimpleAdapter simpleAdapter;//ListView Adapter
	private List<HashMap<String, String>> data;//Bind data
	private String from[];
	private int to[];
	private Resources res;
	
	public static boolean firstPosition = true;
	public static int first = -1;
	
	
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
	
	
	private void initData(){
		setList();
		//设置点击监听事件
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long arg3) {
				if (firstPosition) {//第一次跳转之后的列表
					setList(position);					
				}else {//第二次跳转之后
					//跳出一个弹出提示框
					Toast.makeText(getActivity(), Constant.getSickSolution(first, position, res), Toast.LENGTH_LONG).show();
				}
			}
		});
	}	
	
	//设置list中得data数据
	private void setListData(String[] strs){
		data = new ArrayList<HashMap<String,String>>();
		for (int i = 0; i < strs.length; i++) {
			HashMap<String , String> map = new HashMap<String, String>();
			map.put("item_fenzhen_right_text", strs[i]);
			data.add(map);
		}
	}
	//设置list第二个列表(具体哪一方面疾病的列表) 可以提供外部引用
	public void setList(int position){
		String []strs = Constant.getSickList(position,res);
		setListData(strs);
		simpleAdapter = new SimpleAdapter(getActivity(), data, R.layout.item_fenzhen_right_text, from, to);
		listView.setAdapter(simpleAdapter);
		firstPosition = false;//准备第二次跳转使用
		first = position;
	}
	
	//初始化设置list第一个列表(所有疾病的列表) 外部引用
	public void setList(){
		firstPosition = true;//状态改变之后重置
		String[] strs = Constant.getBodyList(res);
		setListData(strs);
		simpleAdapter = new SimpleAdapter(getActivity(), data, R.layout.item_fenzhen_right_text, from, to);
		listView.setAdapter(simpleAdapter);
	}
	
	//外部引用 返回当前是第几个列表
	public int getCurrentList(){
		return (firstPosition == true) ? 0:1;
	}
	
}
