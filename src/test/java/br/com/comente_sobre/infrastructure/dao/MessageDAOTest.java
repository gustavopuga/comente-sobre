package br.com.comente_sobre.infrastructure.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import br.com.comente_sobre.domain.model.Message;

@ContextConfiguration(locations = { "classpath:spring/dataContext.xml" })
public class MessageDAOTest extends AbstractJUnit4SpringContextTests{

	@Autowired protected MessageDAO dao;
	
	@Test
	public void testSaveMessage() {
		
		Message message = new Message();
		message.setText("text");
		message.setAuthor("author");
		
		dao.saveOrUpdate(message);
		Assert.assertNotNull(dao.get(message));
		dao.delete(message);
	}
	
	@Test
	public void testUpdateTextMessage() {
		
		Message message = new Message();
		message.setText("text");
		message.setAuthor("author");
		
		dao.saveOrUpdate(message);
		message.setText("other text");
		dao.saveOrUpdate(message);
		
		Assert.assertEquals("other text", dao.get(message).getText());
		dao.delete(message);
		
	}
	
	@Test
	public void testSaveAuthorMessageLikeUndefinedWhenAuthorIsNull() {
		
		Message message = new Message();
		message.setText("text");
		
		dao.saveOrUpdate(message);
		
		Assert.assertEquals("Anônimo", dao.get(message).getAuthor());
		dao.delete(message);
		
	}
	
	@Test
	public void testUpdateAuthorMessage() {
		
		Message message = new Message();
		message.setText("text");
		message.setAuthor("author");
		
		dao.saveOrUpdate(message);
		message.setAuthor("other author");
		dao.saveOrUpdate(message);
		
		Assert.assertEquals("other author", dao.get(message).getAuthor());
		dao.delete(message);
		
	}
}
