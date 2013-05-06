package br.com.talkabout.infrastructure.persistence;

import org.springframework.stereotype.Repository;

import br.com.talkabout.domain.model.Author;
import br.com.talkabout.domain.repository.AuthorRepository;

@Repository
public class AuthorDAO extends AbstractDAO<Author> implements AuthorRepository {

	public Author get(String email) {
		return super.get(email);
	}

	@Override
	protected Class<Author> getModelClass() {
		return Author.class;
	}

}
