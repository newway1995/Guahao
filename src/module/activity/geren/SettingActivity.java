package module.activity.geren;

import common.util.ViewUtils;

import module.activity.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * 下午6:03:55
 * 设置
 */
public class SettingActivity extends Activity implements OnClickListener{
	
	RelativeLayout setting_navigate;
	RelativeLayout setting_about;
	RelativeLayout setting_advice;
	RelativeLayout setting_check_update;
	Button logout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		initView();
	}
	
	private void initView(){
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setting_navigate = (RelativeLayout)findViewById(R.id.setting_navigate);
		setting_about = (RelativeLayout)findViewById(R.id.setting_about);
		setting_check_update = (RelativeLayout)findViewById(R.id.setting_check_update);
		logout = (Button)findViewById(R.id.logout);
		setting_advice = (RelativeLayout)findViewById(R.id.setting_advice);
		
		setting_about.setOnClickListener(this);
		setting_navigate.setOnClickListener(this);
		setting_check_update.setOnClickListener(this);
		logout.setOnClickListener(this);
		setting_advice.setOnClickListener(this);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.setting_navigate:
			Toast.makeText(this, "名医吃饭去了,有事请拨110", Toast.LENGTH_SHORT).show();
			break;
		case R.id.setting_about:
			startActivity(new Intent(SettingActivity.this,AboutUsActivity.class));
			break;
		case R.id.setting_check_update:
			final Dialog dialog = ViewUtils.getInstance().createLoadingDialog(this);
			dialog.show();
			new Handler().postDelayed(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					dialog.dismiss();
					Toast.makeText(SettingActivity.this, "已是当前最新版本", Toast.LENGTH_SHORT).show();
				}
			}, 1500);
			break;
		case R.id.logout:
			Toast.makeText(this, "名医吃饭去了,有事请拨110", Toast.LENGTH_SHORT).show();
			break;
		case R.id.setting_advice:
			startActivity(new Intent(SettingActivity.this,ContactUsActivity.class));
			break;

		default:
			break;
		}
	}
}
