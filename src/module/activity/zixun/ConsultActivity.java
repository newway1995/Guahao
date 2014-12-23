package module.activity.zixun;

import module.activity.R;
import module.entity.Doctor;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * 上午10:31:58
 * 咨询
 */
public class ConsultActivity extends Activity{
	
	private String TAG = "ConsultActivity";
	private Doctor doctor;//传入的医生
	private TextView deptText;//部门
	private TextView hospitalText;//医院
	private TextView doctorText;//医生
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_zixun);
		super.onCreate(savedInstanceState);
		initView();		
	}
	
	private void initView(){
		getActionBar().setDisplayHomeAsUpEnabled(true);
		doctor = null;
		
		deptText = (TextView)findViewById(R.id.zixun_dept);
		hospitalText = (TextView)findViewById(R.id.zixun_hospital);
		doctorText = (TextView)findViewById(R.id.zixun_doctor);
		
		if (getIntent() != null) 
			doctor = (Doctor)getIntent().getSerializableExtra("consultDoctor");
		if (doctor != null) 					
			initData(doctor);				
	}
	
	private void initData(Doctor doctor){
		Log.d(TAG, "Doctor = " + doctor.toString());
		deptText.setText(getResources().getString(R.string.zixun_dept) + doctor.getSection_name());
		hospitalText.setText(getResources().getString(R.string.zixun_hospital) + doctor.getHospital_name());
		doctorText.setText(getResources().getString(R.string.consult_doctor) + doctor.getName());
		//设置可见
		hospitalText.setVisibility(View.VISIBLE);
		doctorText.setVisibility(View.VISIBLE);
		//设置不可编辑
		deptText.setEnabled(false);
		hospitalText.setEnabled(false);
		doctorText.setEnabled(false);
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
	protected void onStop() {
		super.onStop();
	}
}
