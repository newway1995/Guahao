package module.model;

import android.util.Log;

public class UserModel {
	private static final String TAG = "UserModel";
	
	private static boolean isLogin = false;
	private static UserModel instance;
	/**
	 * 单例模式
	 * */
	public static UserModel getInstance(){
		if (instance == null) {
			synchronized (FaxianModel.class) {
				if (instance == null) {
					instance = new UserModel();
				}
			}
		}
		return instance;
	} 
	
	public void login(){
		Log.d(TAG, "is logining!");
	}
	
	public boolean getIsLogin(){
		return isLogin;
	}
	
	public void setIsLogin(boolean flag){
		isLogin = flag;
	}
}
