package constant;

import android.app.Application;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:MyApplication.java
 * @Package:constant
 * @time:下午10:53:01
 * @useage:Application本身既是类也是单例的--即所有的类共享一个,且只存在一个实例
 */
public class MyApplication extends Application{	
	
	@Override
	public void onCreate() {
		super.onCreate();
	}
}
