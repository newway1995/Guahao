package module.activity.zixun;

import module.activity.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * 上午10:31:58
 * 咨询
 */
public class ConsultActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_zixun);
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
			finish();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
}
