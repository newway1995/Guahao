package common.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;


/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:NetHandler.java
 * @Package:common.util
 * @time:下午11:17:09 2014-11-24
 * @useage:网络状态判断
 */
public class NetHandler {
	/** 
	 * 网络是否可用 
	 *  
	 * @param activity 
	 * @return 
	 */  
	public static boolean isNetworkAvailable(Context context) {  
		String TAG="isisNetworkAvailable";
		
	    ConnectivityManager connectivity = (ConnectivityManager) context  
	            .getSystemService(Context.CONNECTIVITY_SERVICE);  
	    if (connectivity == null) {  
	    } else {  
	        NetworkInfo[] info = connectivity.getAllNetworkInfo();  
	        if (info != null) {  
	            for (int i = 0; i < info.length; i++) {  
	                if (info[i].getState() == NetworkInfo.State.CONNECTED) { 
	                	String MSG="Context="+context;
	                	Log.d(TAG, MSG);
	                    return true;  
	                }  
	            }  
	        }  
	    }  
	    String MSG="Context="+context;
		Log.d(TAG, MSG);
	    return false;  
	}  


	/** 
	 * GPS是否打开 
	 *  
	 * @param context 
	 * @return 
	 */  
	public static boolean isGpsEnabled(Context context) {  
		String TAG="isGpsEnabled";
	    LocationManager locationManager = ((LocationManager) context  
	            .getSystemService(Context.LOCATION_SERVICE));  
	    List<String> accessibleProviders = locationManager.getProviders(true);
	    String MSG="accessibleProviders="+accessibleProviders;
		Log.d(TAG, MSG);
	    return accessibleProviders != null && accessibleProviders.size() > 0;  
	}  

	/** 
	 * wifi是否打开 
	 */  
	public static boolean isWifiEnabled(Context context) { 
		String TAG="isWifiEnabled";
	    ConnectivityManager mgrConn = (ConnectivityManager) context  
	            .getSystemService(Context.CONNECTIVITY_SERVICE);  
	    TelephonyManager mgrTel = (TelephonyManager) context  
	            .getSystemService(Context.TELEPHONY_SERVICE);  
	   Boolean a=(mgrConn.getActiveNetworkInfo() != null && mgrConn  
	            .getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) || mgrTel  
	            .getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS;
	   String MSG="wifi是否打开"+a;
	    Log.d(TAG, MSG);
	    return a;
	}  

	/** 
	 * 判断当前网络是否是wifi网络 
	 * if(activeNetInfo.getType()==ConnectivityManager.TYPE_MOBILE) {  
	 *  
	 * @param context 
	 * @return boolean 
	 */  
	public static boolean isWifi(Context context) { 
		String TAG="isWifi";
	    ConnectivityManager connectivityManager = (ConnectivityManager) context  
	            .getSystemService(Context.CONNECTIVITY_SERVICE);  
	    NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();  
	    if (activeNetInfo != null  
	            && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) { 
	    	String  MSG="当前网络是否是wifi网络=true";
	    	Log.d(TAG, MSG);
	        return true;  
	    }  
		String  MSG="当前网络是否是wifi网络=false";
		Log.d(TAG, MSG);
	    return false;  
	}  

	/** 
	 * 判断当前网络是否3G网络 
	 *  
	 * @param context 
	 * @return boolean 
	 */  
	public static boolean is3G(Context context) { 
		String TAG="is3G";
	    ConnectivityManager connectivityManager = (ConnectivityManager) context  
	            .getSystemService(Context.CONNECTIVITY_SERVICE);  
	    NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();  
	    if (activeNetInfo != null  
	            && activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {  
	    	String MSG="当前网络是否3G网络=true";
	    	Log.d(TAG, MSG);
	        return true;  
	    }
	    String MSG="当前网络是否3G网络=true";
		Log.d(TAG, MSG); 
	    return false;  
	}  



	/**
	 * 判断是否有网络
	 * @return
	 */
	public static boolean netJudge(Context context)
	{
		String TAG="netJudge";
		boolean connect = true;
		
		ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

		if( cm == null ){
		   return false;
		}
		NetworkInfo netinfo = cm.getActiveNetworkInfo();
		if (netinfo == null ){
		   return false;
		}
		if(!netinfo.isConnected()){
		   connect = false;
		}
		
		final NetworkInfo[] infos = cm.getAllNetworkInfo();

        for (final NetworkInfo info : infos) {
            if (info != null) {
                if (info.isConnectedOrConnecting()) {
                    final android.net.NetworkInfo.State networkState = info.getState();
                    if (android.net.NetworkInfo.State.CONNECTED == networkState
                            || android.net.NetworkInfo.State.CONNECTING == networkState) {
                        // if (Logging.isEnabled)
                        {
                            
                            try {
                                 InetAddress.getByName( "192.168.1.27").isReachable(1000);
                                 String MSG="网络连接=true";
                                 Log.d(TAG, MSG);
                            } catch (UnknownHostException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                                String MSG="网络连接=false";
                                Log.d(TAG, MSG);
                            	return false;
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                                String MSG="网络连接=false";
                                Log.d(TAG, MSG);
                            	return false;
                            }
                        }
                        
                    }
                }
            }
        }
        return connect;
	}
	
	/**
	 * netGet方法
	 * @param url
	 * @return
	 */
	public static String netGet(String url, Context context)
	{
		String result = "";
		if(!netJudge(context))
			return result;
		HttpClient httpClient = new DefaultHttpClient();//生成一个Http客户端对象;
		HttpGet httpGet  = new HttpGet(url);//生成一个请求对象
		try
		{
			HttpResponse httpResponse = httpClient.execute(httpGet);//使用Http客户端发送请求对象
			HttpEntity httpEntity = httpResponse.getEntity();
			InputStream inputStream = httpEntity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String line = "";
			while((line = reader.readLine()) != null)
			{
				result = result + line;
			}
//	            result = result.substring(0, result.lastIndexOf("}")+1);
			byte[] b = result.getBytes();
			result = new String(b, "UTF-8");
			System.out.println("result = " + result);//输出结果
			
		}
	    catch(Exception e)
		{
//		    	String MSG="netGet="+result+"url="+url;
//				Log.d(TAG, MSG);  
	    	e.printStackTrace();
		}
//			String MSG="netGet="+result+"url="+url;
//			Log.d(TAG, MSG);
       
		return result;
	}
	/**
	 * Post方法
	 * @param url
	 * @param params
	 * @return
	 */
	public static String netPost(String url, List<NameValuePair> params, Context context)
	{
				
		String result = "";
		try {
            HttpEntity requestHttpEntity = new UrlEncodedFormEntity(params,HTTP.UTF_8);//对参数进行编码操作
            //生成一个post请求对象
            
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(requestHttpEntity);
            InputStream inputStream=null;
            //生成一个http客户端对象
            HttpClient httpClient = new DefaultHttpClient();//发送请求
            try {
                HttpResponse httpResponse = httpClient.execute(httpPost);//接收响应
                HttpEntity httpEntity = httpResponse.getEntity();//取出响应
                //客户端收到响应的信息流
                inputStream = httpEntity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                
                String line = "";
                while((line = reader.readLine()) != null){
                    result = result + line;
                }
               
            } catch (Exception e) {
                e.printStackTrace();
            } finally{//最后一定要关闭输入流
                try{
                    inputStream.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		System.out.println("post result = " + result);
		 return result;
    }
    /**
     * getFile方法
     * @param uri
     * @return
     */
	public static String getFile(String uri){
    	String s = "";
    	String TAG="getFile";
        try { 
            URL url = new URL(uri); 
            URLConnection conexion = url.openConnection(); 
            conexion.connect(); 
            InputStream input = new BufferedInputStream(url.openStream()); 
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			String line = "";
			while((line = reader.readLine()) != null)
			{
				s = s + line;
			
            reader.close();
            input.close(); 
        } }
        catch (Exception e) { 
            Log.e("error",e.getMessage().toString()); 
            System.out.println(e.getMessage().toString()); 
            String MSG="message="+e.getMessage().toString()+"url"+uri;
            Log.d(TAG, MSG);
        } 
        String MSG="url"+uri;
        Log.d(TAG, MSG);
        return s; 

	}
	/**
	 * getPic方法
	 * @param url
	 * @return
	 */
	public static Bitmap getPic(String url) { 
		String TAG="getPic";
		if(url == null)
			return null;
		
	    URL myFileUrl = null;   
	    Bitmap bitmap = null;   
	    try {   
	    	myFileUrl = new URL(url);   
	    } catch (MalformedURLException e) {   
	    e.printStackTrace();   
	    }   
	    try {   
	    	HttpURLConnection conn = (HttpURLConnection)myFileUrl.openConnection();   
	    	conn.setDoInput(true);   
	    	conn.connect();   
	    	InputStream is = conn.getInputStream();   
	    	bitmap = BitmapFactory.decodeStream(is);   
	    	is.close();   
	    	conn.disconnect();
	    } catch (IOException e) {  
	    	String MSG="bitmap="+bitmap+"URL="+url+"e="+e;
	 	    Log.d(TAG, MSG);
	    e.printStackTrace();   
	    }
	    String MSG="bitmap="+bitmap+"URL="+url;
	    Log.d(TAG, MSG);
	    return bitmap;   
    }
}
