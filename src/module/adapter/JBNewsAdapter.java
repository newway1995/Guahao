package module.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import module.activity.R;

import org.kymjs.aframe.bitmap.KJBitmap;

import constant.Constant;

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
 * 疾病
 */
public class JBNewsAdapter extends BaseAdapter{
	private final static String TAG = "JBNewsAdapter";
	ArrayList<HashMap<String, String >> list;	
	private LayoutInflater inflater;
	
	public JBNewsAdapter(Context context,ArrayList<HashMap<String, String>>	list) {
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
			convertView = inflater.inflate(R.layout.item_jibing_news, null);
		ImageView imageView = (ImageView)convertView.findViewById(R.id.item_jibing_image);
		TextView titleText = (TextView)convertView.findViewById(R.id.item_jibing_title);
		TextView dscptText = (TextView)convertView.findViewById(R.id.item_jibing_description);
		
		KJBitmap kjBitmap = KJBitmap.create();
		String imgPath = Constant.IMAGE_DOCTOR_PATH_SUFFIX + list.get(position).get("img").substring(4);
		kjBitmap.display(imageView, imgPath,96,96);
		titleText.setText(list.get(position).get("title"));
		dscptText.setText(list.get(position).get("content"));
		return convertView;
	}
}