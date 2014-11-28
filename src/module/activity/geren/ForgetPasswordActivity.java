package module.activity.geren;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:ForgetPasswordActivity.java
 * @Package:module.activity.geren
 * @time:下午3:26:43 2014-11-28
 * @useage:处理顽疾密码
 */
public class ForgetPasswordActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
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
