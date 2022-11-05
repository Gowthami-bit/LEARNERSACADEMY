package com.web;
import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.model.Student;
import com.model.Subject;
import com.model.Teacher;
import com.util.HibernateSessionUtil;


@WebServlet("/add-teacher-with-subject")
public class AddTeacherWithSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddTeacherWithSubject() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("index.html").include(request, response);
		request.getRequestDispatcher("add-teacher-with-subject.html").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("index.html").include(request, response);
		//student
		String teacherName = request.getParameter("tname");
		String teacherempcode = request.getParameter("emp_c");
		//student-details
		String name = request.getParameter("subname");
		String subcode = request.getParameter("subcode");

		// build hibernate session factory
		try {
			// 1. load session factory
			SessionFactory factory = HibernateSessionUtil.buildSessionFactory();

			// 2. create a session
			Session session = factory.openSession();

			// 3. create transaction
			 Transaction tx = session.beginTransaction();

			 //4. create student object & student details
			 Teacher teacher = new Teacher(teacherName,teacherempcode );
			 Subject sub = new Subject(name,subcode);
			 teacher.setSubject(sub);

			 //5. save student
			 session.save(teacher);

			 //6. commit transaction.
			 tx.commit();

			if (session != null) {
				out.print("<h3 style='color:green'> Student is created with student details sucessfully ! </h3>");
			}

			// close session
			session.close();
		} catch (Exception e) {
			out.print("<h3 style='color:red'> Hibernate session is failed ! </h3>");
		}

	}

}

