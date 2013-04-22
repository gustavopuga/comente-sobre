package br.com.talkabout.domain.repository;

import br.com.talkabout.domain.model.Discussion;

public interface DiscussionRepository {

	public void saveOrUpdate(Discussion discussion);
	
	public void delete(Discussion discussion);

	public Discussion getBySubject(String subject);
}
