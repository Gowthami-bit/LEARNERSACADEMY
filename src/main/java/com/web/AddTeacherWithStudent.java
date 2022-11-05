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
import com.model.Teacher;
import com.util.HibernateSessionUtil;

@WebServlet("/add-teacher-with-student")
public class AddTeacherWithStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddTeacherWithStudent() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		if(session != null) {


				out.println("<h3 style='color:green'> Welcome to admin access page  </h3>");

				request.getRequestDispatcher("add-teacher-with-student.html").include(request, response);
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
		String tname = request.getParameter("teachername");
		String eco =request.getParameter("ecode");


		// get product details
		String studentOneName = request.getParameter("name1");
		String studentOneAge =request.getParameter("age1");
		String studentOneroll=request.getParameter("rollno1");

		String studentTwoName = request.getParameter("name2");
		String studentTwoAge =request.getParameter("age2");
		String studentTworoll=request.getParameter("rollno2");

		String studentThreeName = request.getParameter("name3");
		String studentThreeAge =request.getParameter("age3");
		String studentThreeroll=request.getParameter("rollno3");

		String studentFourName = request.getParameter("name4");
		String studentFourAge =request.getParameter("age4");
		String studentFourroll=request.getParameter("rollno4");

		// build hibernate session factory
		try {
			// 1. load session factory
			SessionFactory factory = HibernateSessionUtil.buildSessionFactory();

			// 2. create a session
			Session session = factory.openSession();

			// 3. create transaction
			Transaction tx = session.beginTransaction();

			// 4. create order object
			Teacher teach = new Teacher(tname,eco);

			Set<Student> students1= new HashSet<Student>();
			Student student1 = new Student(studentOneName,studentOneAge, studentOneroll);
			Student student2 = new Student(studentTwoName,studentTwoAge, studentTworoll);
			Student student3 = new Student(studentThreeName,studentThreeAge, studentThreeroll);
			Student student4 = new Student(studentFourName,studentFourAge, studentFourroll);
			students1.add(student1);
			students1.add(student2);
			students1.add(student3);
			students1.add(student4);
			// add products list to order
			teach.setStudents1(students1);

			// 5. save product
			session.save(teach);

			// 6. commit transaction.
			tx.commit();

			if (session != null) {
				out.print("<h3 style='color:green'> Order is created with products sucessfully ! </h3>");
			}

			// close session
			session.close();
		} catch (Exception e) {
			out.print("<h3 style='color:red'> Hibernate session is failed ! </h3>"+e);
		}

	}

}

