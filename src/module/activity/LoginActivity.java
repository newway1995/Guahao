package module.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * 上午11:04:35
 * 登陆界面
 */
public class LoginActivity extends Activity implements OnClickListener{

	ActionBar actionBar;
	private EditText usernameText;
	private EditText passwordText;
	private Button login_btn;
	private TextView register;
	private TextView forgetPwd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		initData();
	}

	private void initView(){
		usernameText = (EditText)findViewById(R.id.login_username);
		passwordText = (EditText)findViewById(R.id.login_password);
		login_btn = (Button)findViewById(R.id.login_btn);
		register = (TextView)findViewById(R.id.login_register);
		forgetPwd = (TextView)findViewById(R.id.login_forget_pwd);
		
		usernameText.setOnClickListener(this);
		passwordText.setOnClickListener(this);
		login_btn.setOnClickListener(this);
		register.setOnClickListener(this);
		forgetPwd.setOnClickListener(this);
	}
	
	private void initData(){
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_btn:			
			break;
		case R.id.login_forget_pwd:
			break;
		case R.id.login_register:
			break;
		default:
			break;
		}
	}
}
