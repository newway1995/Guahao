package module.activity.guahao;

import module.activity.R;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

public class MessageActivity extends Activity{
	
	private ActionBar actionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
		initView();
	}	
	
	private void initView(){
		actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		//actionBar.setDisplayShowHomeEnabled(false); //隐藏图片
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
