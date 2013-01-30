package br.com.comente_sobre.infrastructure.dao;

import org.springframework.stereotype.Repository;

import br.com.comente_sobre.domain.model.Message;

@Repository
public class MessageDAO extends AbstractDAO<Message>{
	
	@Override
	protected Class<Message> getModelClass() {
		return Message.class;
	}

}
