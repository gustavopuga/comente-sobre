package br.com.talkabout.domain.repository;

import java.util.Date;
import java.util.List;

import br.com.talkabout.domain.model.Message;

public interface MessageRepository {

	public void saveOrUpdate(Message message);

	public void delete(Message message);

	public Message get(Long messageId);
	
	public List<Message> getBySubject(String subject);
	
	public List<Message> getBySubjectAndStartDate(String subject, Date startSearchDate);
}
