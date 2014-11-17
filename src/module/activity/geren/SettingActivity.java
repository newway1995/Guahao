package module.activity.geren;

import module.activity.R;
import android.app.Activity;
import android.os.Bundle;
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
			Toast.makeText(this, "名医吃饭去了,有事请拨110", Toast.LENGTH_SHORT).show();
			break;
		case R.id.setting_check_update:
			Toast.makeText(this, "名医吃饭去了,有事请拨110", Toast.LENGTH_SHORT).show();
			break;
		case R.id.logout:
			Toast.makeText(this, "名医吃饭去了,有事请拨110", Toast.LENGTH_SHORT).show();
			break;
		case R.id.setting_advice:
			Toast.makeText(this, "名医吃饭去了,有事请拨110", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
	}
}
