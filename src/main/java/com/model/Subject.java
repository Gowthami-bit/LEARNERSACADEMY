package com.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mysql.cj.x.protobuf.MysqlxCursor.Fetch;


@Entity
@Table(name="ecom_subjects")
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sub_id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "subcode")
	private String subcode;



	public Subject(String name, String subcode) {
		super();

		this.name = name;
		this.subcode = subcode;
	}



	public Subject() {
		super();
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



	public String getSubcode() {
		return subcode;
	}
	
	
	


	public void setSubcode(String subcode) {
		this.subcode = subcode;
	}



	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", subcode=" + subcode + "]";
	}



}
	
	
