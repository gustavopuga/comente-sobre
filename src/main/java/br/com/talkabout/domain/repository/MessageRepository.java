package br.com.talkabout.domain.repository;

import br.com.talkabout.domain.model.Message;

public interface MessageRepository {

	public void saveOrUpdate(Message message);

	public void delete(Message message);

	public Message get(long messageId);
}
