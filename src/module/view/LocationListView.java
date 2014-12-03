package module.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ListView;
import android.view.MotionEvent;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:LocationListView.java
 * @Package:module.view
 * @time:下午6:54:36 2014-11-25
 * @useage:listView
 */
public class LocationListView extends ListView{ 
	private String TAG = "LocationListView";
	
	
    public LocationListView (Context context, AttributeSet attrs) { 
        super(context, attrs); 
    } 
 
    public LocationListView (Context context, AttributeSet attrs, int defStyle) { 
        super(context, attrs, defStyle); 
    } 
 
    public LocationListView (Context context) {     	
        super(context); 
        Log.d(TAG, "LocationListView");  
    } 
 
    @Override 
    public boolean onTouchEvent(MotionEvent event) { 
        return super.onTouchEvent(event); 
    }    
}
