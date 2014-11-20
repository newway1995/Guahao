package module.activity.guahao;

import module.activity.R;
import module.adapter.LocationAdapter;
import module.adapter.LocationSubAdapter;
import module.view.LocationListView;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * 上午2:03:33
 * 位置选择
 */
public class SelectLocationActivity extends Activity {
	
	private LocationListView provincelList;
	private LocationListView cityList;
	private LocationAdapter provinceAdapter;
	private LocationSubAdapter cityAdapter;
	
	private String provinces[] = new String[]{"山东","江苏","安徽","浙江","福建","上海","广东","广西","海南","湖北","湖南","河南","江西","北京","天津","河北","山西","内蒙古","宁夏","新疆","青海","陕西","甘肃","四川","云南","贵州","西藏","重庆","辽宁","吉林","黑龙江","台湾","香港","澳门"};
	private String cityes[][] = new String[][]{
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_location_select);
		super.onCreate(savedInstanceState);	
		initView();
		initData();
	}

	private void initView(){
		getActionBar().setDisplayHomeAsUpEnabled(true);
		provincelList = (LocationListView)findViewById(R.id.location_province_listView);
		cityList = (LocationListView)findViewById(R.id.location_city_listView);
		provinceAdapter = new LocationAdapter(this, provinces);
		cityAdapter = new LocationSubAdapter(this, cityes[0]);
		provincelList.setAdapter(provinceAdapter);
		cityList.setAdapter(cityAdapter);
		
		provincelList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(getApplicationContext(), "Selection Position = "+arg2, Toast.LENGTH_SHORT).show();
				cityAdapter = new LocationSubAdapter(getApplicationContext(), cityes[arg2]);
				cityList.setAdapter(cityAdapter);
			}
		});
		
		cityList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(getApplicationContext(), "Selection Position = "+arg2, Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	private void initData(){
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			overridePendingTransition(R.anim.activity_exist_bottom_in, R.anim.activity_exist_bottom_out);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
