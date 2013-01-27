package br.com.comente_sobre.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.comente_sobre.infrastructure.dao.DiscussionDAO;

public class TalkAboutServiceTest {

	private TalkAboutService service;

	@Before
	public void setUp() {
		service = new TalkAboutService(new DiscussionDAO());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testThrowsExceptionWhenEmptySubject() {
		service.getDiscussion("");
	}

	@Test
	public void testGetNullDiscussionWhenThereIsNoDiscussionOnTheSubject() {
		Assert.assertNull(service.getDiscussion("subject"));
	}
}
