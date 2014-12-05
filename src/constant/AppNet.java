package constant;



 
/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:AppNet.java
 * @Package:constant
 * @time:下午4:37:00 2014-12-5
 * @useage:面向系统内部,保存一些网络变量
 */
public class AppNet {
	private static class SingletonHolder{
		private static AppNet instance = new AppNet();
	}
	
	/**
	 * 单例模式
	 * */
	public static AppNet getInstance(){
		return SingletonHolder.instance;
	}
	
	private static final String HTTP = "http://";
	public static final String HTTPS = "https://";
	private static final String DOMIN = "1.newway.sinaapp.com/";
	private static final String APIV1 = "guahao/index.php";//接口
	public static final String URL = HTTP + DOMIN + APIV1;
	
	public static class Access{
		public static final int POST = 0;
		public static final int GET = 1;
	}	
		
}
