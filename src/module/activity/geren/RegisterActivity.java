package module.activity.geren;

import module.activity.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:RegisterActivity.java
 * @Package:module.activity.geren
 * @time:下午3:22:52 2014-11-28
 * @useage:注册
 */
public class RegisterActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_register);
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
