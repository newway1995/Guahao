package constant;

import java.util.HashMap;

import common.util.CacheHandler;

import module.activity.R;
import module.entity.Doctor;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:Constant.java
 * @Package:constant
 * @time:上午12:29:12 2014-12-18
 * @useage:TODO
 */
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
	
	//保存字段
	public static final String IS_LOGIN = "isLogin";//是否登录
	public static final String LOGGED = "yes";//已经登录
	public static final String UNLOGGED = "no";//没有登录
	public static final String USERNAME = "username";//当前用户名
	public static final String PASSWORD = "password";//当前用户的密码
	
	public static final String USER_ID = "user_id";//当前用户的ID
	public static final String USER_INFO = "user_info";//当前用户的其他信息	
	public static final String USER_HOSPITAL_ID = "hid";//当前用户选择的医院id
	public static final String USER_HOSPITAL_NAME = "hname";//当前用户选择的医院name
	public static final String USER_SECTION_ID = "sid";//当前用户选择的科室id
	public static final String USER_SECTION_NAME = "sname";//当前用户选择的科室name
	public static final String USER_DOCTOR_ID = "did";//当前用户选择的医生id
	public static final String USER_DOCTOR_NAME = "dname";//当前用户选择的医生name	
	public static final String USER_DOCTOR_IMG = "dimg";//当前用户选择的医生img
	public static final String USER_DOCTOR_LEVEL = "dlevel";//当前用户选择的医生level
	public static final String USER_DOCTOR_FAVORITE = "dfavorite";//当前用户选择的医生擅长
	public static final String USER_DOCTOR_TICKET = "ticket_num";//当前用户选择的医生的剩余票数
	public static final String USER_ORDER_TIME = "order_time" ;//当前用户预约的时间
	public static final String USER_ORDER_KIND = "order_kind"; //当前用户预约的门诊种类
	public static final String USER_ORDER_FEE = "order_fee"; //当前用户预约的门诊费用
	public static final String USER_ORDER_HISTORY_HOSPITAL = "history_hospital_name";
	public static final String USER_ORDER_HISTORY_SECTION = "history_section_name";
	public static final String USER_ORDER_HISTORY_DOCTOR = "history_doctort_name";
	public static final String USER_ORDER_HISTORY_TIME = "history_order_time";
	public static final String USER_ORDER_HISTORY_KIND = "history_order_kind";
	//net参数常量
	public static final String PAGE_COUNT = "pageCount";//从服务器获取多少页面
	public static final String PAGE_FROM = "pageFrom";	//从第几页开始获取
	public static final String PROVINCE = "province";
	public static final String CITY = "city";
	public static final String CITY_ID = "city_id";
	public static final String HID = "hid";
	public static final String SID = "sid";
	public static final String FRI = "周五上午";
	public static final String MON = "周一上午";
			
	
	
	//ImagePath
	public static final String IMAGE_DOCTOR_PATH_SUFFIX = "http://1.sysicode.sinaapp.com/doctor/";
	
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
		String []headList = resources.getStringArray(R.array.male_solution_toubu);
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
		String []headList = resources.getStringArray(R.array.male_solution_toubu);//男女这一项都是一样的
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
	
	/**
	 * 返回所有的省份名
	 * */
	public static String[] getProvinceList(Resources res){
		return res.getStringArray(R.array.province);
	}
	
	/**
	 * 返回省份下面的城市名
	 * */
	public static String[][] getCitiesList(Resources res){
		return null;
	}
	
	/**
	 * 是否登录
	 * */
	public static boolean isLogin(Context context){
		return CacheHandler.readCache(context, Constant.USER_INFO, Constant.IS_LOGIN).equals(Constant.LOGGED);
	}
	/**
	 * 设置登录
	 * @param context 上下文
	 * @param isLogin 只能取值为Constant.Logged or Constant.UnLogged
	 * */
	public static void setLogin(Context context,String isLogin){
		CacheHandler.writeCache(context, Constant.USER_INFO, Constant.IS_LOGIN, isLogin);
	}	
	
	/**
	 * @param doctor
	 * 保存doctor的信息
	 */
	public static void saveDoctor(Context context,HashMap<String, String>map){
		CacheHandler.writeCache(context, Constant.USER_INFO, Constant.USER_DOCTOR_ID, map.get("id"));
		CacheHandler.writeCache(context, Constant.USER_INFO, Constant.USER_DOCTOR_IMG, map.get("img"));
		CacheHandler.writeCache(context, Constant.USER_INFO, Constant.USER_DOCTOR_NAME, map.get("name"));
		CacheHandler.writeCache(context, Constant.USER_INFO, Constant.USER_DOCTOR_FAVORITE, map.get("favorite"));
		CacheHandler.writeCache(context, Constant.USER_INFO, Constant.USER_DOCTOR_LEVEL, map.get("level"));
		CacheHandler.writeCache(context,Constant.USER_INFO,Constant.USER_DOCTOR_TICKET,map.get("ticket_num"));
		
	}
	public static void saveOrderHistory(Context context,HashMap<String, String>map){
		CacheHandler.writeCache(context, Constant.USER_INFO, Constant.USER_ORDER_HISTORY_HOSPITAL, map.get("hospital_name"));
		CacheHandler.writeCache(context, Constant.USER_INFO, Constant.USER_ORDER_HISTORY_SECTION, map.get("section_name"));
		CacheHandler.writeCache(context, Constant.USER_INFO, Constant.USER_ORDER_HISTORY_DOCTOR, map.get("doctor_name"));
		CacheHandler.writeCache(context, Constant.USER_INFO, Constant.USER_ORDER_HISTORY_KIND, map.get("order_info"));
		CacheHandler.writeCache(context, Constant.USER_INFO, Constant.USER_ORDER_HISTORY_TIME, map.get("order_time"));
		CacheHandler.writeCache(context,Constant.USER_INFO,Constant.USER_DOCTOR_TICKET,map.get("ticket_num"));
		
	}
	/**
	 * @param doctor
	 * 保存doctor的信息
	 */
	public static void saveOrder(Context context,HashMap<String ,String> map)
	{
		CacheHandler.writeCache(context, Constant.USER_INFO, Constant.USER_ORDER_TIME, map.get("time_choice"));
		CacheHandler.writeCache(context, Constant.USER_INFO, Constant.USER_ORDER_KIND, map.get("kind"));
		CacheHandler.writeCache(context, Constant.USER_INFO, Constant.USER_ORDER_FEE, map.get("fee"));
	}
	
	
	/**
	 * @param context
	 * @return 读取用户选择的医生信息
	 */
	public static Doctor getDoctor(Context context){
		int id = Integer.parseInt(CacheHandler.readCache(context, Constant.USER_INFO, Constant.USER_DOCTOR_ID));
		String name = CacheHandler.readCache(context, Constant.USER_INFO, Constant.USER_DOCTOR_NAME);
		String img = CacheHandler.readCache(context, Constant.USER_INFO, Constant.USER_DOCTOR_IMG);
		String favorite = CacheHandler.readCache(context, Constant.USER_INFO, Constant.USER_DOCTOR_FAVORITE);
		String level = CacheHandler.readCache(context, Constant.USER_INFO, Constant.USER_DOCTOR_LEVEL);
		String hname = CacheHandler.readCache(context, Constant.USER_INFO, Constant.USER_HOSPITAL_NAME);
		String sname = CacheHandler.readCache(context, Constant.USER_INFO, Constant.USER_SECTION_NAME);
		Doctor doctor = new Doctor(id, name, sname, level, hname, favorite, img);
		Log.d("NIUWEI", doctor.toString());
		return doctor;
	}
}
