package common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.util.Log;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * 上午10:38:20
 * 读取缓存
 */
public class CacheHandler {
	private static final String TAG = "DataCache";
	private String SDPATH = Environment.getExternalStorageDirectory() + "/"; 
	/**
	 * 写入缓存
	 * @param context 上下文
	 * @param cacheName 缓存文件名
	 * @param fieldName 缓存字段名称
	 * @param data 存入的数据
	 * @return 是否写入成功
	 */
	public static boolean writeCache(Context context, String cacheName,String fieldName, String data){
		Log.d(TAG, "writeCache");
		SharedPreferences preferences = context.getSharedPreferences(cacheName, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(fieldName, data);
		editor.commit();
		return true;
	}

	/**
	 * 是否存在该缓存
	 * @param context 上下文
	 * @param cacheName 缓存文件名
	 * @param fieldName 缓存字段名称
	 * @return
	 */
	public static boolean judgeCache(Context context, String cacheName, String fieldName) {
		Log.d(TAG, "judgeCache");
		SharedPreferences preferences = context.getSharedPreferences(cacheName, Context.MODE_PRIVATE);
		if(preferences.contains(fieldName))
			return true;
		return false;
	}
	
	public static void clearCache(Context context, String cacheName){
		SharedPreferences preferences = context.getSharedPreferences(cacheName, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.clear();
		editor.commit();
	}
	
	/**
	 * 读缓存
	 * @param context 上下文
	 * @param cacheName 缓存文件名称
	 * @param fieldName 缓存字段名称
	 * @return 是否读成功
	 */
	public static String readCache(Context context, String cacheName, String fieldName){
		Log.d(TAG, "readCache");
		SharedPreferences preferences = context.getSharedPreferences(cacheName, Context.MODE_PRIVATE);
		String result = preferences.getString(fieldName, "");
		return result;
	}
	public InputStream getInputStreamFromURL(String urlStr) {  
        HttpURLConnection urlConn = null;  
        InputStream inputStream = null;  
        try {  
            URL url = new URL(urlStr);  
            urlConn = (HttpURLConnection)url.openConnection();  
            inputStream = urlConn.getInputStream();  
              
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
          
        return inputStream;  
    }  
	public int downFile(String urlStr, String path, String fileName){  
        InputStream inputStream = null;  
        try {  
                inputStream = getInputStreamFromURL(urlStr);  
                File resultFile = write2SDFromInput(path, fileName, inputStream);  
                if(resultFile == null){  
                    return -1;  
                }  
         
        }   
        catch (Exception e) {  
            e.printStackTrace();  
            return -1;  
        }  
        finally{  
            try {  
                inputStream.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return 0;  
    }  
	
	public File write2SDFromInput(String path,String fileName,InputStream input){  
        File file = null;  
        int FILESIZE = 4 * 1024;  
        OutputStream output = null;  
        try {  
            createSDDir(path);  
            file = createSDFile(path + fileName);  
            
            output = new FileOutputStream(file);  
                            byte[] buffer = new byte[FILESIZE];  
  
            /*真机测试，这段可能有问题，请采用下面网友提供的  
                            while((input.read(buffer)) != -1){  
                output.write(buffer);  
            }  
                            */  
  
                           /* 网友提供 begin */  
                           int length;  
                           while((length=(input.read(buffer))) >0){  
                                 output.write(buffer,0,length);  
                           }  
                           /* 网友提供 end */  
  
            output.flush();  
        }   
        catch (Exception e) {  
            e.printStackTrace();  
        }  
        finally{  
            try {  
                output.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return file;  
    }  
	public File createSDFile(String fileName) throws IOException{  
        File file = new File(SDPATH + fileName);  
        file.createNewFile();  
        return file;  
    }  

    public File createSDDir(String dirName){  
        File dir = new File(SDPATH + dirName);  
        dir.mkdir();  
        return dir;  
    } 
}
