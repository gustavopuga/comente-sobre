package br.com.talkabout.infrastructure.persistence;

import java.io.Serializable;

import org.hibernate.PropertyValueException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.id.IdentifierGenerationException;
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
		} catch (ConstraintViolationException | PropertyValueException
				| IdentifierGenerationException exception) {
			throw new IllegalArgumentException(exception);
		}
	}

	@Transactional
	public void delete(T object) {
		getSession().delete(object);
	}

	@SuppressWarnings("unchecked")
	protected T get(Serializable id) {
		try {
			return (T) getSession().get(getModelClass(), id);
		} catch (ConstraintViolationException | PropertyValueException exception) {
			throw new IllegalArgumentException(exception);
		}
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected abstract Class<T> getModelClass();

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}