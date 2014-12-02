package module.view;

import module.activity.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
@SuppressWarnings("unused")
public class ZixunDoctor{
	
	private View view;
	private Context context;
	private LayoutInflater inflater;
	
	/**
	 * 控件监听
	 * */
	//第一组
	private ImageView zixun_doctor_portrait1;
	private TextView zixun_doctor_name1;
	private TextView zixun_doctor_zhuzhi1;
	private TextView zixun_doctor_dept1;
	private TextView zixun_which_hospital1;
	//第二组
	private ImageView zixun_doctor_portrait2;
	private TextView zixun_doctor_name2;
	private TextView zixun_doctor_zhuzhi2;
	private TextView zixun_doctor_dept2;
	private TextView zixun_which_hospital2;
	//第三组
	private ImageView zixun_doctor_portrait3;
	private TextView zixun_doctor_name3;
	private TextView zixun_doctor_zhuzhi3;
	private TextView zixun_doctor_dept3;
	private TextView zixun_which_hospital3;
	//第四组
	private ImageView zixun_doctor_portrait4;
	private TextView zixun_doctor_name4;
	private TextView zixun_doctor_zhuzhi4;
	private TextView zixun_doctor_dept4;
	private TextView zixun_which_hospital4;
	
	public ZixunDoctor(Context context) {
		this.context = context;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.item_zixun_scroll_layout, null);	
		initView();
		initData();
		onClick();
	}
	
	/**
	 * 设置监听
	 * */
	public View getView(){
		return view;
	}
	
	/**
	 * 初始化
	 * */
	private void initView(){
		 zixun_doctor_portrait1 = (ImageView)view.findViewById(R.id.zixun_doctor_portrait1);
		 zixun_doctor_name1 = (TextView)view.findViewById(R.id.zixun_doctor_name1);
		 zixun_doctor_zhuzhi1 = (TextView)view.findViewById(R.id.zixun_doctor_zhuzhi1);
		 zixun_doctor_dept1 = (TextView)view.findViewById(R.id.zixun_doctor_dept1);
		 zixun_which_hospital1 = (TextView)view.findViewById(R.id.zixun_which_hospital1);
		 
		 zixun_doctor_portrait2 = (ImageView)view.findViewById(R.id.zixun_doctor_portrait2);
		 zixun_doctor_name2 = (TextView)view.findViewById(R.id.zixun_doctor_name2);
		 zixun_doctor_zhuzhi2 = (TextView)view.findViewById(R.id.zixun_doctor_zhuzhi2);
		 zixun_doctor_dept2 = (TextView)view.findViewById(R.id.zixun_doctor_dept2);
		 zixun_which_hospital2 = (TextView)view.findViewById(R.id.zixun_which_hospital2);
		 
		 zixun_doctor_portrait3 = (ImageView)view.findViewById(R.id.zixun_doctor_portrait3);
		 zixun_doctor_name3 = (TextView)view.findViewById(R.id.zixun_doctor_name3);
		 zixun_doctor_zhuzhi3 = (TextView)view.findViewById(R.id.zixun_doctor_zhuzhi3);
		 zixun_doctor_dept3 = (TextView)view.findViewById(R.id.zixun_doctor_dept3);
		 zixun_which_hospital3 = (TextView)view.findViewById(R.id.zixun_which_hospital3);
		 
		 zixun_doctor_portrait4 = (ImageView)view.findViewById(R.id.zixun_doctor_portrait4);
		 zixun_doctor_name4 = (TextView)view.findViewById(R.id.zixun_doctor_name4);
		 zixun_doctor_zhuzhi4 = (TextView)view.findViewById(R.id.zixun_doctor_zhuzhi4);
		 zixun_doctor_dept4 = (TextView)view.findViewById(R.id.zixun_doctor_dept4);
		 zixun_which_hospital4 = (TextView)view.findViewById(R.id.zixun_which_hospital4);		 
	}
	
	private void initData(){
		
	}
	
	private void onClick(){
		//第一个头像被点击
		zixun_doctor_portrait1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "Clicked first!", Toast.LENGTH_SHORT).show();
			}
		});
		
		//第二个头像被点击
		zixun_doctor_portrait2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "Clicked second!", Toast.LENGTH_SHORT).show();
			}
		});
		
		//第三个头像被点击
		zixun_doctor_portrait3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "Clicked third!", Toast.LENGTH_SHORT).show();
			}
		});
		
		//第四个头像被点击
		zixun_doctor_portrait4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "Clicked fourth!", Toast.LENGTH_SHORT).show();
			}
		});		
	}
	
}
