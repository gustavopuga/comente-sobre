package br.com.talkabout.domain.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class DiscussionTest {

	@Test
	public void testEmptyDiscussionWhenThereIsNotMessages() {
		Discussion conversation = new Discussion();
		Assert.assertTrue(conversation.isEmpty());
	}
	
	@Test
	public void testEmptyConversationWhenMessagesListIsEmpty() {
		
		Discussion conversation = new Discussion();
		conversation.setMessages(new ArrayList<Message>());
		
		Assert.assertEquals(conversation.isEmpty(), conversation.getMessages().isEmpty());
	}
	
	@Test
	public void testNotEmptyConversationWhenMessagesListNotIsEmpty() {
		
		List<Message> messages = new ArrayList<Message>();
		messages.add(new Message());
		
		Discussion conversation = new Discussion();
		conversation.setMessages(messages);
		
		Assert.assertEquals(conversation.isEmpty(), conversation.getMessages().isEmpty());
	}
}
