package module.activity.geren;

import module.activity.R;
import android.app.Activity;
import android.os.Bundle;
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
public class UserInfoActivity extends Activity implements OnClickListener{
	private final static String TAG = "UserInfoActivity";
	
	private RelativeLayout user_info_portrait;
	private TextView user_info_username;
	private LinearLayout user_info_changpwd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
		setContentView(R.layout.activity_userinfo);
		super.onCreate(savedInstanceState);
		initView();
		initData();
	}	
	
	private void initView(){
		getActionBar().setDisplayHomeAsUpEnabled(true);
		user_info_portrait = (RelativeLayout)findViewById(R.id.user_info_portrait);
		user_info_username = (TextView)findViewById(R.id.user_info_username);
		user_info_changpwd = (LinearLayout)findViewById(R.id.user_info_changpwd);
		
		user_info_changpwd.setOnClickListener(this);
		user_info_portrait.setOnClickListener(this);
	}
	
	private void initData(){
		user_info_username.setText("登录名： 15652953455");
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
}
