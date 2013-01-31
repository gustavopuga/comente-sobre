package br.com.comente_sobre.domain.repository;

import br.com.comente_sobre.domain.model.Message;

public interface MessageRepository {

	public void saveOrUpdate(Message message);

	public void delete(Message message);

	public Message get(long messageId);
}
