package br.com.talkabout.domain.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import br.com.talkabout.domain.model.Author;

@ContextConfiguration(locations = { "classpath:spring/dataContext.xml" })
public class AuthorRepositoryTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private AuthorRepository repository;

	@Test
	public void testSaveAuthor() {

		Author author = new Author();
		author.setEmail("mail@mail.com");
		author.setName("name");

		repository.saveOrUpdate(author);
		assertNotNull(repository.get(author.getEmail()));
		repository.delete(author);
	}

	@Test
	public void testUpdateAuthor() {

		Author author = new Author();
		author.setEmail("mail@mail.com");
		author.setName("name");

		repository.saveOrUpdate(author);

		author.setName("New Name");
		repository.saveOrUpdate(author);

		assertEquals(author, repository.get(author.getEmail()));

		repository.delete(author);
	}
}
