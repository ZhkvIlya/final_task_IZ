package springmvc.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.kzn.itis.db.model.UserF;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class FinalTaskController implements Controller {

	UserF user;

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ModelAndView modelAndView = new ModelAndView("hello_world");
		modelAndView.addObject("message", "Hello World MVC");

		modelAndView.addObject("user", new UserF(1, "Ayrat", "Natfullin", 32));

		return modelAndView;
	}

}
