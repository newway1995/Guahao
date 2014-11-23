package module.activity.guahao;

import constant.Constant;
import module.activity.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class FenZhenLeftFragment extends Fragment implements OnClickListener,OnTouchListener{

	private final static String TAG = "FenZhenLeftFragment";
	
	private OnBodyClick onBodyClick;//回调事件
	
	private ImageView maleImageView;
	private ImageView manImageView;//大图 body
	private ImageView femaleImageView;//小兔 icon
	private ImageView womanImageView;
	private ImageView rotateImageView;
	
	
	private Boolean manFace;
	private Boolean womanFace;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView");
		View parentView = inflater.inflate(R.layout.fragment_fenzhen_female, null);
		initView(parentView);
		initData();
		return parentView;
	}
	
	private void initView(View parentView){
		maleImageView = (ImageView)parentView.findViewById(R.id.fragment_fenzhen_male_icon);
		femaleImageView = (ImageView)parentView.findViewById(R.id.fragment_fenzhen_female_icon);
		manImageView = (ImageView)parentView.findViewById(R.id.fragment_fenzhen_male_body);
		womanImageView = (ImageView)parentView.findViewById(R.id.fragment_fenzhen_female_body);
		rotateImageView = (ImageView)parentView.findViewById(R.id.fenzhen_swipe_face);
		
		rotateImageView.setOnClickListener(this);		
		womanImageView.setOnClickListener(this);
		womanImageView.setOnTouchListener(this);
		manImageView.setOnClickListener(this);
		manImageView.setOnTouchListener(this);
		femaleImageView.setOnClickListener(this);
		maleImageView.setOnClickListener(this);
	}
	
	private void initData(){
		manFace = true;
		womanFace = true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fragment_fenzhen_female_body:
			break;
		case R.id.fragment_fenzhen_male_body:
			break;
			
		case R.id.fragment_fenzhen_female_icon:	
			showMan();
			break;
		case R.id.fragment_fenzhen_male_icon:
			showWoman();
			break;
		case R.id.fenzhen_swipe_face:			
			Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.image_rotate);
			if (manImageView.getVisibility() == View.VISIBLE) {
				if (manFace) 
					manImageView.setImageResource(R.drawable.man_back);
				else
					manImageView.setImageResource(R.drawable.man_face);
				manImageView.startAnimation(animation);
				manFace = !manFace;
			}else {
				if (womanFace) 
					womanImageView.setImageResource(R.drawable.woman_back);
				else
					womanImageView.setImageResource(R.drawable.woman_face);
				womanImageView.startAnimation(animation);
				womanFace = !womanFace;
			}
			break;
		default:
			break;
		}
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent e) {
		float x,y;
		x = e.getX();
		y = e.getY();
		inWhichPart(x, y);
		return true;
	}
	
	
	
	//不要也没关系
	private void clearAnimation(){
		femaleImageView.clearAnimation();
		maleImageView.clearAnimation();
		manImageView.clearAnimation();
		womanImageView.clearAnimation();
	}
	
	private void showMan(){
		clearAnimation();
		femaleImageView.setVisibility(View.INVISIBLE);
		womanImageView.setVisibility(View.INVISIBLE);
		maleImageView.setVisibility(View.VISIBLE);
		manImageView.setVisibility(View.VISIBLE);
	}
	
	private void showWoman(){
		clearAnimation();
		maleImageView.setVisibility(View.INVISIBLE);
		manImageView.setVisibility(View.INVISIBLE);
		femaleImageView.setVisibility(View.VISIBLE);
		womanImageView.setVisibility(View.VISIBLE);
	}

	private void inWhichPart(float x,float y){
		if (inCircle(x, y, 50, 280, 330) || inCircle(x, y, 50, 410, 340)) {
			onBodyClick.onClick(Constant.FENZHEN_SICK_CHEST);
		}else if (inCircle(x, y, 50, 340, 50)) {
			onBodyClick.onClick(Constant.FENZHEN_SICK_HEAD);
		}else if (inCircle(x, y, 50, 340, 230)) {
			onBodyClick.onClick(Constant.FENZHEN_SICK_NICK);
		}else if (inCircle(x, y, 50, 340, 170)) {
			onBodyClick.onClick(Constant.FENZHEN_SICK_EYE);
		}else if (inCircle(x, y, 80, 330, 500)) {
			onBodyClick.onClick(Constant.FENZHEN_SICK_BELLY);
		}else if (inCircle(x, y, 80, 330, 500)) {
			onBodyClick.onClick(Constant.FENZHEN_SICK_WAIST);
		}else if (inCircle(x, y, 100, 400, 900)) {
			onBodyClick.onClick(Constant.FENZHEN_SICK_LEG);
		}else if (inCircle(x, y, 50, 320, 680)) {
			onBodyClick.onClick(Constant.FENZHEN_SICK_DICK);			
		}else if (inCircle(x, y, 50, 320, 680)) {
			onBodyClick.onClick(Constant.FENZHEN_SICK_ASS);
		}		
	}
	
	/**
	 * @param float x:
	 * 				点击点的横坐标
	 * @param float y:
	 * 				点击点得纵坐标
	 * @param float r:
	 * 				半径
	 * @param int cy:
	 * 				判断点的坐标原点
	 * @param int cx:
	 * 				判断点的坐标原点
	 * */
	private boolean inCircle(float x,float y,int r,int cx,int cy){
		if (((x - cx) * (x - cx) + (y - cy) * (y - cy)) < r * r) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断当前显示的是male照片还是female
	 * @return manImageView is showing -- true , else return false
	 * */
	public boolean isMaleShowing(){		
		return manImageView.getVisibility() == View.VISIBLE;
	}
	
	public void setOnBodyClick(OnBodyClick click){
		onBodyClick = click;
	}
	
	public interface OnBodyClick{
		public void onClick(int req);
	}
}
