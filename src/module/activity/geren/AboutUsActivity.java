package module.activity.geren;

import module.activity.R;
import module.activity.SwipeBackActivity;
import android.os.Bundle;
import android.view.MenuItem;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:AboutUsActivity.java
 * @Package:module.activity.geren
 * @time:上午10:09:47 2014-11-30
 * @useage:关于我们
 */
public class AboutUsActivity extends SwipeBackActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_us);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			overridePendingTransition(0, R.anim.base_slide_right_out);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}	
}
