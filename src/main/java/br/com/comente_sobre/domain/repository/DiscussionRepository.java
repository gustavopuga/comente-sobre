package br.com.comente_sobre.domain.repository;

import br.com.comente_sobre.domain.model.Discussion;

public interface DiscussionRepository {

	public void saveOrUpdate(Discussion discussion);
	
	public void delete(Discussion discussion);

	public Discussion getBySubject(String subject);
}
