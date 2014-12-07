package module.activity.guahao;

import constant.Constant;
import module.activity.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:JBSolutionActivity.java
 * @Package:module.activity.guahao
 * @time:下午1:41:39 2014-12-6
 * @useage:分诊的疾病推断和解决方案
 */
public class JBSolutionActivity extends Activity{

	private int first;//第几项
	private int position;//第几条，合在一起就是第几项的第几条
	private TextView textView;
	private boolean isMale;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fenzhen_jb_solution);
		init();
	}
	
	/**
	 * 初始化
	 * */
	private void init(){
		getActionBar().setDisplayHomeAsUpEnabled(true);
		first = getIntent().getExtras().getInt("first");
		position = getIntent().getExtras().getInt("position");
		isMale = getIntent().getExtras().getBoolean("isMale");
				
		textView = (TextView)findViewById(R.id.solution_text);
		//textView.setText("First = " + first + " position = " + position);
		if (isMale) {
			textView.setText(Constant.getMaleSickSolution(first, position, getResources()));
		}else {
			textView.setText(Constant.getFemaleSickSolution(first, position, getResources()));
		}
	}

	@Override
	protected void onStop() {
		finish();
		super.onStop();
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
