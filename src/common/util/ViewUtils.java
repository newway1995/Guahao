package common.util;

import module.activity.R;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    
    /**
     * 获取屏幕的大小
     * */
    public DisplayMetrics getScreenSize(Context context){
    	DisplayMetrics dm = new DisplayMetrics();
    	WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    	wm.getDefaultDisplay().getMetrics(dm);
    	return dm;
    }
    
    /**
     * 获取action bar的高度
     * */
    public int getActionBarHeight(ActionBar actionBar){
    	return actionBar.getHeight();
    }
    
    /**
     * 获取状态栏的高度
     * */
    public int getStateBarHeight(Activity activity){
    	Rect rect = new Rect();
    	activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
    	return rect.top;
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
	
	/** 
     * 得到自定义的progressDialog 
     * @param context 
     * @param msg 
     * @return 
     */ 
	public Dialog createLoadingDialog(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);  
		View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view  
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.loading_view);// 加载布局
        // main.xml中的ImageView  
        ImageView spaceshipImage = (ImageView) v.findViewById(R.id.loading_img);  
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(  
                context, R.anim.loading_animation);
        spaceshipImage.startAnimation(hyperspaceJumpAnimation); 
        
        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog  
        
        loadingDialog.setCancelable(true);// 不可以用“返回键”取消  
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(  
                LinearLayout.LayoutParams.MATCH_PARENT,  
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局  
        return loadingDialog;  
	}
}
