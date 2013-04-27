package br.com.talkabout.domain.service;

import java.util.ArrayList;
import java.util.Calendar;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.talkabout.domain.model.Discussion;
import br.com.talkabout.domain.model.Message;
import br.com.talkabout.domain.repository.DiscussionRepository;
import br.com.talkabout.domain.repository.MessageRepository;

@Service
public class TalkAboutService {

	private DiscussionRepository discussionRepository;
	private MessageRepository messageRepository;
	
	@Autowired
	public TalkAboutService(DiscussionRepository discussionRepository, MessageRepository messageRepository) {
		this.discussionRepository = discussionRepository;
		this.messageRepository = messageRepository;
	}

	public Discussion getDiscussionBySubject(String subject)
			throws IllegalArgumentException {
		return discussionRepository.get(subject);
	}

	public Discussion createNewSubjectDiscussion(String subject) {

		Discussion discussion = new Discussion();
		discussion.setSubject(subject);
		discussion.setStartDate(Calendar.getInstance().getTime());
		discussion.setMessages(new ArrayList<Message>());

		discussionRepository.saveOrUpdate(discussion);
		
		return discussion;
	}

	public void updateDiscussion(Message message) {

		try {

//			Discussion discussion = discussionRepository.get(message.getSubject());
//			if (discussion != null) {
//				discussion.getMessages().add(message);
//				discussionRepository.saveOrUpdate(discussion);
//				return true;
//			}
//			return false;
			
			messageRepository.saveOrUpdate(message);

		} catch (ConstraintViolationException exception) {
			throw new IllegalArgumentException(exception);
		}
	}

}