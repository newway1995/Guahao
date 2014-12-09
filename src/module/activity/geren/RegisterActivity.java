package module.activity.geren;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVMobilePhoneVerifyCallback;
import com.avos.avoscloud.AVOSCloud;
import common.util.AsyncInter;
import common.util.MyAsyncTask;

import constant.AppCode;
import constant.AppNet;
import constant.Constant;

import module.activity.R;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
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
	@SuppressWarnings("unused")
	private int verifySuccess = -1;
	private boolean isRegister;//是注册还是获取验证码
	
	String phone ;
	String username;
	String password;
	String passwordConfirm;
	String identify;
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
		phone = phoneText.getText().toString().trim();
		username = usernameText.getText().toString().trim();
		password = passwordText.getText().toString().trim();
		passwordConfirm = passwordConfirmText.getText().toString().trim();
		identify = identifyText.getText().toString().trim();
		if (isLegal(phone,username ,password, passwordConfirm)) {
			if (isRegister) {//注册
				verifyCode(identify);				
			}else {//验证
				isRegister = true;
				register_btn.setText(getString(R.string.register));
				sendCode(phone);
			}
		}
	}
	
	/**
	 * 注册逻辑实现
	 * */
	private void registerNewUser(String phone,final String username,final String password){
	
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
						//saveUser(username,password,jObject.getString("user_id"));
						Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
						startActivity(new Intent(RegisterActivity.this,UserInfoActivity.class));
						overridePendingTransition(R.anim.base_slide_right_in, 0);
					}else {
						Toast.makeText(RegisterActivity.this, jObject.getString("message"), Toast.LENGTH_LONG).show();
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
				params.add(new BasicNameValuePair("action", AppCode.ACTION_REGISTER));
				result = AppCode.getData(RegisterActivity.this, AppCode.LOGIN, params, AppNet.Access.POST);
			}
		};
		new MyAsyncTask(inter, true, RegisterActivity.this).execute();
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
	
	
	//获取验证码的函数
	public void sendCode(final String phone) {
	    new AsyncTask<Void, Void, Void>() {
	      boolean res;
	      @Override
	      protected Void doInBackground(Void... params) {
	        try {
	          AVOSCloud.requestSMSCode(phone, "SmsDemo", "注册", 10);
	          res=true;
	        } catch (AVException e) {
	          e.printStackTrace();
	          res=false;
	        }
	        return null;
	      }

	      @Override
	      protected void onPostExecute(Void aVoid) {
	        super.onPostExecute(aVoid);
	        if(res){
	          toast(R.string.sendSucceed);
	        }else{
	          toast(R.string.sendFailed);
	        }
	      }
	    }.execute();
	  }
	
	//验证验证码
	private void verifyCode(String code) {
		    AVOSCloud.verifySMSCodeInBackground(code,new AVMobilePhoneVerifyCallback() {
		      @Override
		      public void done(AVException e) {
		        if(e==null){
		        	verifySuccess = 1;
		        	registerNewUser(phone,username,password);
		        	toast(R.string.verifySucceed);
		        }else{
		        	e.printStackTrace();
		        	verifySuccess = -1;
		        	toast(R.string.verifyFailed);
		        }
		      }
		    });
		  }

	  private void toast(int id) {
	    Toast.makeText(RegisterActivity.this, id, Toast.LENGTH_SHORT).show();
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
	
	/**
	 * 注册成功之后注销activity
	 * */
	@Override
	public void onStop(){
		super.onStop();
		finish();
	}
}
