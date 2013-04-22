package br.com.talkabout.domain.service;

import java.util.ArrayList;
import java.util.Calendar;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.talkabout.domain.model.Discussion;
import br.com.talkabout.domain.model.Message;
import br.com.talkabout.domain.repository.DiscussionRepository;

@Service
public class TalkAboutService {

	private DiscussionRepository discussionRepository;

	@Autowired
	public TalkAboutService(DiscussionRepository discussionRepository) {
		this.discussionRepository = discussionRepository;
	}

	public Discussion getDiscussionBySubject(String subject)
			throws IllegalArgumentException {
		return discussionRepository.getBySubject(subject);
	}

	public void createNewSubjectDiscussion(String subject) {

		Discussion discussion = new Discussion();
		discussion.setSubject(subject);
		discussion.setStartDate(Calendar.getInstance().getTime());
		discussion.setMessages(new ArrayList<Message>());

		discussionRepository.saveOrUpdate(discussion);
	}

	public boolean updateDiscussion(Message message) {

		try {

			Discussion discussion = discussionRepository.getBySubject(message.getSubject());
			if (discussion != null) {
				discussion.getMessages().add(message);
				discussionRepository.saveOrUpdate(discussion);
				return true;
			}
			return false;

		} catch (ConstraintViolationException exception) {
			throw new IllegalArgumentException(exception);
		}
	}

}