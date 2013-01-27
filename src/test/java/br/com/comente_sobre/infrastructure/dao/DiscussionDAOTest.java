package br.com.comente_sobre.infrastructure.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;


public class DiscussionDAOTest {
	
	protected SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Test
	public void testGetNullDiscussionWhenThereIsNoDiscussionOnTheSubject() {
		DiscussionDAO dao = new DiscussionDAO();
		Assert.assertNull(dao.getBySubject("subject"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testThrowsExceptionWhenCriteriaSubjectIsEmpty() {
		DiscussionDAO dao = new DiscussionDAO();
		Assert.assertNull(dao.getBySubject(""));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testThrowsExceptionWhenWhenCriteriaSubjectIsSpace() {
		DiscussionDAO dao = new DiscussionDAO();
		Assert.assertNull(dao.getBySubject(" "));
	}
	
}
