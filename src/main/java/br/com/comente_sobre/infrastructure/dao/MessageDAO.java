package br.com.comente_sobre.infrastructure.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.comente_sobre.domain.model.Message;

@Repository
public class MessageDAO extends AbstractDAO<Message>{

	@Override
	@Transactional
	public void saveOrUpdate(Message message) {
		if(message.getAuthor() == null){
			message.setAuthor("Anônimo");
		}
		super.saveOrUpdate(message);
	}
	
	@Override
	protected Class<Message> getModelClass() {
		return Message.class;
	}

}
