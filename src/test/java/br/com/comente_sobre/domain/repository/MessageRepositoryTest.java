package br.com.comente_sobre.domain.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import br.com.comente_sobre.domain.model.Message;

@ContextConfiguration(locations = { "classpath:spring/dataContext.xml" })
public class MessageRepositoryTest extends AbstractJUnit4SpringContextTests{

	@Autowired protected MessageRepository repository;
	
	@Test
	public void testSaveMessage() {
		
		Message message = new Message();
		message.setText("text");
		message.setAuthor("author");
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
		message.setAuthor("author");
		message.setSubject("subject");
		message.setDate(Calendar.getInstance().getTime());
		
		repository.saveOrUpdate(message);
		message.setText("other text");
		repository.saveOrUpdate(message);
		
		assertEquals("other text", repository.get(message.getId()).getText());
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
		message.setAuthor("author");
		
		repository.saveOrUpdate(message);
	}
	
	@Test
	public void testUpdateAuthorMessage() {
		
		Message message = new Message();
		message.setText("text");
		message.setAuthor("author");
		message.setSubject("subject");
		message.setDate(Calendar.getInstance().getTime());
		
		repository.saveOrUpdate(message);
		message.setAuthor("other author");
		repository.saveOrUpdate(message);
		
		assertEquals("other author", repository.get(message.getId()).getAuthor());
		repository.delete(message);
		
	}
}
