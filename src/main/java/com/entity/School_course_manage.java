package com.entity;

/**
 * @author HungryCats
 * @date 2023-10-22 06:14:17
 */
public class School_course_manage {

	private Integer id;
	private String teacher_name_id;
	private Integer classes;
	private Integer subject_id;
	private String teaching_start_time;
	private String teaching_end_time;
	private Integer state;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTeacher_name_id() {
		return teacher_name_id;
	}
	public void setTeacher_name_id(String teacher_name_id) {
		this.teacher_name_id = teacher_name_id;
	}
	public Integer getClasses() {
		return classes;
	}
	public void setClasses(Integer classes) {
		this.classes = classes;
	}
	public Integer getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(Integer subject_id) {
		this.subject_id = subject_id;
	}
	public String getTeaching_start_time() {
		return teaching_start_time;
	}
	public void setTeaching_start_time(String teaching_start_time) {
		this.teaching_start_time = teaching_start_time;
	}
	public String getTeaching_end_time() {
		return teaching_end_time;
	}
	public void setTeaching_end_time(String teaching_end_time) {
		this.teaching_end_time = teaching_end_time;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "School_course_manage [id=" + id + ", teacher_name_id=" + teacher_name_id + ", classes=" + classes
				+ ", subject_id=" + subject_id + ", teaching_start_time=" + teaching_start_time + ", teaching_end_time="
				+ teaching_end_time + ", state=" + state + ", getId()=" + getId() + ", getTeacher_name_id()="
				+ getTeacher_name_id() + ", getClasses()=" + getClasses() + ", getSubject_id()=" + getSubject_id()
				+ ", getTeaching_start_time()=" + getTeaching_start_time() + ", getTeaching_end_time()="
				+ getTeaching_end_time() + ", getState()=" + getState() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
