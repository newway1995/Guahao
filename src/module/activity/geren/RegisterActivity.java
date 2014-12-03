package module.activity.geren;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import module.activity.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:RegisterActivity.java
 * @Package:module.activity.geren
 * @time:下午3:22:52 2014-11-28
 * @useage:注册
 */
public class RegisterActivity extends Activity{

	private final String TAG = "registerActivity";
	private EditText phoneText;
	private EditText usernameText;
	private EditText passwordText;
	private EditText passwordConfirmText;
	private EditText identifyText;
	private Button register_btn;
	private boolean isRegister;//是注册还是获取验证码
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
		setContentView(R.layout.activity_register);
		initView();
		initData();
	}

	/**
	 * 初始化视图
	 * */
	private void initView(){
		getActionBar().setDisplayHomeAsUpEnabled(true);
		phoneText = (EditText)findViewById(R.id.register_telephone);
		usernameText = (EditText)findViewById(R.id.register_username);
		passwordText = (EditText)findViewById(R.id.register_password);
		passwordConfirmText = (EditText)findViewById(R.id.register_password_confirm);
		identifyText = (EditText)findViewById(R.id.register_identifying);
		register_btn = (Button)findViewById(R.id.register_btn);
	}
	
	/**
	 * 初始化数据
	 * */
	private void initData(){
		isRegister = false;
	}

	/**
	 * 注册监听
	 * */
	public void register(View v){
		String phone = phoneText.getText().toString().trim();
		String username = usernameText.getText().toString().trim();
		String password = passwordText.getText().toString().trim();
		String passwordConfirm = passwordConfirmText.getText().toString().trim();
		String identify = identifyText.getText().toString().trim();
		if (isLegal(phone,username ,password, passwordConfirm)) {
			if (isRegister) {//注册
				registerNewUser(phone,username,password,identify);
			}else {//验证
				isRegister = true;
				register_btn.setText(getString(R.string.register));
			}
		}
	}
	
	/**
	 * 注册逻辑实现
	 * */
	private void registerNewUser(String phone,String username,String password,String identify){
		
	}
	
	/**
	 * 检查输入是否合法
	 * */
	private boolean isLegal(String phone,String username,String password,String passwordConfirm){
		Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");//匹配电话号码
		Matcher match = pattern.matcher(phone); 
		if (phone.equals("") || phone == null ||username == null || username.equals("") || 
				password == null || password.equals("") || passwordConfirm == null || passwordConfirm.equals("")) {
			Toast.makeText(this, "输入不能为空", Toast.LENGTH_SHORT).show();
			return false;
		}else if (!match.matches()) {
			Toast.makeText(this, "电话格式不正确", Toast.LENGTH_SHORT).show();
			return false;
		}else if (!password.equals(passwordConfirm)) {
			Toast.makeText(this, "两次密码输入不正确", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
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
}
