package br.com.comente_sobre.infrastructure.dao;

import br.com.comente_sobre.domain.model.Discussion;

public class DiscussionDAO {
	
	public Discussion getBySubject(String subject) throws IllegalArgumentException {
		if (subject.trim().isEmpty()){
			throw new IllegalArgumentException("Subject should not be empty");
		}
		return null;
	}

}
