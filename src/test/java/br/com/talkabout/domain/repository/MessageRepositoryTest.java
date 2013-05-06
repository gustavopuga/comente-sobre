package br.com.talkabout.domain.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import br.com.talkabout.domain.model.Author;
import br.com.talkabout.domain.model.Discussion;
import br.com.talkabout.domain.model.Message;

@ContextConfiguration(locations = { "classpath:spring/dataContext.xml" })
public class MessageRepositoryTest extends AbstractJUnit4SpringContextTests {

	@Autowired 
	private MessageRepository repository;
	
	@Autowired 
	private AuthorRepository authorRepository;
	
	@Autowired 
	private DiscussionRepository discussionRepository;
	
	private Discussion discussion;
	private Author author;

	@Before
	public void createTestDependencies() {
		
		author = new Author();
		author.setName("author");
		author.setEmail("mail@mail.com");
		
		authorRepository.saveOrUpdate(author);
		
		discussion = new Discussion();
		discussion.setSubject("subject");
		discussion.setMessages(new ArrayList<Message>());
		discussion.setStartDate(Calendar.getInstance().getTime());
		
		discussionRepository.saveOrUpdate(discussion);
	}
	
	@Test
	public void testSaveMessage() {
		
		Message message = new Message();
		message.setText("text");
		message.setAuthor(author);
		message.setSubject("subject");
		message.setDate(Calendar.getInstance().getTime());
		
		repository.saveOrUpdate(message);
		assertNotNull(repository.get(message.getId()));
		repository.delete(message);
	}
	
	@Test
	public void testUpdateTextMessage() {
		
		Message message = new Message();
		message.setText("text");
		message.setAuthor(author);
		message.setSubject("subject");
		message.setDate(Calendar.getInstance().getTime());
		
		repository.saveOrUpdate(message);
		message.setText("other text");
		repository.saveOrUpdate(message);
		
		assertEquals("other text", repository.get(message.getId()).getText());
		repository.delete(message);
		
	}
	
	@Test
	public void testGetBySubjectAndDate() {
		
		Date startDate = Calendar.getInstance().getTime();
		
		Message message = new Message();
		message.setText("text");
		message.setAuthor(author);
		message.setSubject("subject");
		message.setDate(startDate);
		repository.saveOrUpdate(message);
		
		Date date = Calendar.getInstance().getTime();
		List<Message> messages = new ArrayList<>();
		
		for (int i = 0; i < 3; i++) {
			
			Message m = new Message();
			m.setText("text" + i);
			m.setAuthor(author);
			m.setSubject("subject");
			m.setDate(date);
			
			repository.saveOrUpdate(m);
			messages.add(m);
		}
		
		List<Message> repositoryMessages = repository.getBySubjectAndStartDate("subject", startDate);
		Assert.assertArrayEquals(messages.toArray(), repositoryMessages.toArray());
		
		for (Message m : messages) {
			repository.delete(m);
		}
		
		repository.delete(message);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSaveAuthorMessageLikeUndefinedWhenAuthorIsNull() {
		
		Message message = new Message();
		message.setText("text");
		message.setDate(Calendar.getInstance().getTime());
		
		repository.saveOrUpdate(message);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSaveAuthorMessageLikeUndefinedWhenDateIsNull() {
		
		Message message = new Message();
		message.setText("text");
		message.setAuthor(author);
		
		repository.saveOrUpdate(message);
	}
	
	@After
	public void excludeTestDependencies() {
		
		authorRepository.delete(author);
		discussionRepository.delete(discussion);
	}
}
