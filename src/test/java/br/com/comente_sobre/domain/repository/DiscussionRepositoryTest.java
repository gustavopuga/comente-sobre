package br.com.comente_sobre.domain.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import br.com.comente_sobre.domain.model.Discussion;
import br.com.comente_sobre.domain.model.Message;
import br.com.comente_sobre.domain.repository.DiscussionRepository;

@ContextConfiguration(locations = { "classpath:spring/dataContext.xml" })
public class DiscussionRepositoryTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private DiscussionRepository repository;

	@Test(expected = IllegalArgumentException.class)
	public void testThrowsExceptionWhenCriteriaSubjectIsEmpty() {
		repository.getBySubject("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThrowsExceptionWhenCriteriaSubjectIsSpace() {
		repository.getBySubject(" ");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThrowsExceptionWhenCriteriaSubjectIsSpaces() {
		assertNull(repository.getBySubject("       "));
	}

	@Test
	public void testGetNullDiscussionWhenThereIsNoDiscussionOnTheSubject() {
		assertNull(repository.getBySubject("subject"));
	}

	@Test
	public void testSaveDiscussion() {

		Discussion discussion = new Discussion();
		discussion.setSubject("subject");
		discussion.setMessages(new ArrayList<Message>());
		discussion.setStartDate(Calendar.getInstance());

		repository.saveOrUpdate(discussion);
		assertNotNull(repository.getBySubject("subject"));
		repository.delete(discussion);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSaveDiscussionWhenSubjectIsNull() {

		Discussion discussion = new Discussion();
		discussion.setMessages(new ArrayList<Message>());
		discussion.setStartDate(Calendar.getInstance());

		repository.saveOrUpdate(discussion);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSaveDiscussionWhenStartDateIsNull() {

		Discussion discussion = new Discussion();
		discussion.setMessages(new ArrayList<Message>());
		discussion.setSubject("subject");

		repository.saveOrUpdate(discussion);
	}
	
	@Test
	public void testSaveDiscussionWhenThereIsNotMessages() {

		Discussion discussion = new Discussion();
		discussion.setSubject("subject");
		discussion.setStartDate(Calendar.getInstance());

		repository.saveOrUpdate(discussion);
		assertNotNull(repository.getBySubject("subject"));
		repository.delete(discussion);
	}

	@Test
	public void testUpdateDiscussionMessages() {

		List<Message> messages = new ArrayList<Message>();

		Discussion discussion = new Discussion();
		discussion.setSubject("subject");
		discussion.setMessages(messages);
		discussion.setStartDate(Calendar.getInstance());

		repository.saveOrUpdate(discussion);

		Message message = new Message();
		message.setText("text");
		message.setAuthor("author");
		message.setSubject("subject");
		message.setDate(Calendar.getInstance());
		
		messages.add(message);
		discussion.setMessages(messages);

		repository.saveOrUpdate(discussion);

		assertEquals(1, repository.getBySubject("subject").getMessages().size());
		repository.delete(discussion);
	}

	@Test
	public void testUpdateDiscussionMessagesWhenMessagesNotSetSubject() {

		List<Message> messages = new ArrayList<Message>();

		Discussion discussion = new Discussion();
		discussion.setSubject("subject");
		discussion.setMessages(messages);
		discussion.setStartDate(Calendar.getInstance());

		repository.saveOrUpdate(discussion);

		Message message = new Message();
		message.setText("text");
		message.setAuthor("author");
		message.setSubject("subject");
		message.setDate(Calendar.getInstance());

		messages.add(message);
		discussion.setMessages(messages);

		repository.saveOrUpdate(discussion);

		assertEquals("subject", repository.getBySubject("subject").getMessages()
				.get(0).getSubject());
		repository.delete(discussion);
	}
}