package br.com.talkabout.domain.repository;

import br.com.talkabout.domain.model.Author;

public interface AuthorRepository {

	public void saveOrUpdate(Author author);
	
	public void delete(Author author);

	public Author get(String email);
}
