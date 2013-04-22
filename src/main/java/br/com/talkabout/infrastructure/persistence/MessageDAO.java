package br.com.talkabout.infrastructure.persistence;

import org.springframework.stereotype.Repository;

import br.com.talkabout.domain.model.Message;
import br.com.talkabout.domain.repository.MessageRepository;

@Repository
public class MessageDAO extends AbstractDAO<Message> implements MessageRepository{
	
	@Override
	protected Class<Message> getModelClass() {
		return Message.class;
	}

}
