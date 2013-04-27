package br.com.talkabout.application.controller;

import java.io.IOException;
import java.util.Calendar;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.talkabout.domain.model.Discussion;
import br.com.talkabout.domain.model.Message;
import br.com.talkabout.domain.service.TalkAboutService;

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
	
	@RequestMapping(value="/{subject}")
	public ModelAndView searchDiscussion(@PathVariable("subject")String subject) {

		ModelAndView response = new ModelAndView();
		if (subject != null && !subject.trim().isEmpty()) {
			
			Discussion discussion;
			try {	
				
				discussion = talkAboutService.getDiscussionBySubject(subject);
				if (discussion == null) {
					discussion = talkAboutService.createNewSubjectDiscussion(subject);
				}
				
			} catch (IllegalArgumentException e) {
				discussion = talkAboutService.createNewSubjectDiscussion(subject);	
			}
			
			response.addObject(discussion);
			response.setViewName("discussion");
		} else {
			response.setViewName("index");
		}
		
		return response;
	}

	@RequestMapping(value="/list/{subject}")
	public @ResponseBody String listDiscussion(@PathVariable("subject")String subject) throws JsonGenerationException, JsonMappingException, IOException {

		if (subject != null && !subject.trim().isEmpty()) {
			
			Discussion discussion;
			try {	
				discussion = talkAboutService.getDiscussionBySubject(subject);
			} catch (IllegalArgumentException e) {
				discussion = talkAboutService.createNewSubjectDiscussion(subject);	
			}
			
			ObjectMapper mapper = new ObjectMapper(); //ObjectMapper é uma classe da biblioteca Jackson
	        return mapper.writeValueAsString(discussion.getMessages());
		}
		
		return "";
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
