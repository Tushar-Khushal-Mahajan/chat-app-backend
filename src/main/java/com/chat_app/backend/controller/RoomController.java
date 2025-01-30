package com.chat_app.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chat_app.backend.entities.Message;
import com.chat_app.backend.entities.Room;
import com.chat_app.backend.services.RoomService;

@RestController
@RequestMapping("api/v1/rooms")
@CrossOrigin(origins = "http://localhost:5173/")
public class RoomController {

	private RoomService roomService;

	public RoomController(RoomService roomService) {
		this.roomService = roomService;
	}

	// create room
	@PostMapping
	public ResponseEntity<?> createRoom(@RequestBody String roomId) {

		System.out.println("Room id : " + roomId);

		if (roomService.getRoomByRoomId(roomId) != null) {
			// Room already exists

			return ResponseEntity.badRequest().body("Room already Exists..");
		} else {
			// create room
			Room room = roomService.createRoom(roomId);

			return ResponseEntity.status(HttpStatus.CREATED).body(room);
		}
	}

	// Get room
	@GetMapping("/{roomId}")
	public ResponseEntity<?> joinRoom(@PathVariable("roomId") String roomId) {

		Room room = roomService.getRoomByRoomId(roomId);

		if (room == null) {
			return ResponseEntity.badRequest().body("Room not found");
		} else {
			return ResponseEntity.ok(room);
		}
	}

	// Get Message of room
	@GetMapping("/{roomId}/messages")
	public ResponseEntity<List<Message>> getMessages(@PathVariable("roomId") String roomId,
			@RequestParam(value = "page", defaultValue = "0", required = false) int page,
			@RequestParam(value = "size", defaultValue = "20", required = false) int size)

	{

		Room room = roomService.getRoomByRoomId(roomId);

		if (room == null) {
			return ResponseEntity.badRequest().build();
		} else {

			List<Message> messages = room.getMessages();

			int start = Math.max(0, messages.size() - (page + 1) * size);
			int end = Math.min(messages.size(), start + size);

			List<Message> paginateList = messages.subList(start, end);

			return ResponseEntity.ok(paginateList);
		}
	}

}
