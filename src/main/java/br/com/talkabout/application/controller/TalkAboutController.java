package br.com.talkabout.application.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
	
	@RequestMapping(value="/{subject}", method=RequestMethod.POST)
	public ModelAndView searchDiscussion(@PathVariable("subject")String subject) {

		ModelAndView response = new ModelAndView();
		if (subject != null && !subject.trim().isEmpty()) {
			
			Discussion discussion;
			Date lastMessageDate;
			
			try {	
				
				discussion = talkAboutService.getDiscussionBySubject(subject);
				
				if (discussion == null) {
					discussion = talkAboutService.createNewSubjectDiscussion(subject);
				}
				
				List<Message> messages = discussion.getMessages();
				int messageNumber = messages.size();
				
				if (messageNumber == 0){
					lastMessageDate = discussion.getStartDate();
				} else {
					lastMessageDate = messages.get(messages.size() - 1).getDate();
				}
				
			} catch (IllegalArgumentException e) {
				discussion = talkAboutService.createNewSubjectDiscussion(subject);
				lastMessageDate = discussion.getStartDate();
			}
			
			response.addObject(discussion);
			response.addObject("date", lastMessageDate.getTime());
			response.setViewName("discussion");
			
		} else {
			response.setViewName("index");
		}
		
		return response;
	}

	@RequestMapping(value="/{subject}/{date}", method=RequestMethod.GET)
	public @ResponseBody String listNewDiscussion(@PathVariable("subject")String subject, @PathVariable("date")String stringDate) throws JsonGenerationException, JsonMappingException, IOException {

		List<Message> messages;
		messages = new ArrayList<Message>();
		
//		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + subject);
//		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + stringDate);
		
		if(stringDate != null && !stringDate.trim().isEmpty()){
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(Long.valueOf(stringDate));
			try {	
				messages = talkAboutService.getMessagesBySubjectAndDate(subject, calendar.getTime());
			} catch (IllegalArgumentException e) { }
			
//			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< " + messages);
		}
		
		ObjectMapper mapper = new ObjectMapper(); 
	    return mapper.writeValueAsString(messages);
	}
	
	@RequestMapping(value="/{subject}", method=RequestMethod.PUT)
	public @ResponseBody String updateDiscussionMessages(String author, String subject, String text) {
		
		System.out.println("||||||||||||||||||||||||||||||||||||||||" + talkAboutService.getDiscussionBySubject(subject));
		
		Message message = new Message();
		message.setAuthor(author);
		message.setSubject(subject);
		message.setText(text);
		message.setDate(Calendar.getInstance().getTime());
		talkAboutService.updateDiscussion(message);
		
		return "";
	}
}
