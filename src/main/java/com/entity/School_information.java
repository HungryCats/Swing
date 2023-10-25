package com.entity;
/**
 * @author HungryCats
 * @date 2023-10-23 04:23:49 
*/
public class School_information {
	private Integer id;
	private String school_name;
	private String school_web_address;
	private String school_area;
	private Integer school_type_id;
	private String school_type;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public String getSchool_web_address() {
		return school_web_address;
	}
	public void setSchool_web_address(String school_web_address) {
		this.school_web_address = school_web_address;
	}
	public String getSchool_area() {
		return school_area;
	}
	public void setSchool_area(String school_area) {
		this.school_area = school_area;
	}
	public Integer getSchool_type_id() {
		return school_type_id;
	}
	public void setSchool_type_id(Integer school_type_id) {
		this.school_type_id = school_type_id;
	}
	public String getSchool_type() {
		return school_type;
	}
	public void setSchool_type(String school_type) {
		this.school_type = school_type;
	}
	@Override
	public String toString() {
		return "School_information [id=" + id + ", school_name=" + school_name + ", school_web_address="
				+ school_web_address + ", school_area=" + school_area + ", school_type_id=" + school_type_id
				+ ", school_type=" + school_type + ", getId()=" + getId() + ", getSchool_name()=" + getSchool_name()
				+ ", getSchool_web_address()=" + getSchool_web_address() + ", getSchool_area()=" + getSchool_area()
				+ ", getSchool_type_id()=" + getSchool_type_id() + ", getSchool_type()=" + getSchool_type()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
}
