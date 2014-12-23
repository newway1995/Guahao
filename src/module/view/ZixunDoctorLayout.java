package module.view;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kymjs.aframe.bitmap.KJBitmap;

import common.receiver.NetResultInterface;
import constant.Constant;

import module.activity.R;
import module.activity.zixun.ConsultActivity;
import module.entity.Doctor;
import module.model.HospitalModel;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class ZixunDoctorLayout{
	
	private View view;
	private Context context;
	private LayoutInflater inflater;
	private Doctor clickDoctor;//点击的doctor
	private ArrayList<HashMap<String, String>> list;
	private int group;
	private KJBitmap kjBitmap;
	/**
	 * 控件监听
	 * */
	//第一组
	private ImageView zixun_doctor_portrait1;
	private TextView zixun_doctor_name1;
	private TextView zixun_doctor_zhuzhi1;
	private TextView zixun_doctor_dept1;
	private TextView zixun_which_hospital1;
	//第二组
	private ImageView zixun_doctor_portrait2;
	private TextView zixun_doctor_name2;
	private TextView zixun_doctor_zhuzhi2;
	private TextView zixun_doctor_dept2;
	private TextView zixun_which_hospital2;
	//第三组
	private ImageView zixun_doctor_portrait3;
	private TextView zixun_doctor_name3;
	private TextView zixun_doctor_zhuzhi3;
	private TextView zixun_doctor_dept3;
	private TextView zixun_which_hospital3;
	//第四组
	private ImageView zixun_doctor_portrait4;
	private TextView zixun_doctor_name4;
	private TextView zixun_doctor_zhuzhi4;
	private TextView zixun_doctor_dept4;
	private TextView zixun_which_hospital4;
	
	public ZixunDoctorLayout(Context context,int group) {
		this.context = context;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.item_zixun_scroll_layout, null);
		kjBitmap = KJBitmap.create();
		this.group = group;
		initView();
		initData(this.group);
		onClick();
	}
	
	/**
	 * 设置监听
	 * */
	public View getView(){
		return view;
	}
	
	/**
	 * 初始化
	 * */
	private void initView(){
		 zixun_doctor_portrait1 = (ImageView)view.findViewById(R.id.zixun_doctor_portrait1);
		 zixun_doctor_name1 = (TextView)view.findViewById(R.id.zixun_doctor_name1);
		 zixun_doctor_zhuzhi1 = (TextView)view.findViewById(R.id.zixun_doctor_zhuzhi1);
		 zixun_doctor_dept1 = (TextView)view.findViewById(R.id.zixun_doctor_dept1);
		 zixun_which_hospital1 = (TextView)view.findViewById(R.id.zixun_which_hospital1);
		 
		 zixun_doctor_portrait2 = (ImageView)view.findViewById(R.id.zixun_doctor_portrait2);
		 zixun_doctor_name2 = (TextView)view.findViewById(R.id.zixun_doctor_name2);
		 zixun_doctor_zhuzhi2 = (TextView)view.findViewById(R.id.zixun_doctor_zhuzhi2);
		 zixun_doctor_dept2 = (TextView)view.findViewById(R.id.zixun_doctor_dept2);
		 zixun_which_hospital2 = (TextView)view.findViewById(R.id.zixun_which_hospital2);
		 
		 zixun_doctor_portrait3 = (ImageView)view.findViewById(R.id.zixun_doctor_portrait3);
		 zixun_doctor_name3 = (TextView)view.findViewById(R.id.zixun_doctor_name3);
		 zixun_doctor_zhuzhi3 = (TextView)view.findViewById(R.id.zixun_doctor_zhuzhi3);
		 zixun_doctor_dept3 = (TextView)view.findViewById(R.id.zixun_doctor_dept3);
		 zixun_which_hospital3 = (TextView)view.findViewById(R.id.zixun_which_hospital3);
		 
		 zixun_doctor_portrait4 = (ImageView)view.findViewById(R.id.zixun_doctor_portrait4);
		 zixun_doctor_name4 = (TextView)view.findViewById(R.id.zixun_doctor_name4);
		 zixun_doctor_zhuzhi4 = (TextView)view.findViewById(R.id.zixun_doctor_zhuzhi4);
		 zixun_doctor_dept4 = (TextView)view.findViewById(R.id.zixun_doctor_dept4);
		 zixun_which_hospital4 = (TextView)view.findViewById(R.id.zixun_which_hospital4);
		 
		 list = new ArrayList<HashMap<String,String>>();
	}
	
	private void initData(final int group){
		HospitalModel.getInstance().getDoctor(context,false, 1, 0, new NetResultInterface() {
			
			@Override
			public void parseResult(String result) {
				JSONObject jObject;
				try {
					jObject = new JSONObject(result);
					JSONArray jArray = jObject.getJSONArray("doctor");
					refresh(jArray, group);							
				} catch (JSONException e) {					
					e.printStackTrace();
				} 
			}
		});
	}
	
	private void refresh(JSONArray jArray,int group) throws JSONException{
		list = new ArrayList<HashMap<String,String>>();
		for (int i = (group - 1) * 4; i < jArray.length(); i++) {//循环便利
			JSONObject jsonObject = jArray.getJSONObject(i);
			HashMap< String, String> map = new HashMap<String, String>();
			map.put("id", jsonObject.getString("id"));//医生id
			map.put("name", jsonObject.getString("name"));
			map.put("hospital_id", jsonObject.getString("hospital_id"));
			map.put("hospital_name", jsonObject.getString("hospital_name"));
			map.put("section_id", jsonObject.getString("section_id"));
			map.put("section_name", jsonObject.getString("section_name"));
			map.put("img", jsonObject.getString("img"));
			map.put("ticket_num", jsonObject.getString("ticket_num"));
			map.put("level", jsonObject.getString("level"));
			map.put("favorite", jsonObject.getString("favorite"));
			list.add(map);
		}
		kjBitmap.display(zixun_doctor_portrait1, Constant.IMAGE_DOCTOR_PATH_SUFFIX + list.get(0).get("img").substring(4),96,96);
		kjBitmap.display(zixun_doctor_portrait2, Constant.IMAGE_DOCTOR_PATH_SUFFIX + list.get(1).get("img").substring(4),96,96);
		kjBitmap.display(zixun_doctor_portrait3, Constant.IMAGE_DOCTOR_PATH_SUFFIX + list.get(2).get("img").substring(4),96,96);
		kjBitmap.display(zixun_doctor_portrait4, Constant.IMAGE_DOCTOR_PATH_SUFFIX + list.get(3).get("img").substring(4),96,96);
		zixun_doctor_name1.setText(list.get(0).get("name"));
		zixun_doctor_name2.setText(list.get(1).get("name"));
		zixun_doctor_name3.setText(list.get(2).get("name"));
		zixun_doctor_name4.setText(list.get(3).get("name"));
		zixun_doctor_zhuzhi1.setText( (list.get(0).get("level")).equals(0) ? "主治医生":"专家" );
		zixun_doctor_zhuzhi2.setText( (list.get(1).get("level")).equals(0) ? "主治医生":"专家" );
		zixun_doctor_zhuzhi3.setText( (list.get(2).get("level")).equals(0) ? "主治医生":"专家" );
		zixun_doctor_zhuzhi4.setText( (list.get(3).get("level")).equals(0) ? "主治医生":"专家" );
		zixun_doctor_dept1.setText(list.get(0).get("section_name"));
		zixun_doctor_dept2.setText(list.get(1).get("section_name"));
		zixun_doctor_dept3.setText(list.get(2).get("section_name"));
		zixun_doctor_dept4.setText(list.get(3).get("section_name"));
		zixun_which_hospital1.setText(list.get(0).get("hospital_name"));
		zixun_which_hospital2.setText(list.get(1).get("hospital_name"));
		zixun_which_hospital3.setText(list.get(2).get("hospital_name"));
		zixun_which_hospital4.setText(list.get(3).get("hospital_name"));
	}
	
	private void onClick(){
		//第一个头像被点击
		zixun_doctor_portrait1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				Activity currentActivity = (Activity)v.getContext();
				HashMap<String, String> map = list.get(0);
				clickDoctor = new Doctor(Integer.parseInt(map.get("id")), map.get("name"), map.get("section_name"), 
						map.get("level"), map.get("hospital_name"), map.get("favorite"), map.get("img"));
				Intent intent = new Intent(currentActivity,ConsultActivity.class);
				intent.putExtra("consultDoctor", clickDoctor);
				currentActivity.startActivity(intent);
			}
		});
		
		//第二个头像被点击
		zixun_doctor_portrait2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Activity currentActivity = (Activity)v.getContext();
				HashMap<String, String> map = list.get(1);
				clickDoctor = new Doctor(Integer.parseInt(map.get("id")), map.get("name"), map.get("section_name"), 
						map.get("level"), map.get("hospital_name"), map.get("favorite"), map.get("img"));
				Intent intent = new Intent(currentActivity,ConsultActivity.class);
				intent.putExtra("consultDoctor", clickDoctor);
				currentActivity.startActivity(intent);
			}
		});
		
		//第三个头像被点击
		zixun_doctor_portrait3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Activity currentActivity = (Activity)v.getContext();
				HashMap<String, String> map = list.get(2);
				clickDoctor = new Doctor(Integer.parseInt(map.get("id")), map.get("name"), map.get("section_name"), 
						map.get("level"), map.get("hospital_name"), map.get("favorite"), map.get("img"));
				Intent intent = new Intent(currentActivity,ConsultActivity.class);
				intent.putExtra("consultDoctor", clickDoctor);
				currentActivity.startActivity(intent);
			}
		});
		
		//第四个头像被点击
		zixun_doctor_portrait4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Activity currentActivity = (Activity)v.getContext();
				HashMap<String, String> map = list.get(3);
				clickDoctor = new Doctor(Integer.parseInt(map.get("id")), map.get("name"), map.get("section_name"), 
						map.get("level"), map.get("hospital_name"), map.get("favorite"), map.get("img"));
				Intent intent = new Intent(currentActivity,ConsultActivity.class);
				intent.putExtra("consultDoctor", clickDoctor);
				currentActivity.startActivity(intent);
			}
		});		
	}
	
}
