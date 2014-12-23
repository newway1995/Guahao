package module.activity.guahao;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import common.receiver.NetResultInterface;
import common.util.CacheHandler;
import constant.Constant;

import module.activity.R;
import module.adapter.LocationAdapter;
import module.adapter.LocationSubAdapter;
import module.model.CommonModel;
import module.view.LocationListView;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * 上午2:03:33
 * 位置选择
 */
public class SelectLocationActivity extends Activity implements OnScrollListener{
	private static final String TAG = "SelectLocationActivity";
	private View topView;//需要显示的View 
	private LocationListView provincelList;
	private LocationListView cityList;
	private LocationAdapter provinceAdapter;
	private LocationSubAdapter cityAdapter;
	private boolean topIsShowing = false;
	private int statusBarHeight;//状态栏高度
	private int actionBarHeight;//actionbar高度
	/**
	 * 手机屏幕高度
	 * */
	private int screenHeight;
	/** 
     * 手机屏幕宽度 
     */  
    private int screenWidth; 
    /** 
     * 悬浮框的参数 
     */  
    private static WindowManager.LayoutParams suspendLayoutParams;      
       
    private WindowManager mWindowManager;  
    private View view;//topView->imagevIew
    private TextView textView;//topview->text
	private int currentProvince = 0;
	
	//"山东","江苏","安徽","浙江","福建","上海","广东","广西","海南","湖北","湖南","河南","江西","北京","天津","河北","山西","内蒙古","宁夏","新疆","青海","陕西","甘肃","四川","云南","贵州","西藏","重庆","辽宁","吉林","黑龙江","台湾","香港","澳门"
	private String provinces[] = new String[]{"山东","江苏","安徽","浙江","福建","上海","广东","广西","海南","湖北","湖南","河南","江西","北京","天津","河北","山西","内蒙古","宁夏","新疆","青海","陕西","甘肃","四川","云南","贵州","西藏","重庆","辽宁","吉林","黑龙江","台湾","香港","澳门"};
	private String cityes[][] = new String[][]{
			new String[]{"北京","上海","天津","重庆"},
			new String[]{"阿拉善盟","巴彦淖尔","包头","赤峰","鄂尔多斯","呼和浩特","呼伦贝尔","通辽","乌海","乌兰察布","锡林郭勒","兴安盟"},
			new String[]{"固原","石嘴山","吴忠","银川","中卫"},
			new String[]{},
			new String[]{"福建","济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"上海","济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"广东","济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"广西","济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"海南","济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"湖北","济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"湖南","济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"河南","济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"江西","济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"北京","济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"添加","济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"河北","济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"山西","济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"内蒙古","济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"宁夏","济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"陕西","济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"甘肃","济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"云南","济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"贵州","济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"西藏","济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"重庆","济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"辽宁","济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"吉林","济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"黑龙江","济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"台湾","济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"香港","济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
			new String[]{"澳门","济南市 ","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","滨州市","德州市","聊城市","临沂市","莱芜市","菏泽市"},
	};
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_location_select);
		super.onCreate(savedInstanceState);	
		mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);  
        screenWidth = mWindowManager.getDefaultDisplay().getWidth(); 
        screenHeight = mWindowManager.getDefaultDisplay().getHeight();
        //asyncGetPro();//获取省份信息
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
				Log.d(TAG, "----------click position = " + arg2);
				provinceAdapter.setSelectedPosition(arg2);
				provinceAdapter.notifyDataSetChanged();
				currentProvince = arg2;
				cityAdapter = new LocationSubAdapter(getApplicationContext(), cityes[arg2]);				
				cityList.setAdapter(cityAdapter);
				textView.setText(provinces[currentProvince]);
			}
		});
		
		cityList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(getApplicationContext(), "Selection Item = " + cityes[currentProvince][arg2], Toast.LENGTH_SHORT).show();
				CacheHandler.writeCache(SelectLocationActivity.this, Constant.APP_NAME, Constant.PROVINCE, provinces[currentProvince]);
				CacheHandler.writeCache(SelectLocationActivity.this, Constant.APP_NAME, Constant.CITY, cityes[currentProvince][arg2]);
			}
		});
	}
	
	
	private void initData(){
		provincelList.setOnScrollListener(this);
	}
	
	//计算状态栏、actionbar的高度,当准备完毕的时候加载第一个topView
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        statusBarHeight = frame.top;
        actionBarHeight = getActionBar().getHeight(); 
        Log.d(TAG, "actionBar.height = " + actionBarHeight);
        Log.d(TAG, "statusBar.height = " + statusBarHeight);
        addTopView();
	}
	
	/**
	 * 获取省份
	 * */
	@SuppressWarnings("unused")
	private void asyncGetPro(){
		CommonModel.getInstance().getProvince(SelectLocationActivity.this, new NetResultInterface() {
			
			@Override
			public void parseResult(String result) {
				try {
					JSONObject jObject = new JSONObject(result);
					JSONArray jArray = jObject.getJSONArray("province");
					for (int i = 0; i < jArray.length(); i++) {
						JSONObject jsonObject = jArray.getJSONObject(i);
						provinces[i] = jsonObject.getString("name");
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}				
			}
		});
	}
	
	/**
	 * 获取城市
	 * */
	@SuppressWarnings("unused")
	private void asyncGetCity(int pro_id){
		CommonModel.getInstance().getCityByProID(SelectLocationActivity.this, pro_id, new NetResultInterface() {
			
			@Override
			public void parseResult(String result) {				
				Log.d(TAG,"city = " + result);
			}
		});
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


	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {

		if (firstVisibleItem >= currentProvince) {
			if (!topIsShowing) {
				Log.d(TAG, "----------addTopView");
				addTopView();
			}
		}else if(firstVisibleItem < currentProvince && currentProvince < firstVisibleItem + visibleItemCount - 1) {
			if (topIsShowing) {
				Log.d(TAG, "----------clearTopView");
				clearTopView();
			}
		}else {
			if (!topIsShowing) {
				Log.d(TAG, "----------addBottomView");
				addBottomView();
			}
		}
	}
	
	/**
	 * 在listView上面添加view
	 * */
	private void addTopView(){     
		if (topView == null && actionBarHeight != 0) {
			topView = LayoutInflater.from(this).inflate(R.layout.item_selecte_location, null);
			topView.setBackgroundColor(Color.WHITE);
			view = (View)topView.findViewById(R.id.location_icon);
			textView = (TextView)topView.findViewById(R.id.location_item);
			textView.setText(provinces[currentProvince]);
			view.setVisibility(View.VISIBLE);
			if(suspendLayoutParams == null){  
				suspendLayoutParams = new LayoutParams();  
	            suspendLayoutParams.type = LayoutParams.TYPE_PHONE; //悬浮窗的类型，一般设为2002，表示在所有应用程序之上，但在状态栏之下   
	            suspendLayoutParams.format = PixelFormat.RGBA_8888;   
	            suspendLayoutParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL    
	                     | LayoutParams.FLAG_NOT_FOCUSABLE;  //悬浮窗的行为，比如说不可聚焦，非模态对话框等等   
	            suspendLayoutParams.gravity = Gravity.TOP | Gravity.LEFT;  //悬浮窗的对齐方式  
	            suspendLayoutParams.width = screenWidth / 2;  
	            suspendLayoutParams.height = LayoutParams.WRAP_CONTENT;    
	            suspendLayoutParams.x = 0;  //悬浮窗X的位置  
	            suspendLayoutParams.y = actionBarHeight; 
			}
			mWindowManager.addView(topView, suspendLayoutParams);
			topIsShowing = true;
		}else {
			clearTopView();
		}
	}
	
	/**
	 * 在listView上面添加bottom view
	 * */
	private void addBottomView(){     
		if (topView == null && actionBarHeight != 0) {
			topView = LayoutInflater.from(this).inflate(R.layout.item_selecte_location, null);
			topView.setBackgroundColor(Color.WHITE);
			view = (View)topView.findViewById(R.id.location_icon);
			textView = (TextView)topView.findViewById(R.id.location_item);
			textView.setText(provinces[currentProvince]);
			view.setVisibility(View.VISIBLE);
			if(suspendLayoutParams == null){  
				suspendLayoutParams = new LayoutParams();  
	            suspendLayoutParams.type = LayoutParams.TYPE_PHONE; //悬浮窗的类型，一般设为2002，表示在所有应用程序之上，但在状态栏之下   
	            suspendLayoutParams.format = PixelFormat.RGBA_8888;   
	            suspendLayoutParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL    
	                     | LayoutParams.FLAG_NOT_FOCUSABLE;  //悬浮窗的行为，比如说不可聚焦，非模态对话框等等   
	            suspendLayoutParams.gravity = Gravity.TOP | Gravity.LEFT;  //悬浮窗的对齐方式  
	            suspendLayoutParams.width = screenWidth / 2;  
	            suspendLayoutParams.height = LayoutParams.WRAP_CONTENT;    
	            suspendLayoutParams.x = 0;  //悬浮窗X的位置  
	            suspendLayoutParams.y = screenHeight - topView.getHeight(); //设置位置
			}
			mWindowManager.addView(topView, suspendLayoutParams);
			topIsShowing = true;
		}else {
			clearTopView();
		}
	}
	
	/**
	 * 在listView上面移除view
	 * */
	private void clearTopView(){
		if(topView != null){  			
            mWindowManager.removeView(topView);  
            topView = null;
            suspendLayoutParams = null;
            topIsShowing = false;
        }  
	}
	
	@Override
	protected void onStop() {
		finish();
		super.onStop();
	}
		
}
