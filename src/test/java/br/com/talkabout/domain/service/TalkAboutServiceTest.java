package br.com.talkabout.domain.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;

import br.com.talkabout.domain.model.Message;

@ContextConfiguration(locations = { "classpath:spring/dataContext.xml",
		"classpath:spring/serviceContext.xml" })
public class TalkAboutServiceTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private TalkAboutService service;

	@Test(expected = IllegalArgumentException.class)
	public void testThrowsExceptionWhenEmptySubject() {
		service.getDiscussionBySubject("");
	}

	@Test
	public void testGetNullDiscussionWhenThereIsNoSubject() {
		assertNull(service.getDiscussionBySubject("subject"));
	}

	@Test
	@Transactional
	@Rollback(value = true)
	public void testCreateNewDiscussion() {
		service.createNewSubjectDiscussion("subject");
		assertNotNull(service.getDiscussionBySubject("subject"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpdateDiscussionWhenSubjectIsNull() {
		
		Message message = new Message();
		message.setText("");
		message.setAuthor("author");
		message.setDate(Calendar.getInstance().getTime());
		
		service.updateDiscussion(message);	
	}

	@Test
	public void testNotUpdateDiscussionWhenSubjectIsInvalid() {
		
		Message message = new Message();
		message.setText("");
		message.setAuthor("author");
		message.setSubject("subject");
		message.setDate(Calendar.getInstance().getTime());
		
		assertFalse(service.updateDiscussion(message));
	}
	
	@Test
	@Transactional
	@Rollback(value = true)
	public void testUpdateDiscussionWhenSubjectIsValid() {
		
		String subject = "subject";
		
		service.createNewSubjectDiscussion(subject);
		
		Message message = new Message();
		message.setText("");
		message.setAuthor("author");
		message.setSubject(subject);
		message.setDate(Calendar.getInstance().getTime());
		
		service.updateDiscussion(message);
		
		assertTrue(service.getDiscussionBySubject(subject).getMessages().get(0).equals(message));
	}
}
