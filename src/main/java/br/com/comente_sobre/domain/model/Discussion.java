package br.com.comente_sobre.domain.model;

import static javax.persistence.FetchType.EAGER;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "discussion")
public class Discussion implements Model {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "subject", unique = true, nullable = false)
	private String subject;

	@OrderBy("date DESC")
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = EAGER)
	private List<Message> messages;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date", nullable = false)
	private Calendar startDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

}
