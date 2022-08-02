package com.example.dto;

import org.apache.ibatis.type.Alias;

@Alias("employee")
public class EmployeeDTO {
	private String eno;
	private String name;
	private String department;
	private String position_no;
	public EmployeeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getEno() {
		return eno;
	}
	public void setEno(String eno) {
		this.eno = eno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition_no() {
		return position_no;
	}
	public void setPosition_no(String position_no) {
		this.position_no = position_no;
	}
	@Override
	public String toString() {
		return "EmployeeDTO [eno=" + eno + ", name=" + name + ", department=" + department + ", position_no=" + position_no
				+ "]";
	}
}
