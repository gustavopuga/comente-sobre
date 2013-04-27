package br.com.talkabout.infrastructure.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import br.com.talkabout.domain.model.Message;
import br.com.talkabout.domain.repository.MessageRepository;

@Repository
public class MessageDAO extends AbstractDAO<Message> implements MessageRepository{
	
	public Message get(long id) {
		try {
			return (Message) getSession().get(getModelClass(), id);
		} catch (ConstraintViolationException exception) {
			throw new IllegalArgumentException(exception);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Message> getBySubject(String subject) {
		
		Criteria criteria = getSession().createCriteria(getModelClass());
		criteria.add(Restrictions.ilike("subject", subject));
		criteria.addOrder(Order.asc("date"));
		
		return criteria.list();
	}
	
	@Override
	protected Class<Message> getModelClass() {
		return Message.class;
	}

}
