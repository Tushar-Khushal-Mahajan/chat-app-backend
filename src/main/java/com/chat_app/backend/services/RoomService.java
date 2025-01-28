package com.chat_app.backend.services;

import org.springframework.stereotype.Service;

import com.chat_app.backend.entities.Room;
import com.chat_app.backend.repository.RoomRepository;

@Service
public class RoomService {

	private RoomRepository roomRepository;

	public RoomService(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}

//	---------------------
	public Room getRoomByRoomId(String roomId) {
		return roomRepository.findByRoomId(roomId);
	}

	public Room createRoom(String roomId) {

		Room room = Room.builder().roomId(roomId).build();

		return roomRepository.save(room);
	}

}
