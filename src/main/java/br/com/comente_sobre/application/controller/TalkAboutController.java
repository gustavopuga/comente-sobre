package br.com.comente_sobre.application.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.comente_sobre.domain.model.Discussion;
import br.com.comente_sobre.domain.model.Message;
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
	
	@RequestMapping(value="/{subject}", method=RequestMethod.POST)
	public ModelAndView searchDiscussion(@PathVariable("subject")String subject) {

		ModelAndView response = new ModelAndView();
		if (subject != null && !subject.trim().isEmpty()) {
			
			try {	
				Discussion discussion = talkAboutService.getDiscussionBySubject(subject);
				response.addObject(discussion);
			} catch (IllegalArgumentException e) {
				talkAboutService.createNewSubjectDiscussion(subject);	
				Discussion discussion = talkAboutService.getDiscussionBySubject(subject);
				response.addObject(discussion);
			}
			
			response.setViewName("discussion");
		} else {
			response.setViewName("index");
		}
		
		return response;
	}

	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ModelAndView update(String author, String subject, String text) {
		
		if (author != null && !author.isEmpty() && text != null && !text.isEmpty()){
			Message message = new Message();
			message.setAuthor(author);
			message.setSubject(subject);
			message.setText(text);
			message.setDate(Calendar.getInstance().getTime());
			talkAboutService.updateDiscussion(message);
		}
		return searchDiscussion(subject);
	}
}
