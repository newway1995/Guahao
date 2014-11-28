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
	
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String USER_ID = "user_id";
	public static final String USER_INFO = "user_info";
	public static final String PAGE_COUNT = "pageCount";
	public static final String PAGE_FROM = "pageFrom";
	
	
	/**
	 * 返回所有的疾病列表
	 * @param Resources resources
	 * */
	public static String[] getBodyList(Resources resources){
		return resources.getStringArray(R.array.body_list);
	}
	
	//male
	/**
	 * 返回Male疾病完整列表
	 * @param Resources resources
	 * */
	public static String[][] getMaleSickList(Resources resources){		
		String []headList = resources.getStringArray(R.array.male_body_toubu);
		String []jingbuList = resources.getStringArray(R.array.male_body_jingbu);
		String []xiongbuList = resources.getStringArray(R.array.male_body_xiongbu);
		String []fubuList = resources.getStringArray(R.array.male_body_fubu);
		String []shengzhiqiList = resources.getStringArray(R.array.male_body_shengzhiqi);
		String []paixiebuList = resources.getStringArray(R.array.male_body_paixiebu);
		String []yaobuList = resources.getStringArray(R.array.male_body_yaobu);
		String []eryankoubiList = resources.getStringArray(R.array.male_body_eryankoubi);
		String []xiazhiList = resources.getStringArray(R.array.male_body_xiazhi);
		String []qitaList = resources.getStringArray(R.array.male_body_qita);
		
		String allList[][] = new String[][]{headList,jingbuList,xiongbuList,fubuList,shengzhiqiList,
				paixiebuList,yaobuList,eryankoubiList,xiazhiList,qitaList};
		return allList;		
	}
	
	/**
	 * 返回Male疾病完整列表
	 * @param Resources resources
	 * @param int first 第几行
	 * */
	public static String[] getMaleSickList(int first,Resources resources){
		String [][] strs = getMaleSickList(resources);
		return strs[first];
	}
	
	/**
	 * 返回Male所有疾病的解决方案
	 * @param Resources resources
	 * */
	public static String[][] getMaleSickSolution(Resources resources){
		String []headList = resources.getStringArray(R.array.male_advice_toubu);
		String []jingbuList = resources.getStringArray(R.array.male_advice_jingbu);
		String []xiongbuList = resources.getStringArray(R.array.male_advice_xiongbu);
		String []fubuList = resources.getStringArray(R.array.male_advice_fubu);
		String []shengzhiqiList = resources.getStringArray(R.array.male_advice_shengzhiqi);
		String []paixiebuList = resources.getStringArray(R.array.male_advice_paixiebu);
		String []yaobuList = resources.getStringArray(R.array.male_advice_yaobu);
		String []eryankoubiList = resources.getStringArray(R.array.male_advice_eryankoubi);
		String []xiazhiList = resources.getStringArray(R.array.male_advice_xiazhi);
		String []qitaList = resources.getStringArray(R.array.male_advice_qita);
		String allList[][] = new String[][]{headList,jingbuList,xiongbuList,fubuList,shengzhiqiList,
				paixiebuList,yaobuList,eryankoubiList,xiazhiList,qitaList};
		return allList;
	}
	
	/**
	 * 返回Male某一种疾病的解决方案
	 * @param Resources resources
	 * @param int first 第一列数据
	 * @param int second 第二列数据
	 * */
	public static String getMaleSickSolution(int first,int second,Resources resources){
		String all[][] = getMaleSickSolution(resources);
		return all[first][second];
	}
	
	//Female	
	/**
	 * 返回Male疾病完整列表
	 * @param Resources resources
	 * */
	public static String[][] getFemaleSickList(Resources resources){		
		String []headList = resources.getStringArray(R.array.female_body_toubu);
		String []jingbuList = resources.getStringArray(R.array.female_body_jingbu);
		String []xiongbuList = resources.getStringArray(R.array.female_body_xiongbu);
		String []fubuList = resources.getStringArray(R.array.female_body_fubu);
		String []shengzhiqiList = resources.getStringArray(R.array.female_body_shengzhiqi);
		String []paixiebuList = resources.getStringArray(R.array.female_body_paixiebu);
		String []yaobuList = resources.getStringArray(R.array.female_body_yaobu);
		String []eryankoubiList = resources.getStringArray(R.array.female_body_eryankoubi);
		String []xiazhiList = resources.getStringArray(R.array.female_body_xiazhi);
		String []qitaList = resources.getStringArray(R.array.female_body_qita);
		
		String allList[][] = new String[][]{headList,jingbuList,xiongbuList,fubuList,shengzhiqiList,
				paixiebuList,yaobuList,eryankoubiList,xiazhiList,qitaList};
		return allList;		
	}
	
	/**
	 * 返回Male疾病完整列表
	 * @param Resources resources
	 * @param int first 第几行
	 * */
	public static String[] getFemaleSickList(int first,Resources resources){
		String [][] strs = getFemaleSickList(resources);
		return strs[first];
	}
	
	/**
	 * 返回Male所有疾病的解决方案
	 * @param Resources resources
	 * */
	public static String[][] getFemaleSickSolution(Resources resources){
		String []headList = resources.getStringArray(R.array.female_advice_toubu);
		String []jingbuList = resources.getStringArray(R.array.female_advice_jingbu);
		String []xiongbuList = resources.getStringArray(R.array.female_advice_xiongbu);
		String []fubuList = resources.getStringArray(R.array.female_advice_fubu);
		String []shengzhiqiList = resources.getStringArray(R.array.female_advice_shengzhiqi);
		String []paixiebuList = resources.getStringArray(R.array.female_advice_paixiebu);
		String []yaobuList = resources.getStringArray(R.array.female_advice_yaobu);
		String []eryankoubiList = resources.getStringArray(R.array.female_advice_eryankoubi);
		String []xiazhiList = resources.getStringArray(R.array.female_advice_xiazhi);
		String []qitaList = resources.getStringArray(R.array.female_advice_qita);
		String allList[][] = new String[][]{headList,jingbuList,xiongbuList,fubuList,shengzhiqiList,
				paixiebuList,yaobuList,eryankoubiList,xiazhiList,qitaList};
		return allList;
	}
	
	/**
	 * 返回Male某一种疾病的解决方案
	 * @param Resources resources
	 * @param int first 第一列数据
	 * @param int second 第二列数据
	 * */
	public static String getFemaleSickSolution(int first,int second,Resources resources){
		String all[][] = getFemaleSickSolution(resources);
		return all[first][second];
	}
}
