package common.util;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.PopupWindow;


/**
 * @author niuwei
 * @email nniuwei@163.com
 * 下午8:13:30
 * View 
 */
public class ViewUtils {
	private ViewUtils() {}

    private static class ClassHolder {
        private static final ViewUtils instance = new ViewUtils();
    }
    
    /**
     * 类对象创建方法
     * 
     * @return 本类的对象
     */
    public static ViewUtils getInstance(){
    	return ClassHolder.instance;
    }
    
    public void createDialog(){
    	
    }
    
    /**
     * 用于创建PopupWindow封装一些公用属性
     */
	public PopupWindow createWindow(View view, int w, int h, int argb) {
        PopupWindow popupView = new PopupWindow(view, w, h);
        popupView.setFocusable(true);
        popupView.setBackgroundDrawable(new ColorDrawable(argb));
        popupView.setOutsideTouchable(true);
        return popupView;
    }
}
