package common.util;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * 上午10:24:02
 * 网络异步接口
 */
public interface AsyncInter {
	//异步操作前
	public void onPreExecute();
	//异步操作后
	public void onPostExecute();
	//异步操作中
	public void doInBackground();
	//异步操作中断
	public void interruptTask();
}
