package module.activity.guahao;

import module.activity.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class FenZhenLeftFragment extends Fragment implements OnClickListener{

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
		manImageView.setOnClickListener(this);
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
}
