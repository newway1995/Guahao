package module.activity.geren;

import java.util.ArrayList;

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

import module.activity.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:ChangePwdActivity.java
 * @Package:module.activity.geren
 * @time:下午2:35:40 2014-11-28
 * @useage:修改密码
 */
public class ChangePwdActivity extends Activity implements OnClickListener{
	private static final String TAG = "ChangePwdActivity";
	private TextView oldTextView;
	private TextView newTextView;
	private Button submit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_change_pwd);
		super.onCreate(savedInstanceState);
		initView();
	}
	
	private void initView(){
		oldTextView = (TextView)findViewById(R.id.change_pwd_old);
		newTextView = (TextView)findViewById(R.id.change_pwd_new);
		submit = (Button)findViewById(R.id.change_pwd_submit);
		
		submit.setOnClickListener(this);
	}
	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.change_pwd_submit:
			changePwd();
			break;

		default:
			break;
		}
	}
	
	private void changePwd(){
		final String oldPwd = oldTextView.getText().toString().trim();
		final String newPwd = newTextView.getText().toString().trim();
		if (oldPwd.equals("")) {//字段不能为空
			Toast.makeText(this, getResources().getString(R.string.change_pwd_old_is_empty),
					Toast.LENGTH_SHORT).show();
			return;
		}else if (newPwd.equals("")) {
			Toast.makeText(this, getResources().getString(R.string.change_pwd_new_is_empty),
					Toast.LENGTH_SHORT).show();
			return;
		}else{//修改密码实现
			AsyncInter inter = new AsyncInter() {
				String result = "";
				@Override
				public void onPreExecute() {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onPostExecute() {
					// TODO Auto-generated method stub
					Log.d(TAG, result);
					try {
						JSONObject jObject = new JSONObject(result);
						//修改密码成功
						if (Integer.parseInt(jObject.getString("success")) == 1) {
							Toast.makeText(ChangePwdActivity.this, getString(R.string.change_pwd_success), Toast.LENGTH_SHORT).show();
						}else {//密码修改失败
							Toast.makeText(ChangePwdActivity.this, getString(R.string.change_pwd_error), Toast.LENGTH_SHORT).show();
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}					
				}
				
				@Override
				public void interruptTask() {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void doInBackground() {
					// TODO Auto-generated method stub
					Log.d(TAG, "doInBackground");
					ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
					params.add(new BasicNameValuePair("old_password", oldPwd));
					params.add(new BasicNameValuePair("new_password", newPwd));
					params.add(new BasicNameValuePair("user_id",CacheHandler.
							readCache(ChangePwdActivity.this, Constant.USER_INFO, Constant.USER_ID)));//用户的id
					params.add(new BasicNameValuePair("action", AppCode.ACTION_CHANGE_PWD));
					result = AppCode.getData(ChangePwdActivity.this, params, AppNet.Access.GET);
				}
			};
			new MyAsyncTask(inter, true, ChangePwdActivity.this).execute();
		}
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
