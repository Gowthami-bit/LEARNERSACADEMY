
package com.model;
import java.util.Set;

import javax  .persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ecom_class")
public class Class {

	// properties
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "class")
	private String classPos;
	@Column(name = "school")
	private String schoolName;	



	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Student> students;

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Subject> subjects;

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Teacher> teacher;

	public Class() {
		super();
	}

	public Class(String classPos, String schoolName) {
		super();

		this.classPos = classPos;
	 this.schoolName = schoolName;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClassPos() {
		return classPos;
	}

	public void setClassPos(String classPos) {
		this.classPos = classPos;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Set<Student> getStudents() {
		return students;
	
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Set<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}
//for teacher 
	public Set<Teacher> getTeacher() {
		return teacher;
	}

	public void setTeacher(Set<Teacher> teachers) {
		this.teacher = teachers;
	}
//close 
	@Override
	public String toString() {
		return "Class [id=" + id + ", classPos=" + classPos + ", schoolName=" + schoolName + ", students=" + students
				+ "]";
	}
	}





	

	
	
	
	
	
	
	
	
	
	
	
