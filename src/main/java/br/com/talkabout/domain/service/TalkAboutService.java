package br.com.talkabout.domain.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
	public TalkAboutService(DiscussionRepository discussionRepository,
			MessageRepository messageRepository) {
		this.discussionRepository = discussionRepository;
		this.messageRepository = messageRepository;
	}

	public Discussion getDiscussion(String subject)
			throws IllegalArgumentException {

		Discussion discussion = discussionRepository.get(subject);
		if (discussion != null && discussion.getMessages().isEmpty()) {
			List<Message> messages = messageRepository.getBySubject(subject);
			discussion.setMessages(messages);
		}

		return discussion;
	}

	public List<Message> getMessagesBySubjectAndDate(String subject, Date date)
			throws IllegalArgumentException {
		return messageRepository.getBySubjectAndDate(subject, date);
	}

	public Discussion createNewSubjectDiscussion(String subject) {

		Discussion discussion = new Discussion();
		discussion.setSubject(subject.toLowerCase());
		discussion.setStartDate(Calendar.getInstance().getTime());
		discussion.setMessages(new ArrayList<Message>());

		discussionRepository.saveOrUpdate(discussion);

		return discussion;
	}

	public void updateDiscussion(Message message) {

		try {
			messageRepository.saveOrUpdate(message);
		} catch (ConstraintViolationException exception) {
			throw new IllegalArgumentException(exception);
		}
	}

}