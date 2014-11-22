package common.util;


import module.activity.R;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MyAsyncTask extends AsyncTask<Void, Void, Void> implements Runnable{

	//时间控制线程
	private Thread thread;
	//进度条
	private Dialog dialog;
	//异步操作
	private AsyncInter function;
	//异步操作限时
	private int MAX_TIME = 20;
	//是否存在进度条
	private boolean existDialog;
	//上下文
	private Context context;
	
	private static final String TAG = "MyAsyncTask";

	/**
	 * 构造函数
	 * @param func 异步操作
	 * @param existDialog 是否存在进度条
	 * @param context 上下文
	 */
	public MyAsyncTask(AsyncInter func, boolean existDialog, Context context) {

		Log.d(TAG, "MyAsyncTask construct");
		
//		dialog = new ProgressDialog(context);
//		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); 
//        dialog.setMessage(context.getText(R.string.loading));  
//        dialog.setIndeterminate(false);
//        dialog.setCanceledOnTouchOutside(false);
		dialog = createLoadingDialog(context);
        function = func;
        
        thread = new Thread(this);
        this.existDialog = existDialog;
        this.context = context;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			int i = 0;
            do
            {
            	i++;
                Thread.sleep(500);
                
                if(i * 0.5f > MAX_TIME)
                {
                	this.cancel(false);
                	break;
                }
                System.out.println("{" + 0.5 * i + "s}");
            }while(Thread.interrupted()==false);
        } catch (Exception e) {
            // TODO: handle exception
           e.printStackTrace();
        }
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		Log.d(TAG, "MyAsyncTask onPreExecute");
		super.onPreExecute();
		if(existDialog)
		{
			dialog.show();
		}
		thread.start();
		function.onPreExecute();
	}
	
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		Log.d(TAG, "MyAsyncTask onPostExecute");
		super.onPostExecute(result);
		if(existDialog)
		{
			dialog.cancel();
		}
		function.onPostExecute();
		if(!Thread.interrupted())
			thread.interrupt();
	}
	
	@Override
	protected Void doInBackground(Void... arg0) {
		Log.d(TAG, "MyAsyncTask doInBackground");
		function.doInBackground();
		return null;
	}

	@Override
	protected void onCancelled(Void result) {
		// TODO Auto-generated method stub
		Log.d(TAG, "MyAsyncTask onCancelled");
		super.onCancelled(result);
	}
	
	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();

		Log.d(TAG, "MyAsyncTask onCancelled");
	    	dialog.cancel();
	    	function.interruptTask();
	    	Toast.makeText(context, R.string.connect_timeout, Toast.LENGTH_LONG).show();
	}
	
	/** 
     * 得到自定义的progressDialog 
     * @param context 
     * @param msg 
     * @return 
     */ 
	private Dialog createLoadingDialog(Context context) {
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
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(  
                LinearLayout.LayoutParams.MATCH_PARENT,  
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局  
        return loadingDialog;  
	}
}
