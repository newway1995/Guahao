package module.activity.guahao;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.kymjs.aframe.ui.BindView;
import module.activity.R;
import module.activity.geren.UserInfoActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import common.core.BaseLoginActivity;
import common.util.AsyncInter;
import common.util.CacheHandler;
import common.util.MyAsyncTask;
import constant.AppCode;
import constant.AppNet;
import constant.Constant;


/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:GuahaoActivity.java
 * @Package:module.activity.guahao
 * @time:下午10:02:21 2014-12-18
 * @useage:预约界面
 */
public class GuahaoActivity extends BaseLoginActivity{
	
	@BindView(id = R.id.order_info_hospital_name)
	private TextView hospitalText;//医院名称
	@BindView(id = R.id.order_info_section_name)
	private TextView sectionText;//挂号科室
	@BindView(id = R.id.order_info_doctor_name)
	private TextView doctorlText;//医生姓名
	@BindView(id = R.id.order_info_menzhen_time)
	private TextView timeText;//门诊时间
	@BindView(id = R.id.order_info_guahao_fee)
	private TextView feeText;//挂号费用
	@BindView(id = R.id.order_info_name)
	private TextView userText;//就诊人
	@BindView(id = R.id.order_info_chufuzhen)
	private TextView chufuzhenText;//初复诊
	@BindView(id = R.id.order_info_zhifu)
	private TextView zhifuText;//支付方式
	
	
	@Override
	protected void initData() {
		super.initData();
		hospitalText.setText(CacheHandler.readCache(this, Constant.USER_INFO, Constant.USER_HOSPITAL_NAME));
		sectionText.setText(CacheHandler.readCache(this, Constant.USER_INFO, Constant.USER_SECTION_NAME));
		doctorlText.setText(CacheHandler.readCache(this, Constant.USER_INFO, Constant.USER_DOCTOR_NAME));
		timeText.setText(CacheHandler.readCache(this, Constant.USER_INFO, Constant.USER_ORDER_TIME));
		feeText.setText(CacheHandler.readCache(this, Constant.USER_INFO, Constant.USER_ORDER_FEE));
		userText.setText(CacheHandler.readCache(this, Constant.USER_INFO, Constant.USER_ID));
		zhifuText.setText("去医院支付");
	}
	
	@Override
	protected void initView() {
		super.initView();
	}

	
	@Override
	protected void skip() {
		super.skip();
	}

	
	@Override
	public void widgetClick(View v) {
		super.widgetClick(v);
	}

	@Override
	protected void setRootView() {
		setContentView(R.layout.activity_order_info);
		super.setRootView();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.order_info, menu);
        return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		case R.id.actionbar_order_info_submit:
			submitOrder();
			break;
		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}
	public void submitOrder()
	{
		String temp_ticket_num = CacheHandler.readCache(this, Constant.USER_INFO, Constant.USER_DOCTOR_TICKET);
		final String ticket_num ;
		final String user_id = CacheHandler.readCache(this, Constant.USER_INFO, Constant.USER_ID);	
		final String doctor_id = CacheHandler.readCache(this, Constant.USER_INFO, Constant.USER_DOCTOR_ID);
		final String order_time = CacheHandler.readCache(this, Constant.USER_INFO,Constant.USER_ORDER_TIME);
		final String hospital_id = CacheHandler.readCache(this, Constant.USER_INFO, Constant.USER_HOSPITAL_ID);
		final String sectionn_id = CacheHandler.readCache(this, Constant.USER_INFO, Constant.USER_SECTION_ID);
		final String order_kind = CacheHandler.readCache(this, Constant.USER_INFO,Constant.USER_ORDER_KIND);
		if(order_time.equals("周一上午"))
		{
			ticket_num = (Integer.parseInt(temp_ticket_num) - 1)+"";
		}else
		{
			ticket_num = (Integer.parseInt(temp_ticket_num) - 100)+"";
		}
		AsyncInter inter = new AsyncInter() {
			String result;
			@Override
			public void onPreExecute() {
				
			}
			
			@Override
			public void onPostExecute() {
				// TODO Auto-generated method stub
				try {
					Log.d(TAG, "result = " + result);
					JSONObject jObject = new JSONObject(result);
					int success = Integer.parseInt(jObject.getString("success")); 
					if (success == 1) {
						
						Toast.makeText(GuahaoActivity.this, "预约成功", Toast.LENGTH_LONG).show();
						startActivity(new Intent(GuahaoActivity.this,UserInfoActivity.class));
						overridePendingTransition(R.anim.base_slide_right_in, 0);
					}else {
						Toast.makeText(GuahaoActivity.this, "预约失败", Toast.LENGTH_LONG).show();
					}
				} catch (JSONException e) {				
					e.printStackTrace();
				}				
			}
			
			@Override
			public void interruptTask() {
				 
			}
			
			@Override
			public void doInBackground() {
				// TODO Auto-generated method stub
				Log.d(TAG, "doInBackground");
				ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("action", AppCode.ACTION_GUAHAO));
				params.add(new BasicNameValuePair("doctor_id",doctor_id));
				params.add(new BasicNameValuePair("ticket_num",ticket_num));
				params.add(new BasicNameValuePair("user_id",user_id));
				params.add(new BasicNameValuePair("doctor_id",doctor_id));
				params.add(new BasicNameValuePair("order_time",order_time));
				params.add(new BasicNameValuePair("hospital_id",hospital_id));
				params.add(new BasicNameValuePair("section_id",sectionn_id));
				params.add(new BasicNameValuePair("order_kind",order_kind));		
				result = AppCode.getData(GuahaoActivity.this, params, AppNet.Access.GET);
			}
		};
		new MyAsyncTask(inter, true, GuahaoActivity.this).execute();
	}
	
}
