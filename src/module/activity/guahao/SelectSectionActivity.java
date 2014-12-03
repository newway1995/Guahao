package module.activity.guahao;

import module.activity.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:SelectSectionActivity.java
 * @Package:module.activity.guahao
 * @time:上午11:55:42 2014-12-3
 * @useage:选择医院科室
 */
public class SelectSectionActivity extends Activity{
	private final String TAG = "SelectSectionActivity"; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
		setContentView(R.layout.activity_location_select);
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
