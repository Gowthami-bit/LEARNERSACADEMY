package com.model;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.model.Subject;

@Entity
@Table(name="ecom_teachers")
public class Teacher {




		// properties
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "id")
		private int id;

		@Column(name = "teacher_name")
		private String teachname;



		@Column(name = "empcode")
		private String ecode;	



		@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
		private Set<Student> students1;


		@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
		@JoinColumn(name="sub_id")
		private Subject subject;

		public Teacher() {
			super();

		}


		public 	 Teacher(String teachname, String ecode) {
			super();

			this.teachname = teachname;
			this.ecode = ecode;
			this.students1 = students1;
		}


		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public String getTeachname() {
			return teachname;
		}


		public void setTeachname(String teachname) {
			this.teachname = teachname;
		}


		public String getEcode() {
			return ecode;
		}


		public void setEcode(String ecode) {
			this.ecode = ecode;
		}


		public Set<Student> getStudents1() {
			return students1;
		}


		public void setStudents1(Set<Student> students1) {
			this.students1 = students1;
		}


		public Subject getSubject() {
			return subject;
		}


		public void setSubject(Subject subject) {
			this.subject = subject;
		}


		@Override
		public String toString() {
			return "Teacher [id=" + id + ", teachname=" + teachname + ", ecode=" + ecode + ", students1=" + students1
					+ "]";
		}


}

		
