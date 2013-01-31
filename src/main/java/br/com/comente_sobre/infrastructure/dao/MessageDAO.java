package br.com.comente_sobre.infrastructure.dao;

import org.springframework.stereotype.Repository;

import br.com.comente_sobre.domain.model.Message;
import br.com.comente_sobre.domain.repository.MessageRepository;

@Repository
public class MessageDAO extends AbstractDAO<Message> implements MessageRepository{
	
	@Override
	protected Class<Message> getModelClass() {
		return Message.class;
	}

}
