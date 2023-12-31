package com.skilldistillery.quorum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.quorum.data.GroupPostDAO;
import com.skilldistillery.quorum.data.ProfessorDAO;
import com.skilldistillery.quorum.data.SchoolDAO;
import com.skilldistillery.quorum.data.SocialGroupDAO;
import com.skilldistillery.quorum.data.UserDAO;
import com.skilldistillery.quorum.entities.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class GeneralController {

	private static String REDIRECT = "redirect:/";
	private static String REDIRECT_LOGIN = REDIRECT + "login.do";

	@Autowired
	private GroupPostDAO postDao;

	@Autowired
	private UserDAO userDao;

	@Autowired
	private SchoolDAO schoolDao;

	@Autowired
	private ProfessorDAO professorDao;

	@Autowired
	private SocialGroupDAO groupDao;

	@GetMapping({ "/error", "error.do" })
	public String errorOccured() {
		return "error";
	}

	@GetMapping({ "/404", "/not-found", "404.do", "notFound.do" })
	public String notFound() {
		return "not-found";
	}

	@GetMapping({ "/", "home.do" })
	public ModelAndView home(ModelAndView mav, HttpSession session) {
		mav.setViewName("home");
		User loggedUser = (User) session.getAttribute("loggedUser");
		if (loggedUser != null) {
			mav.addObject("feed", postDao.getUserFeed(loggedUser.getId()));
		}
		return mav;
	}

	@GetMapping({ "/search", "search.do" })
	public ModelAndView search(@RequestParam(name = "query") String query, HttpSession session) {

		ModelAndView mav = new ModelAndView("search");
		User user = (User) session.getAttribute("loggedUser");

		if (user == null) {
			mav.setViewName(REDIRECT_LOGIN);
		} else {
			mav.addObject("postResults", postDao.searchByQuery(query, user));
			mav.addObject("userResults", userDao.searchByQuery(query, user));
			mav.addObject("schoolResults", schoolDao.searchByQuery(query, user));
			mav.addObject("professorResults", professorDao.searchByQuery(query, user));
			mav.addObject("groupResults", groupDao.searchByQuery(query, user));
			mav.addObject("query", query);
		}

		return mav;
	}

}
