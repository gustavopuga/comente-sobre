package br.com.talkabout.infrastructure.persistence;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.talkabout.domain.model.Message;
import br.com.talkabout.domain.repository.MessageRepository;

@Repository
public class MessageDAO extends AbstractDAO<Message> implements MessageRepository {
	
	public Message get(Long id) {
		return super.get(id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Message> getBySubject(String subject) {
		
		Criteria criteria = getSession().createCriteria(getModelClass());
		criteria.add(Restrictions.ilike("subject", subject));
		criteria.addOrder(Order.asc("date"));
		
		return criteria.list() ;
	}

	@SuppressWarnings("unchecked")
	public List<Message> getBySubjectAndDate(String subject, Date date) {
		
		Criteria criteria = getSession().createCriteria(getModelClass());
		criteria.add(Restrictions.ilike("subject", subject));
		criteria.add(Restrictions.gt("date", date));
		criteria.addOrder(Order.asc("date"));
		
		return criteria.list();
	}
	
	@Override
	protected Class<Message> getModelClass() {
		return Message.class;
	}

}
