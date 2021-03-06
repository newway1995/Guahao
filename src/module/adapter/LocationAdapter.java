package module.adapter;

import module.activity.R;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LocationAdapter extends BaseAdapter{
	private static final String TAG = "LocationAdapter";
	Context context;
	LayoutInflater inflater;
	String [] provinces;
	private int selectedPosition = -1; 
	
	public LocationAdapter(Context context,String[] pStrings) {
		this.context = context;
		this.provinces = pStrings;
		inflater=LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return provinces.length;
	}

	@Override
	public Object getItem(int arg0) {
		return provinces[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {		
		if(convertView == null)
			convertView = inflater.inflate(R.layout.item_selecte_location, null);
		TextView textView = (TextView)convertView.findViewById(R.id.location_item);
		View imageView = (View)convertView.findViewById(R.id.location_icon);
		// 设置选中效果    
	    if(selectedPosition == position){	    	
	    	convertView.setBackgroundColor(Color.WHITE);
	    	imageView.setVisibility(View.VISIBLE);
			Log.d(TAG, "selectedposition = "+selectedPosition);
	    }else {	    	
	    	convertView.setBackgroundColor(context.getResources().getColor(R.color.gray_location));
	    	imageView.setVisibility(View.GONE);
		}
	    textView.setText(provinces[position]);
		return convertView;
	}
	
	public void setSelectedPosition(int position) {   
		   selectedPosition = position;   
	} 

}
