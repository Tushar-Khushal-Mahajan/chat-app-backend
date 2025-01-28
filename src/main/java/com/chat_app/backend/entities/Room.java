package com.chat_app.backend.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection = "rooms")

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Room {

	@Id
	private String id; // mongoDb uniqye identifier
	private String roomId;

	private List<Message> messages = new ArrayList<Message>();

}
