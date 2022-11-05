package com.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.model.Class;
import com.model.Subject;
import com.model.Student;
import com.util.HibernateSessionUtil;

@WebServlet("/add-std-with-sub")
public class AddStdWithSub extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public AddStdWithSub() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("index.html").include(request, response);
		request.getRequestDispatcher("add-std-with-sub.html").include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("index.html").include(request, response);

		// get student details
		String studentName = request.getParameter("name");
		String age = request.getParameter("age");
		String rollno = request.getParameter("rollno");

		// get payment details
		String name1 = request.getParameter("subName1");
		String subcode1 =  request.getParameter("subcode1");

		String name2 = request.getParameter("subName2");
		String subcode2 =  request.getParameter("subcode2");


		// build hibernate session factory
		try {
			// 1. load session factory
			SessionFactory factory = HibernateSessionUtil.buildSessionFactory();

			// 2. create a session
			Session session = factory.openSession();

			// 3. create transaction
			Transaction tx = session.beginTransaction();

			// create student object
			Student student = new Student(studentName, age ,rollno);

			//create payments set
			Subject sub1 = new Subject(name1 , subcode1);
			Subject sub2 = new Subject(name2, subcode2);
			Set<Subject> subjects = new HashSet<Subject>();

			subjects.add(sub1);
			subjects.add(sub2);

			//set payments to student
			student.setSubject(subjects);

			// 5. save student
			session.save(student);

			// 6. commit transaction.
			tx.commit();

			if (session != null) {
				out.print("<h3 style='color:green'> Students are created with payments sucessfully ! </h3>");
			}

			// close session
			session.close();
		} catch (Exception e) {
			out.print("<h3 style='color:red'> Hibernate session is failed ! </h3>"+e);
		}

	}

}

