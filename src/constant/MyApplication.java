package constant;

import com.avos.avoscloud.AVOSCloud;

import common.util.CacheHandler;

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
	
	public static String selected_hospital = "";
	public static String selected_section = "";
	
	@Override
	public void onCreate() {
		super.onCreate();
		AVOSCloud.initialize(this, "d34hzchjjp9zsnxt4q0mhncpwzmnxthcrff9eeaspf53v0nh",
		        "mdpmpk308z2baf3x4mjf725hbr5acscggh5d34jx56as2dcm");
	}
	
	/**
	 * 判断是否登录
	 * */
	public boolean isLogin(){
		return !(CacheHandler.readCache(getApplicationContext(), Constant.USER_INFO, Constant.PASSWORD).equals(""));
	}
	
}
