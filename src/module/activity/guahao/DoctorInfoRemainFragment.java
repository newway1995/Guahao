package module.activity.guahao;

import java.util.ArrayList;
import java.util.HashMap;

import common.util.CacheHandler;
import constant.Constant;

import module.activity.R;
import module.view.RefreshLayout;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:DoctorInfoRemainFragment.java
 * @Package:module.activity.guahao
 * @time:下午4:55:50 2014-12-7
 * @useage:在医生界面实现挂号,显示医生挂号现在剩余的票数
 */
public class DoctorInfoRemainFragment extends Fragment implements OnClickListener,SwipeRefreshLayout.OnRefreshListener{
	private String TAG ="DoctorInfoRemainFragment";
	private TextView ruleView;//显示规则
	private boolean isRuleShowing = false;
	private RefreshLayout swipeRefreshLayout;//listView
	private ListView mListView;
	private SimpleAdapter adapter;//list的Adapter
	private String from[];//adapter from
	private int to[];//adapter to
	private ArrayList<HashMap<String, String >> list = new ArrayList<HashMap<String,String>>();//adapter data
	private String ticket_num ;
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
		from = new String[]{"time","fee","rest_ticket"};
		to = new int[]{R.id.item_doctorinfo_remain_time,R.id.item_doctorinfo_remain_fee,R.id.item_doctorinfo_remain_rest_ticket};//时间、挂号费、剩余票数
		asyncGetData();
		adapter = new SimpleAdapter(getActivity().getApplicationContext(), list, R.layout.item_doctorinfo_remain, from, to);				
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(Integer.parseInt(list.get(0).get("ticket_num"))!=0&&Integer.parseInt(list.get(0).get("ticket_num"))!=0)
				{
					Constant.saveOrder(getActivity(), list.get(position));
					startActivity(new Intent(getActivity(),GuahaoActivity.class));
				}else
				{
					Toast.makeText(getActivity(), "票数不足，无法预约",Toast.LENGTH_SHORT).show() ;
				}
				
			}
		});
		mListView.setAdapter(adapter);
	}
	
	private void asyncGetData(){
		ticket_num = CacheHandler.readCache(getActivity(), Constant.USER_INFO, Constant.USER_DOCTOR_TICKET);
		int Monday = Integer.parseInt(ticket_num)%100;//低两位保存周一的挂号信息
		int Friday = Integer.parseInt(ticket_num)/100;//高两位保存周五的挂号信息
		if (!list.isEmpty()) {//重置
			list = new ArrayList<HashMap<String,String>>();
		}
		
			HashMap<String, String> map1 = new HashMap<String, String>() ;
			map1.put("time", Constant.MON);
			map1.put("time_choice","1");//1表示周一，5表示周五
			map1.put("fee", "专家门诊 14.00元");
			map1.put("kind","1");//1表示专家号，2表示其他
			map1.put("rest_ticket", "剩余票数" +Monday);
			map1.put("ticket_num",Monday+"");
			list.add(map1);
			HashMap<String, String> map2 = new HashMap<String, String>() ;
			map2.put("time", Constant.FRI);
			map2.put("fee", "专家门诊 14.00元");
			map2.put("time_choice","5");
			map2.put("kind","1");
			map2.put("rest_ticket","剩余票数" + Friday);
			map2.put("ticket_num",Monday+"");
			list.add(map2);
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
