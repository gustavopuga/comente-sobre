package br.com.comente_sobre.domain;

import br.com.comente_sobre.domain.model.Discussion;
import br.com.comente_sobre.infrastructure.dao.DiscussionDAO;

public class TalkAboutService {

	private DiscussionDAO discussionDAO;
	
	public TalkAboutService(DiscussionDAO discussionDAO) {
		this.discussionDAO = discussionDAO;
	}
	
	public Discussion getDiscussion(String subject) throws IllegalArgumentException{
		return discussionDAO.getBySubject(subject);
	}

}
