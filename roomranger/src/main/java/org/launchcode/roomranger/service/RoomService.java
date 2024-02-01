package org.launchcode.roomranger.service;

import org.launchcode.roomranger.data.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RoomService {

        @Autowired
        private RoomRepository roomRepository;
        public boolean isRoomNumberExists(String roomNumber){
            return roomRepository.existsByRoomNumber(roomNumber);
        }

}
