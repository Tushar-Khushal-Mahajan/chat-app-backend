package com.chat_app.backend.entities;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Message {

	private String sender;
	private String content;
	private LocalDateTime time;

	public Message(String sender, String content) {
		super();
		this.sender = sender;
		this.content = content;
		this.time = LocalDateTime.now();
	}

}
