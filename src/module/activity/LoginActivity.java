package module.activity;



import java.util.ArrayList;

import module.activity.geren.ForgetPasswordActivity;
import module.activity.geren.RegisterActivity;
import module.activity.geren.UserInfoActivity;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import common.util.AsyncInter;
import common.util.CacheHandler;
import common.util.MyAsyncTask;
import constant.AppCode;
import constant.AppNet;
import constant.Constant;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * 上午11:04:35
 * 登陆界面
 */
public class LoginActivity extends Activity implements OnClickListener{
	public static final String TAG = "LoginActivity";
	ActionBar actionBar;
	private EditText usernameText;
	private EditText passwordText;
	private Button login_btn;
	private TextView register;
	private TextView forgetPwd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub 
		Log.d(TAG, "onCreate");
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
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_btn:
			login();
			break;
		case R.id.login_forget_pwd:
			startActivity(new Intent(LoginActivity.this,ForgetPasswordActivity.class));
			break;
		case R.id.login_register:
			startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
			break;
		default:
			break;
		}
	}
	
	
	//处理登录事件
	private void login(){
		final String username = usernameText.getText().toString().trim();
		final String password = passwordText.getText().toString().trim();
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
						saveUser(username,password,jObject.getString("user_id"));
						Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
						startActivity(new Intent(LoginActivity.this,UserInfoActivity.class));
						overridePendingTransition(R.anim.base_slide_right_in, 0);
					}else {
						Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_LONG).show();
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
				params.add(new BasicNameValuePair(Constant.USERNAME, username));
				params.add(new BasicNameValuePair(Constant.PASSWORD, password));
				params.add(new BasicNameValuePair("action", AppCode.ACTION_LOGIN));
				result = AppCode.getData(LoginActivity.this, AppCode.LOGIN, params, AppNet.Access.GET);
			}
		};
		new MyAsyncTask(inter, true, LoginActivity.this).execute();
	}
//	final String username = usernameText.getText().toString().trim();
//	final String password = passwordText.getText().toString().trim();
//	KJHttp kjHttp = new KJHttp();
//	KJStringParams params = new KJStringParams();
//	params.put("username", username);
//	params.put("password", password);
//	params.put("action", "API_GH_LOGIN");
//	kjHttp.urlGet("http://192.168.169.4:8888/SystemDesign/index.php", params, new StringCallBack() {
//		
//		@Override
//		public void onSuccess(String arg0) {
//			// TODO Auto-generated method stub
//			Log.d(TAG, "Success");
//		}
//
//		@Override
//		public void onFailure(Throwable t, int errorNo, String strMsg) {
//			Log.d(TAG, "onFailure");
//			super.onFailure(t, errorNo, strMsg);
//		}			
//	});

	private void saveUser(String username,String password,String user_id){
		CacheHandler.writeCache(this, Constant.USER_INFO, Constant.USERNAME, username);
		CacheHandler.writeCache(this, Constant.USER_INFO, Constant.PASSWORD, password);
		CacheHandler.writeCache(this, Constant.USER_INFO, Constant.USER_ID, user_id);
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
