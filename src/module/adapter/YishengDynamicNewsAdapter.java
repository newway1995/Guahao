package module.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import org.kymjs.aframe.bitmap.KJBitmap;

import module.activity.R;

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
 * 下午1:07:58
 * 医生动态
 */
public class YishengDynamicNewsAdapter extends BaseAdapter{
	private final static String TAG = "MingyiNewsAdapter";
	ArrayList<HashMap<String, String >> list;	
	private LayoutInflater inflater;
	
	public YishengDynamicNewsAdapter(Context context,ArrayList<HashMap<String, String>>	list) {
		this.list = list;
		Log.d(TAG, list.toString());
		inflater=LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) 
			convertView = inflater.inflate(R.layout.item_yishengdynamic_news, null);
		ImageView imageView = (ImageView)convertView.findViewById(R.id.item_yishengdynamic_image);
		TextView nameText = (TextView)convertView.findViewById(R.id.item_yishengdynamic_name);
		TextView timeText = (TextView)convertView.findViewById(R.id.item_yishengdynamic_time);
		TextView contentText = (TextView)convertView.findViewById(R.id.item_yishengdynamic_content);
		
		KJBitmap kjBitmap = KJBitmap.create();
		kjBitmap.display(imageView, list.get(position).get("img"));
		nameText.setText(list.get(position).get("doctor_name"));
		timeText.setText(list.get(position).get("time"));
		contentText.setText(list.get(position).get("content"));
		return convertView;
	}
}
