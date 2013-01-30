package br.com.comente_sobre.infrastructure.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import br.com.comente_sobre.domain.model.Discussion;
import br.com.comente_sobre.domain.model.Message;

@ContextConfiguration(locations = { "classpath:spring/dataContext.xml" })
public class DiscussionDAOTest extends AbstractJUnit4SpringContextTests{

	@Autowired private DiscussionDAO dao;
	
	@Test(expected=IllegalArgumentException.class)
	public void testThrowsExceptionWhenCriteriaSubjectIsEmpty() {
		dao.getBySubject("");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testThrowsExceptionWhenCriteriaSubjectIsSpace() {
		dao.getBySubject(" ");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testThrowsExceptionWhenCriteriaSubjectIsSpaces() {
		assertNull(dao.getBySubject("       "));
	}
	
	@Test
	public void testGetNullDiscussionWhenThereIsNoDiscussionOnTheSubject() {
		assertNull(dao.getBySubject("subject"));
	}
	
	@Test
	public void testSaveDiscussion() {
		
		Discussion discussion = new Discussion();
		discussion.setSubject("subject");
		discussion.setMessages(new ArrayList<Message>());
		
		dao.saveOrUpdate(discussion);
		assertNotNull(dao.getBySubject("subject"));
		dao.delete(discussion);
	}
	
	@Test(expected=ConstraintViolationException.class)
	public void testSaveDiscussionWhenSubjectIsNull() {
		
		Discussion discussion = new Discussion();
		discussion.setMessages(new ArrayList<Message>());
		
		dao.saveOrUpdate(discussion);
	}
	
	@Test
	public void testSaveDiscussionWhenThereIsNotMessages() {
		
		Discussion discussion = new Discussion();
		discussion.setSubject("subject");
		
		dao.saveOrUpdate(discussion);
		assertNotNull(dao.getBySubject("subject"));
		dao.delete(discussion);
	}
	
	@Test
	public void testUpdateDiscussionMessages() {
		
		List<Message> messages = new ArrayList<Message>();
		
		Discussion discussion = new Discussion();
		discussion.setSubject("subject");
		discussion.setMessages(messages);
		
		dao.saveOrUpdate(discussion);
		
		Message message = new Message();
		message.setText("text");
		message.setAuthor("author");
		
		messages.add(message);
		discussion.setMessages(messages);
		
		dao.saveOrUpdate(discussion);
		
		assertEquals(1, dao.getBySubject("subject").getMessages().size());
		dao.delete(discussion);
	}
	
}
