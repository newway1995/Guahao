package module.entity;

import java.io.Serializable;

import org.kymjs.aframe.database.annotate.Id;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:Doctor.java
 * @Package:module.entity
 * @time:下午2:04:57 2014-12-1
 * @useage:医生实体
 */
public class Doctor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3776879019214071711L;
	
	// 将id属性设置为主键，必须有一个主键，
    // 其实如果变量名为：'id'或'_id'默认就是主键
    // 也就是在一个JavaBean里面必须有'id'或'_id'或'@Id()'注解，否则会报错
    @Id()
	private int id;
	private String name;
	private String section_name;
	private String level;//医生职务
	private String hospital_name;
	private String adept;//擅长
	private String img;//img
	
	
	/**
	 * @param id
	 * @param name
	 * @param section_name
	 * @param level
	 * @param hospital_name
	 * @param adept
	 * */
	public Doctor(int id,String name,String section_name,String level,String hospital_name,String adept,String img){
		this.id = id;
		this.name = name;
		this.section_name = section_name;
		this.level = level;
		this.hospital_name = hospital_name;
		this.adept = adept;
		this.setImg(img);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSection_name() {
		return section_name;
	}
	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getHospital_name() {
		return hospital_name;
	}
	public void setHospital_name(String hospital_name) {
		this.hospital_name = hospital_name;
	}
	public String getAdept() {
		return adept;
	}
	public void setAdept(String adept) {
		this.adept = adept;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
}
