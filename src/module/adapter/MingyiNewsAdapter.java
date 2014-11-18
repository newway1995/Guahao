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
 * 下午1:08:24
 * 名医在线
 */
public class MingyiNewsAdapter extends BaseAdapter{
	private final static String TAG = "MingyiNewsAdapter";
	ArrayList<HashMap<String, String >> list;	
	private LayoutInflater inflater;
	
	public MingyiNewsAdapter(Context context,ArrayList<HashMap<String, String>>	list) {
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
			convertView = inflater.inflate(R.layout.item_mingyizaixian_news, null);
		ImageView imageView = (ImageView)convertView.findViewById(R.id.item_mingyi_image);
		TextView titleText = (TextView)convertView.findViewById(R.id.item_mingyi_title);
		TextView timeText = (TextView)convertView.findViewById(R.id.item_mingyi_time);
		TextView dscptText = (TextView)convertView.findViewById(R.id.item_mingyi_description);
		
		KJBitmap kjBitmap = KJBitmap.create();
		kjBitmap.display(imageView, list.get(position).get("imageUrl"));
		titleText.setText(list.get(position).get("title"));
		timeText.setText(list.get(position).get("time"));
		dscptText.setText(list.get(position).get("description"));
		return convertView;
	}
}
