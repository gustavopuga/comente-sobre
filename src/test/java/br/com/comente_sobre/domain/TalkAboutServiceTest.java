package br.com.comente_sobre.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import br.com.comente_sobre.domain.service.TalkAboutService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/dataContext.xml" })
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class TalkAboutServiceTest {

	@Autowired private TalkAboutService service;
	
	@Test(expected=IllegalArgumentException.class)
	public void testThrowsExceptionWhenEmptySubject() {
		service.getDiscussion("");
	}

	@Test
	public void testGetNullDiscussionWhenThereIsNoDiscussionOnTheSubject() {
		Assert.assertNull(service.getDiscussion("subject"));
	}
}
