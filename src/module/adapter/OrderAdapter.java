package module.adapter;
import java.util.ArrayList;
import java.util.HashMap;
import module.activity.R;

import org.kymjs.aframe.bitmap.KJBitmap;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:DoctorAdapter.java
 * @Package:module.adapter
 * @time:上午12:05:26 2014-12-18
 * @useage:Orderlist适配器
 */
public class OrderAdapter extends BaseAdapter{
	private final static String TAG = "DoctorHospital";
	ArrayList<HashMap<String, String >> list;	
	private LayoutInflater inflater;
	
	public OrderAdapter(Context context,ArrayList<HashMap<String, String>> list){
		this.list = list;
		Log.d(TAG, list.toString());
		inflater=LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) 
			convertView = inflater.inflate(R.layout.item_myorder, null);
		ImageView imageView = (ImageView)convertView.findViewById(R.id.item_history_doctor_portrait);
		TextView doctorName = (TextView)convertView.findViewById(R.id.item_history_doctor_name);
		TextView hospitalName= (TextView)convertView.findViewById(R.id.item_history_hospital);
		TextView sectionName= (TextView)convertView.findViewById(R.id.item_history_section);
		TextView time =  (TextView)convertView.findViewById(R.id.item_history_time);
		KJBitmap kjBitmap = KJBitmap.create();
	    //kjBitmap.display(imageView, list.get(position).get("img"));
		doctorName.setText(list.get(position).get("doctor_name"));
		hospitalName.setText(list.get(position).get("hospital_name"));
		sectionName.setText(list.get(position).get("section_name"));
		if (list.get(position).get("order_time").equals("1")) {
			time.setText("就诊时间: 下周五上午");
		}else {
			time.setText("就诊时间: 下周五上午");
		}		
		return convertView;
	}
}
