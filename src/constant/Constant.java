package constant;

import module.activity.R;
import android.content.res.Resources;

public class Constant {
	public static final String APP_NAME = "Guahao";
	
	public static final int FENZHEN_SICK_HEAD = 0;//头部
	public static final int FENZHEN_SICK_NICK = 1;//颈部
	public static final int FENZHEN_SICK_CHEST = 2;//胸部
	public static final int FENZHEN_SICK_BELLY = 3;//腹部
	public static final int FENZHEN_SICK_DICK = 4;//生殖器
	public static final int FENZHEN_SICK_ASS = 5;//排泄部位
	public static final int FENZHEN_SICK_WAIST = 6;//腰部
	public static final int FENZHEN_SICK_EYE = 7;//耳眼口鼻
	public static final int FENZHEN_SICK_LEG = 8;//下肢
	
	
	/**
	 * 返回疾病完整列表
	 * @param Resources resources
	 * */
	public static String[][] getSickList(Resources resources){		
		String []headList = resources.getStringArray(R.array.body_toubu);
		String []jingbuList = resources.getStringArray(R.array.body_jingbu);
		String []xiongbuList = resources.getStringArray(R.array.body_xiongbu);
		String []fubuList = resources.getStringArray(R.array.body_fubu);
		String []shengzhiqiList = resources.getStringArray(R.array.body_shengzhiqi);
		String []paixiebuList = resources.getStringArray(R.array.body_paixiebu);
		String []yaobuList = resources.getStringArray(R.array.body_yaobu);
		String []eryankoubiList = resources.getStringArray(R.array.body_eryankoubi);
		String []xiazhiList = resources.getStringArray(R.array.body_xiazhi);
		String []qitaList = resources.getStringArray(R.array.body_qita);
		
		String allList[][] = new String[][]{headList,jingbuList,xiongbuList,fubuList,shengzhiqiList,
				paixiebuList,yaobuList,eryankoubiList,xiazhiList,qitaList};
		return allList;		
	}
	
	/**
	 * 返回所有的疾病列表
	 * @param Resources resources
	 * */
	public static String[] getBodyList(Resources resources){
		return resources.getStringArray(R.array.body_list);
	}
	
	/**
	 * 返回疾病完整列表
	 * @param Resources resources
	 * @param int first 第几行
	 * */
	public static String[] getSickList(int first,Resources resources){
		String [][] strs = getSickList(resources);
		return strs[first];
	}
	
	/**
	 * 返回所有疾病的解决方案
	 * @param Resources resources
	 * */
	public static String[][] getSickSolution(Resources resources){
		String []headList = resources.getStringArray(R.array.advice_toubu);
		String []jingbuList = resources.getStringArray(R.array.advice_jingbu);
		String []xiongbuList = resources.getStringArray(R.array.advice_xiongbu);
		String []fubuList = resources.getStringArray(R.array.advice_fubu);
		String []shengzhiqiList = resources.getStringArray(R.array.advice_shengzhiqi);
		String []paixiebuList = resources.getStringArray(R.array.advice_paixiebu);
		String []yaobuList = resources.getStringArray(R.array.advice_yaobu);
		String []eryankoubiList = resources.getStringArray(R.array.advice_eryankoubi);
		String []xiazhiList = resources.getStringArray(R.array.advice_xiazhi);
		String []qitaList = resources.getStringArray(R.array.advice_qita);
		String allList[][] = new String[][]{headList,jingbuList,xiongbuList,fubuList,shengzhiqiList,
				paixiebuList,yaobuList,eryankoubiList,xiazhiList,qitaList};
		return allList;
	}
	
	/**
	 * 返回某一种疾病的解决方案
	 * @param Resources resources
	 * @param int first 第一列数据
	 * @param int second 第二列数据
	 * */
	public static String getSickSolution(int first,int second,Resources resources){
		String all[][] = getSickSolution(resources);
		return all[first][second];
	}
}
