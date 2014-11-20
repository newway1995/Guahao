/**
 * FenZhenActivity.java
 * module.activity.guahao
 * niuwei
 * 2014-11-20上午12:46:22
 */
package module.activity.guahao;

import module.activity.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * 上午12:46:22
 * 智能导诊
 */
public class FenZhenActivity extends Activity{
	private static final String TAG = "FenZhenActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		initView();
		initData();
	}
	
	private void initView(){
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	private void initData(){
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();
			break;
		case R.id.actionbar_share:
			Toast.makeText(this, "分享还没做好,不要着急哦,亲", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
}
