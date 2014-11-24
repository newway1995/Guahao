package common.receiver;

import module.activity.R;
import common.util.NetHandler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.widget.Toast;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:NetWorkReceiver.java
 * @Package:common.receiver
 * @time:下午11:17:24 2014-11-24
 * @useage:监听网络变化  提示用户
 */
public class NetWorkReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Resources resources = context.getResources();
		if (NetHandler.isNetworkAvailable(context)) {
			if (NetHandler.isWifi(context)) {
				Toast.makeText(context, resources.getString(R.string.change_wifi), Toast.LENGTH_SHORT).show();
			}else if (NetHandler.is3G(context)) {
				Toast.makeText(context, resources.getString(R.string.change_3g), Toast.LENGTH_SHORT).show();
			}
		}else {
			Toast.makeText(context, resources.getString(R.string.network_error), Toast.LENGTH_SHORT).show();
		}
	}
	
}
