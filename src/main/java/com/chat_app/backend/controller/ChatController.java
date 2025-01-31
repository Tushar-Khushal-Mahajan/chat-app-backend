package com.chat_app.backend.controller;

import java.time.LocalDateTime;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.chat_app.backend.entities.Message;
import com.chat_app.backend.entities.MessageRequest;
import com.chat_app.backend.entities.Room;
import com.chat_app.backend.services.RoomService;

@Controller
@CrossOrigin(origins = "http://localhost:5173/")
public class ChatController {

	private RoomService roomService;

	public ChatController(RoomService roomService) {
		this.roomService = roomService;
	}

	@MessageMapping("/sendMessage/{roomId}") // send-message <app/sendMessage/{roomId}>
	@SendTo("/topic/room/{roomId}") // subscribe
	public Message sendMessage(@DestinationVariable String roomId, @RequestBody MessageRequest request)

	{

		System.out.println("message request : " + request);

		Room room = roomService.getRoomByRoomId(request.getRoomId());

		Message message = new Message();
		message.setContent(request.getContent());
		message.setSender(request.getSender());
		message.setTime(LocalDateTime.now());

		if (room != null) {

			room.getMessages().add(message);
			Room saveRoom = roomService.saveRoom(room);
			System.out.println("saved message :" + saveRoom);
		} else {
			throw new RuntimeException("Room not found");
		}
		return message;
	}

}
