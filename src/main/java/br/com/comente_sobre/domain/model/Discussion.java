package br.com.comente_sobre.domain.model;

import java.util.List;


public class Discussion {

	private List<Message> messages;
	
	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	public boolean isEmpty() {
		return messages == null ? true : messages.isEmpty();
	}

}
