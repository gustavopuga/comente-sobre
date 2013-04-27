package br.com.talkabout.infrastructure.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.talkabout.domain.model.Model;

public abstract class AbstractDAO<T extends Model> {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void saveOrUpdate(T object) {
		try {
			getSession().saveOrUpdate(object);
		} catch (ConstraintViolationException exception) {
			throw new IllegalArgumentException(exception);
		}
	}

	@Transactional
	public void delete(T object) {
		getSession().delete(object);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected abstract Class<T> getModelClass();

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}