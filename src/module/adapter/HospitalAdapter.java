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
 * @ClassName:HospitalAdapter.java
 * @Package:module.adapter
 * @time:上午11:50:01 2014-12-3
 * @useage:医生列表
 */
public class HospitalAdapter extends BaseAdapter{
	private final static String TAG = "HospitalAdapter";
	ArrayList<HashMap<String, String >> list;	
	private LayoutInflater inflater;
	
	public HospitalAdapter(Context context,ArrayList<HashMap<String, String>> list){
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
			convertView = inflater.inflate(R.layout.item_select_hospital, null);
		ImageView imageView = (ImageView)convertView.findViewById(R.id.select_hospital_image);
		TextView titleText = (TextView)convertView.findViewById(R.id.select_hospital_name);
		TextView dscptText = (TextView)convertView.findViewById(R.id.select_hospital_level);
		
		KJBitmap kjBitmap = KJBitmap.create();
		kjBitmap.display(imageView, list.get(position).get("img"));
		titleText.setText(list.get(position).get("name"));
		dscptText.setText(list.get(position).get("level"));
		return convertView;
	}
}
