package constant;

import android.app.Application;

public class MyApplication extends Application{

	private static MyApplication instance = null;
	/**
	 * 单例模式 创建唯一的实例
	 * */
	public static MyApplication getInstance(){
		if (instance == null) {
			synchronized (MyApplication.class) {
				if (instance == null) {
					instance = new MyApplication();
				}
			}
		}
		return instance;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

}
