package module.activity.guahao;

import java.util.ArrayList;
import java.util.HashMap;

import module.activity.R;
import module.view.RefreshLayout;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:DoctorInfoRemainFragment.java
 * @Package:module.activity.guahao
 * @time:下午4:55:50 2014-12-7
 * @useage:在医生界面实现挂号,显示医生挂号现在剩余的票数
 */
public class DoctorInfoRemainFragment extends Fragment implements OnClickListener,SwipeRefreshLayout.OnRefreshListener{
	
	private TextView ruleView;//显示规则
	private boolean isRuleShowing = false;
	private RefreshLayout swipeRefreshLayout;//listView
	private ListView mListView;
	private SimpleAdapter adapter;//list的Adapter
	private String from[];//adapter from
	private int to[];//adapter to
	private ArrayList<HashMap<String, String >> list = new ArrayList<HashMap<String,String>>();//adapter data
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View parentView = inflater.inflate(R.layout.fragment_doctorinfo_remain, null);
		initView(parentView);
		initData();
		return parentView;
	}
	
	private void initData(){
		swipeRefreshLayout.setOnRefreshListener(this);
		swipeRefreshLayout.setColorScheme(android.R.color.holo_green_dark, android.R.color.holo_green_light,
				android.R.color.holo_orange_light, android.R.color.holo_red_light);
		from = new String[]{"time","fee"};
		to = new int[]{R.id.item_doctorinfo_remain_time,R.id.item_doctorinfo_remain_fee};//时间、挂号费
		asyncGetData();
		adapter = new SimpleAdapter(getActivity().getApplicationContext(), list, R.layout.item_doctorinfo_remain, from, to);				
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				startActivity(new Intent(getActivity(),GuahaoActivity.class));
			}
		});
		mListView.setAdapter(adapter);
	}
	
	private void asyncGetData(){
		if (!list.isEmpty()) {//重置
			list = new ArrayList<HashMap<String,String>>();
		}
		for (int i = 0; i < 5; i++) {
			HashMap<String, String> map = new HashMap<String, String>() ;
			map.put("time", "2014-12-15 周一上午");
			map.put("fee", "专家门诊 14.00元");
			list.add(map);
		}		
	}
	
	/**
	 * 初始化视图
	 * */
	private void initView(View parentView){
		ruleView = (TextView)parentView.findViewById(R.id.doctorinfo_rule);
		ruleView.setOnClickListener(this);
		swipeRefreshLayout = (RefreshLayout)parentView.findViewById(R.id.mingyi_list);
		mListView = (ListView)parentView.findViewById(R.id.mingyi_listview);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.doctorinfo_rule:// 显示预约规则
			if (!isRuleShowing) //如果当前没有显示,则显示
				ruleView.setText(getString(R.string.rule) + getString(R.string.yiyuan_rule));
			else 
				ruleView.setText(getString(R.string.rule));
			isRuleShowing = !isRuleShowing;
			break;

		default:
			break;
		}
	}

	@Override
	public void onRefresh() {
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				swipeRefreshLayout.setRefreshing(false);
				asyncGetData();
			}
		}, 1000);
	}
}
