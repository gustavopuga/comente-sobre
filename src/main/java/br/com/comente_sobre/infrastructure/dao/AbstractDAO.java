package br.com.comente_sobre.infrastructure.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.comente_sobre.domain.model.Model;

public abstract class AbstractDAO<T extends Model> {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void saveOrUpdate(T object) {
		getSession().saveOrUpdate(object);
	}

	@Transactional
	public void delete(T object) {
		getSession().delete(object);
	}

	@SuppressWarnings("unchecked")
	public T get(T object) {
		return (T) getSession().get(getModelClass(), object.getId());
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	protected abstract Class<T> getModelClass();

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}