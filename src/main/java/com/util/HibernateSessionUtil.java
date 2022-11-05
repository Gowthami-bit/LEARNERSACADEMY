package com.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.model.Class;
import com.model.Student;
import com.model.Subject;
import com.model.Teacher;


public class HibernateSessionUtil {

	private static SessionFactory factory;

	public static SessionFactory buildSessionFactory() {
		factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.addAnnotatedClass(Class.class)
				.addAnnotatedClass(Teacher.class)
				.addAnnotatedClass(Subject.class)
				.buildSessionFactory();
		return factory;
	}

}


