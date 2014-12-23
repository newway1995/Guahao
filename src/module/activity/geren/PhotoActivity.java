package module.activity.geren;
/**
 * @author yellow
 * 
 * 上传用户头像到服务器upload文件夹下，以用户id.jpg命名
 * 服务器端负责填写user表中的image字段的路径。
 */

import java.io.File;
import java.io.FileOutputStream;
import module.activity.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import common.util.*;
import constant.Constant;

public class PhotoActivity extends Activity {
	public static final String TAG = "PhotoActivity";
	public static final int NONE = 0;
	public static final int PHOTOGRAPH = 1;
	public static final int PHOTOZOOM = 2; 
	public static final int PHOTORESOULT = 3;
	public String saveName ;
	public static final String IMAGE_UNSPECIFIED = "image/*";

	Button button0 = null;
	Button button1 = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo_choose);
		button0 = (Button) findViewById(R.id.btn_01);
		button1 = (Button) findViewById(R.id.btn_02);

		button0.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);//打开相册
				intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
						IMAGE_UNSPECIFIED);
				startActivityForResult(intent, PHOTOZOOM);
			}
		});

		button1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//打开照相机
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
						Environment.getExternalStorageDirectory(), "temp.jpg")));//将图片保存在此目录下再进行裁剪				
				startActivityForResult(intent, PHOTOGRAPH);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == NONE)
			return;
		if (requestCode == PHOTOGRAPH) {  //照相之后回调

			File picture = new File(Environment.getExternalStorageDirectory()
					+ "/temp.jpg");
			Log.d(TAG,picture.getPath());
			startPhotoZoom(Uri.fromFile(picture));
		}

		if (data == null)
			return;
		if (requestCode == PHOTOZOOM) {
			startPhotoZoom(data.getData());
			Log.d(TAG,data.getData().toString());
		}
		if (requestCode == PHOTORESOULT) {	//裁剪之后回调
			Bundle extras = data.getExtras();
			if (extras != null) {
				Bitmap photo = extras.getParcelable("data");
				String filename = Environment.getExternalStorageDirectory().toString()+"/temp.jpg";
				FileOutputStream stream;
				try {
					File file = new File(filename);
					if(file!=null)
					{
						file.delete();
					}
					stream = new FileOutputStream(filename);
					photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
					saveName = CacheHandler.readCache(this, Constant.USER_INFO, Constant.USER_ID)+".jpg";
					boolean flag = SyncHttp.uploadFile("http://192.168.24.1:8081/SystemDesign/SystemDesign/receive_file.php",filename,saveName);//上传图片
					if(flag ==true)
					{
						startActivity(new Intent(PhotoActivity.this,UserInfoActivity.class));
					}
					Log.d(TAG,saveName);
					Log.d(TAG,filename);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			   
			}

		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 64);
		intent.putExtra("outputY", 64);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, PHOTORESOULT);
	}

}
