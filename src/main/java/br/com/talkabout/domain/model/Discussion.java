package br.com.talkabout.domain.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "discussion")
public class Discussion implements Model {

	@Id
	@Column(name = "subject")
	private String subject;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="subject")
	private List<Message> messages;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date", nullable = false)
	private Date startDate;

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public boolean isEmpty() {
		return messages == null ? true : messages.isEmpty();
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}
