package br.com.comente_sobre.domain.model;

import org.junit.Assert;
import org.junit.Test;

public class ConversationTest {

	@Test
	public void testEmptyConversation() {
		Conversation conversation = new Conversation();
		Assert.assertTrue(conversation.isEmpty());
	}
}
