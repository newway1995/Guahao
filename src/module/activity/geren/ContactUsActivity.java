package module.activity.geren;

import module.activity.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;  
  

/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:ContactUsActivity.java
 * @Package:module.activity.geren
 * @time:下午3:04:41 2014-11-30
 * @useage:意见反馈
 */
public class ContactUsActivity extends Activity implements OnClickListener{

	private EditText emailText;
	private EditText phoneText;
	private EditText contentText;
	private Button submitBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_us);
		initView();
	}

	private void initView(){
		emailText = (EditText)findViewById(R.id.contact_email);
		phoneText = (EditText)findViewById(R.id.contact_phone);
		contentText = (EditText)findViewById(R.id.contact_content);		
		submitBtn = (Button)findViewById(R.id.contact_submit);
		
		submitBtn.setOnClickListener(this);
		getActionBar().setDisplayHomeAsUpEnabled(true);
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
	 * 提交按钮
	 * */
	private void submit(String email,String phone,String content){
		if (email.equals("") || email.isEmpty()) {
			Toast.makeText(this, "请输入email", Toast.LENGTH_SHORT).show();
			return;
		}else if (content.equals("") || content.isEmpty()) {
			Toast.makeText(this, "请输入您宝贵的意见", Toast.LENGTH_SHORT).show();
			return;
		}
		sendMailByIntent(email, phone, content);
	}
	
	
	/**
	 * 发送邮件
	 * */
	private int sendMailByIntent(String email,String phone,String content){
		String[] reciver = new String[] { "buaaguahao2014@.com" };  
        Intent myIntent = new Intent(android.content.Intent.ACTION_SEND);  
        myIntent.setType("plain/text");  
        myIntent.putExtra(android.content.Intent.EXTRA_EMAIL, reciver);  
        myIntent.putExtra(android.content.Intent.EXTRA_CC, email);  
        myIntent.putExtra(android.content.Intent.EXTRA_TEXT, content + " -- by " + phone);  
        startActivity(Intent.createChooser(myIntent, "意见反馈"));    
        return 1;  
	}
	
	@Override
	public void onClick(View v) {
		String email = emailText.getText().toString().trim();
		String phone = phoneText.getText().toString().trim();
		String content = contentText.getText().toString().trim();
		submit(email,phone,content);		
	}	
}
