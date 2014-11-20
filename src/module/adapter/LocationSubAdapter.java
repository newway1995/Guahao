package module.adapter;

import module.activity.R;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LocationSubAdapter extends BaseAdapter{
	private LayoutInflater layoutInflater;
	private String[] cities;
	
	public LocationSubAdapter(Context context,String[] cities) {
		this.cities = cities;
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return cities.length;
	}

	@Override
	public Object getItem(int arg0) {
		return cities[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		if (arg1 == null) {
			arg1 = layoutInflater.inflate(R.layout.item_location_selector, null);
		}
		TextView textView = (TextView)arg1.findViewById(R.id.location_item);
		textView.setTextColor(Color.BLACK);
		textView.setBackgroundColor(Color.WHITE);
		textView.setText(cities[arg0]);
		return arg1;
	}

}
