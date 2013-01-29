package br.com.comente_sobre.infrastructure.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
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
		Assert.assertNull(dao.getBySubject(""));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testThrowsExceptionWhenWhenCriteriaSubjectIsSpace() {
		Assert.assertNull(dao.getBySubject(" "));
	}
	
	@Test
	public void testGetNullDiscussionWhenThereIsNoDiscussionOnTheSubject() {
		Assert.assertNull(dao.getBySubject("subject"));
	}
	
	@Test
	public void testSaveDiscussion() {
		
		Discussion discussion = new Discussion();
		discussion.setSubject("subject");
		discussion.setMessages(new ArrayList<Message>());
		
		dao.saveOrUpdate(discussion);
		Assert.assertNotNull(dao.getBySubject("subject"));
		dao.delete(discussion);
	}
	
	@Test
	public void testUpdateDiscussionMessages() {
		
		List<Message> messages = new ArrayList<Message>();
		
		Discussion discussion = new Discussion();
		discussion.setSubject("subject");
		discussion.setMessages(messages);
		
		dao.saveOrUpdate(discussion);
		
		messages.add(new Message());
		discussion.setMessages(messages);
		
		dao.saveOrUpdate(discussion);
		
		Assert.assertEquals(1, dao.getBySubject("subject").getMessages().size());
		dao.delete(discussion);
	}
	
}
