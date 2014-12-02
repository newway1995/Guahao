package module.entity;

import org.kymjs.aframe.database.annotate.Id;

/**
 * @author niuwei
 * @email nniuwei@163.com
 * @ClassName:Department.java
 * @Package:module.entity
 * @time:下午2:04:38 2014-12-1
 * @useage:医院部门
 */
public class Department {
	// 将id属性设置为主键，必须有一个主键，
    // 其实如果变量名为：'id'或'_id'默认就是主键
    // 也就是在一个JavaBean里面必须有'id'或'_id'或'@Id()'注解，否则会报错
    @Id()
	private int id;
	private String name;
	
	/**
	 * @param id
	 * @param name
	 * @param level 等级
	 * @param imgUrl 图片地址
	 * @param location 位置
	 * */
	public Department(int id,String name){
		this.id = id;
		this.name = name;
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
	
}
