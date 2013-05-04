package br.com.talkabout.infrastructure.persistence;

import org.springframework.stereotype.Repository;

import br.com.talkabout.domain.model.Discussion;
import br.com.talkabout.domain.repository.DiscussionRepository;

@Repository
public class DiscussionDAO extends AbstractDAO<Discussion> implements
		DiscussionRepository {

	public Discussion get(String subject) throws IllegalArgumentException {

		if (subject == null || subject.trim().isEmpty()) {
			throw new IllegalArgumentException("Subject should not be empty");
		}

		return super.get(subject);
	}

	protected Class<Discussion> getModelClass() {
		return Discussion.class;
	}

}
