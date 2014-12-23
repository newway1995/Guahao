package module.activity.geren;

import org.kymjs.aframe.ui.BindView;

import common.util.CacheHandler;

import constant.Constant;
import module.activity.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * 下午1:20:39
 * 我的预约单
 */
public class MyOrderActivity extends Activity{
	private final static String TAG = "OrderActivity";

	private TextView hospitalText;//医院名称
	private TextView sectionText;//挂号科室
	private TextView doctorlText;//医生姓名
	private TextView timeText;//门诊时间
	private TextView feeText;//挂号费用
	private TextView userText;//就诊
	private TextView chufuzhenText;//初复诊
	private TextView zhifuText;//支付方式
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_history);
		initView();
		initData();
	}
	
	private void initView(){
		getActionBar().setDisplayHomeAsUpEnabled(true);
		hospitalText = (TextView)findViewById(R.id.order_history_info_hospital_name);//医院名称
		sectionText = (TextView)findViewById(R.id.order_history_info_section_name);//挂号科室
		doctorlText = (TextView)findViewById(R.id.order_history_info_doctor_name);//医生姓名
		timeText = (TextView)findViewById(R.id.order_history_info_menzhen_time);//门诊时间
		feeText = (TextView)findViewById(R.id.order_history_info_guahao_fee);//挂号费用
		userText = (TextView)findViewById(R.id.order_history_info_name);//就诊
		chufuzhenText = (TextView)findViewById(R.id.order_history_info_chufuzhen);//初复诊
		zhifuText = (TextView)findViewById(R.id.order_history_info_zhifu);//支付方式

	}
	
	private void initData(){
		hospitalText.setText(CacheHandler.readCache(this, Constant.USER_INFO, Constant.USER_ORDER_HISTORY_HOSPITAL));
		sectionText.setText(CacheHandler.readCache(this, Constant.USER_INFO, Constant.USER_ORDER_HISTORY_SECTION));
		doctorlText.setText(CacheHandler.readCache(this, Constant.USER_INFO, Constant.USER_ORDER_HISTORY_DOCTOR));
		timeText.setText(CacheHandler.readCache(this, Constant.USER_INFO, Constant.USER_ORDER_HISTORY_TIME));
		feeText.setText(CacheHandler.readCache(this, Constant.USER_INFO, Constant.USER_ORDER_HISTORY_KIND));
		userText.setText(CacheHandler.readCache(this, Constant.USER_INFO, Constant.USER_ID));
		zhifuText.setText("去医院支付");
			
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();
			break;
		case R.id.actionbar_share:
			Toast.makeText(this, "分享还没做好,不要着急哦,亲", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
