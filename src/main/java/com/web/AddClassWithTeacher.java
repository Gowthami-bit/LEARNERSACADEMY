package com.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.model.Class;
import com.model.Student;
import com.model.Subject;
import com.model.Teacher;
import com.util.HibernateSessionUtil;

@WebServlet("/add-class-with-teacher")
public class AddClassWithTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddClassWithTeacher() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		if(session != null) {


				out.println("<h3 style='color:green'> Welcome to admin access page  </h3>");

				request.getRequestDispatcher("add-class-with-teacher.html").include(request, response);
			} 

		else {
			out.println("<h3 style='color:red'>Invalid access, please login to access controls! </h3>");
			request.getRequestDispatcher("login.html").include(request, response);
		}

		response.setContentType("text/html");

		request.getRequestDispatcher("index.html").include(request, response);


	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("index.html").include(request, response);

		// get order params
		String classPos = request.getParameter("clas");
		String schoolName =request.getParameter("schname");


		// get product details
		String teacherOneName = request.getParameter("nameo");
		String teacherOneCode =request.getParameter("e1");


		String teacherTwoName = request.getParameter("namet");
		String teacherTwoCode =request.getParameter("e2");


		String teacherThreeName = request.getParameter("nametr");
		String teacherThreeCode =request.getParameter("e3");




		// build hibernate session factory
		try {
			// 1. load session factory
			SessionFactory factory = HibernateSessionUtil.buildSessionFactory();

			// 2. create a session
			Session session = factory.openSession();

			// 3. create transaction
			Transaction tx = session.beginTransaction();

			// 4. create order object
			Class ctea = new Class (classPos,schoolName);

			Set<Teacher> teachers= new HashSet<Teacher>();
			Teacher teacher1 = new Teacher(teacherOneName,teacherOneCode);
			Teacher teacher2 = new Teacher(teacherTwoName,teacherTwoCode);
			Teacher teacher3 = new Teacher(teacherThreeName,teacherThreeCode);

			teachers.add(teacher1);
			teachers.add(teacher2);
			teachers.add(teacher3);

			// add products list to order
			ctea.setTeacher(teachers);

			// 5. save product
			session.save(ctea);

			// 6. commit transaction.
			tx.commit();

			if (session != null) {
				out.print("<h3 style='color:green'> Class is created with subjects sucessfully ! </h3>");
			}

			// close session
			session.close();
		} catch (Exception e) {
			out.print("<h3 style='color:red'> Hibernate session is failed ! </h3>"+e);
		}
		
	}
}	
