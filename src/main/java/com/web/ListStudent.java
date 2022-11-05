package com.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.model.Student;
import com.util.HibernateSessionUtil;

@WebServlet("/list-students")
public class ListStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public ListStudent() { }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("index.html").include(request, response);

		try {
			//load session factory 
			SessionFactory factory = HibernateSessionUtil.buildSessionFactory();
			// create a session object
			Session session = factory.openSession();
			// read students
			List<Student> students = session.createQuery("from Student").list();

			//show data as table.
			out.print("<h1> Student List :- </h1>");

			out.print("<style> table,td,th {"
					+ "border:2px solid blue;"
					+ "padding: 10px; "
					+ "}</style>");
			out.print("<table >");
			out.print("<tr>");
				out.print("<th> Student Id</th>");
				out.print("<th> Student Name</th>");
				out.print("<th> Student Age</th>");
				out.print("<th> CreateAt </th>");
				out.print("<th> Modified </th>");
			out.print("</tr>");

			for(Student p : students) {
				out.print("<tr>");
				out.print("<td>"+p.getId()+"</td>");
				out.print("<td>"+p.getName()+"</td>");
				out.print("<td>"+p.getAge()+"</td>");
				out.print("<td>"+p.getCreatedAt()+"</td>");
				out.print("<td>"+p.getModifiedAt()+"</td>");
				out.print("</tr>");
			}
			out.print("</table>");

			//close session
			session.close();
		} catch (Exception e) {
			out.print("<h3 style='color:red'> Hibernate session is failed ! </h3>");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

				
				
				
				
				
