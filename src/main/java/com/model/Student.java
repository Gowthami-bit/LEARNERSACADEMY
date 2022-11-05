package com.model;
import java.util.Date;
import java.util.Set;

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

import com.model.Subject;


@Entity
@Table(name="ecom_student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "student_name")
	private String name;

	@Column(name = "student_age")
	private String age;

	@Column(name = "student_roll_no")
	private String rollno;


	@Column(name = "created_At")
	private Date createdAt;

	@Column(name = "modified_At")
	private Date modifiedAt;

	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="students_sub", joinColumns=@JoinColumn(name="id"),
	inverseJoinColumns=@JoinColumn(name="sub_id"))
	private Set<Subject> subject;

	public Date getCreatedAt() {
		return createdAt;
	}



	public Set<Subject> getSubject() {
		return subject;
	}

	public void setSubject(Set<Subject> subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", rollno=" + rollno + ", createdAt="
				+ createdAt + ", modifiedAt=" + modifiedAt + "]";
	}





	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}



	public Student() {
		super();
	}


	public Student(String name, String age,String rollno) {
		super();
		this.name = name;
		this.age = age;
		this.rollno=rollno;
		this.createdAt = new Date();
		this.modifiedAt = new Date();
	}

	public Student(int id, String name, String age,String rollno) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.rollno=rollno;

		this.createdAt = new Date();
		this.modifiedAt = new Date();
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
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age= age;
	}


}
 

