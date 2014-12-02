package module.entity;

import org.kymjs.aframe.database.annotate.Id;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:Hospital.java
 * @Package:module.entity
 * @time:下午2:04:48 2014-12-1
 * @useage:医院实体
 */
public class Hospital {
	// 将id属性设置为主键，必须有一个主键，
    // 其实如果变量名为：'id'或'_id'默认就是主键
    // 也就是在一个JavaBean里面必须有'id'或'_id'或'@Id()'注解，否则会报错
    @Id()
	private int id;
	private String name;
	private String level;
	private String imgUrl;
	private String location;
	
	/**
	 * @param id
	 * @param name
	 * @param level 等级
	 * @param imgUrl 图片地址
	 * @param location 位置
	 * */
	public Hospital(int id,String name,String level,String imgUrl,String location){
		this.id = id;
		this.level = level;
		this.name = name;
		this.imgUrl = imgUrl;
		this.location = location;
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

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
