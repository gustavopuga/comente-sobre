package br.com.comente_sobre.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.comente_sobre.domain.model.Discussion;
import br.com.comente_sobre.infrastructure.dao.DiscussionDAO;
;

@Component
public class TalkAboutService {

	private DiscussionDAO discussionDAO;
	
	@Autowired
	public TalkAboutService(DiscussionDAO discussionDAO) {
		this.discussionDAO = discussionDAO;
	}
	
	public Discussion getDiscussion(String subject) throws IllegalArgumentException{
		return discussionDAO.getBySubject(subject);
	}

}
