package br.com.talkabout.infrastructure.persistence;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.talkabout.domain.model.Discussion;
import br.com.talkabout.domain.repository.DiscussionRepository;
import br.com.talkabout.domain.repository.MessageRepository;

@Repository
public class DiscussionDAO extends AbstractDAO<Discussion> implements DiscussionRepository{

	@Autowired
	private MessageRepository messageRepository;

	public Discussion get(String subject) throws IllegalArgumentException {
		
		if (subject == null || subject.trim().isEmpty())
			throw new IllegalArgumentException("Subject should not be empty");
		
		Session session = getSession();
		Discussion discussion = (Discussion) session.get(getModelClass(), subject);
		
		if (discussion != null)
			discussion.setMessages(messageRepository.getBySubject(subject));
		
		return discussion;
	}

	protected Class<Discussion> getModelClass() {
		return Discussion.class;
	}

	public void setMessageRepository(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}
	
}
