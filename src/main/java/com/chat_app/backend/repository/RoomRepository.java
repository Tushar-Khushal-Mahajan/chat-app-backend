package com.chat_app.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.chat_app.backend.entities.Room;

@Repository
public interface RoomRepository extends MongoRepository<Room, String> {

	Room findByRoomId(String roomId);
}
