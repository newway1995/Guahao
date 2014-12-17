package module.activity.geren;

import common.core.BaseLoginActivity;

import module.activity.LoginActivity;
import module.activity.R;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * 下午5:52:43
 * 用户信息
 */
public class UserInfoActivity extends BaseLoginActivity implements OnClickListener{
	private final static String TAG = "UserInfoActivity";
	
	private RelativeLayout user_info_portrait;
	private TextView user_info_username;
	private LinearLayout user_info_changpwd;
	
	@Override
	public void onStop(){
		super.onStop();
		Log.d(TAG, "UserInfoActivity ----- onStop");
		//finish();
	}
	
	
	@Override
	protected void initData() {
		super.initData();
		user_info_username.setText("登录名： 15652953455");
	}

	@Override
	protected void initView() {
		super.initView();
		user_info_portrait = (RelativeLayout)findViewById(R.id.user_info_portrait);
		user_info_username = (TextView)findViewById(R.id.user_info_username);
		user_info_changpwd = (LinearLayout)findViewById(R.id.user_info_changpwd);
		
		user_info_changpwd.setOnClickListener(this);
		user_info_portrait.setOnClickListener(this);		
	}

	@Override
	protected void skip() {
		super.skip();
		showActivity(UserInfoActivity.this,LoginActivity.class);
	}

	@Override
	public void widgetClick(View v) {
		super.widgetClick(v);
		switch (v.getId()) {
		case R.id.user_info_changpwd:
			Toast.makeText(this, "名医吃饭去了,有事请拨110", Toast.LENGTH_SHORT).show();
			break;
		case R.id.user_info_portrait:
			Toast.makeText(this, "名医吃饭去了,有事请拨110", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}

	@Override
	protected void setRootView() {
		super.setRootView();
		setContentView(R.layout.activity_userinfo);
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			overridePendingTransition(0, R.anim.base_slide_right_out);
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
