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

import com.model.Teacher;
import com.util.HibernateSessionUtil;

@WebServlet("/list-teachers")
public class ListTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public ListTeacher() { }
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
			List<Teacher> teacher = session.createQuery("from Teacher").list();

			//show data as table.
			out.print("<h1> Teacher List :- </h1>");

			out.print("<style> table,td,th {"
					+ "border:2px solid red;"
					+ "padding: 10px; "
					+ "}</style>");
			out.print("<table >");
			out.print("<tr>");
				out.print("<th> Teacher Id</th>");
				out.print("<th> Teacher Name</th>");
				out.print("<th> Teacher emp ID </th>");
			out.print("</tr>");

			for(Teacher p : teacher) {
				out.print("<tr>");
				out.print("<td>"+p.getId()+"</td>");
				out.print("<td>"+p.getTeachname()+"</td>");
				out.print("<td>"+p.getEcode()+"</td>");
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

