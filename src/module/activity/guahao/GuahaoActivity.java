package module.activity.guahao;

import org.kymjs.aframe.ui.BindView;

import module.activity.R;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import common.core.BaseLoginActivity;
import common.util.CacheHandler;
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
	@BindView(id = R.id.order_info_menzhen_class)
	private TextView typeText;//门诊类型
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
		timeText.setText(CacheHandler.readCache(this, Constant.USER_INFO, Constant.USER_DOCTOR_NAME));
		typeText.setText(CacheHandler.readCache(this, Constant.USER_INFO, Constant.USER_DOCTOR_NAME));
		feeText.setText(CacheHandler.readCache(this, Constant.USER_INFO, Constant.USER_DOCTOR_NAME));
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
			break;
		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}
}
