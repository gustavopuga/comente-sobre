package br.com.talkabout.domain.repository;

import java.util.Date;
import java.util.List;

import br.com.talkabout.domain.model.Message;

public interface MessageRepository {

	public void saveOrUpdate(Message message);

	public void delete(Message message);

	public Message get(long messageId);
	
	public List<Message> getBySubject(String subject);
	
	public List<Message> getBySubjectAndDate(String subject, Date startSearchDate);
}
