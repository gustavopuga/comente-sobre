package br.com.comente_sobre.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.comente_sobre.domain.model.Discussion;
import br.com.comente_sobre.domain.service.TalkAboutService;

@Controller
public class TalkAboutController {

	private TalkAboutService talkAboutService;

	@Autowired
	public TalkAboutController(TalkAboutService talkAboutService) {
		this.talkAboutService = talkAboutService;
	}

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/{subject}")
	public void searchDiscussion(String subject) {

		String viewName;
		ModelAndView response = new ModelAndView();
		
		Discussion discussion = talkAboutService.getDiscussionBySubject(subject);
		
		if (discussion != null) {
			response.addObject(discussion);
			viewName = "";
		} else {
			viewName = "";
		}
		
		response.setViewName(viewName);
	}
	
	
}
