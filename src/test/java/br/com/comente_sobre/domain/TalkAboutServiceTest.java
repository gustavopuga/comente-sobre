package br.com.comente_sobre.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TalkAboutServiceTest {

	private TalkAboutService service;
	
	@Before
	public void setUp() {
		service = new TalkAboutService();
	}
	
	@Test
	public void testGetConversationByEmptySubject() {
		Assert.assertTrue(service.getConversation("").isEmpty());
	}
}
