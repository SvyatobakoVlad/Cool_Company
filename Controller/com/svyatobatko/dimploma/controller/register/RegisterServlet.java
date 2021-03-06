package com.svyatobatko.dimploma.controller.register;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.svyatobatko.dimploma.dao.UserDao;
import com.svyatobatko.dimploma.dao.impl.DefaultUserDao;
import com.svyatobatko.dimploma.models.Role;
import com.svyatobatko.dimploma.models.UserData;
import static com.svyatobatko.dimploma.filter.partner.PartnerFilter.*;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDao userDao;

	{
		userDao = DefaultUserDao.getInstance();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/SignUp.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		UserData user = new UserData();
		user.setLastName(request.getParameter("lastname"));
		user.setFirstName(request.getParameter("firstname"));
		user.setDateOfBirth(request.getParameter("Date_of_Birth"));
		user.setLogin(request.getParameter("email"));
		user.setPassword(request.getParameter("Password"));
		user.setTelephone(request.getParameter("Telephone"));
		
		  for (Cookie c : request.getCookies()) { 
			  if(c.getName().equals(PARTNER_ID)) {
		      user.setReferId(c.getValue());
		      System.out.println(c.getValue());
		  }
		  }
		 
		userDao.addUser(user);
		
		doGet(request, response);
	}

}
