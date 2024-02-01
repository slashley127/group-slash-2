package org.launchcode.roomranger.controllers;

import jakarta.validation.Valid;
import org.launchcode.roomranger.data.CommentRepository;
import org.launchcode.roomranger.exception.NotFoundException;
import org.launchcode.roomranger.data.RoomRepository;
import org.launchcode.roomranger.models.Comment;
import org.launchcode.roomranger.models.Room;
import org.launchcode.roomranger.models.Type;
import org.launchcode.roomranger.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)

public class RoomsController {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomService roomService;
    @Autowired
    CommentRepository commentRepository;

    @PostMapping("/room")
    public ResponseEntity<?> addRoom(@RequestBody @Valid Room newRoom){
        String roomNumber = newRoom.getRoomNumber();
        if (roomService.isRoomNumberExists(roomNumber)) {
            return ResponseEntity.badRequest().body("Room number already exists");
        }
        return new ResponseEntity<>(roomRepository.save(newRoom),HttpStatus.OK);
    }

    @GetMapping("/rooms")
    public List<Room> getAllRoomsSortByNumber(){
        Sort sortByNumber = Sort.by(Sort.Order.asc("roomNumber"));
        return roomRepository.findAll(sortByNumber);
    }
//    public ResponseEntity<?> getAllRooms(){
//        Sort sortByNumber = Sort.by(Sort.Order.asc("roomNumber"));
//        List<Room> rooms =(List<Room>) roomRepository.findAll(sortByNumber);
//        return new ResponseEntity<>(rooms, HttpStatus.OK);
//    }

    @GetMapping("/types")
    public Map<String, String> getTypes() {
        Map<String, String> types = new HashMap<>();
        for (Type type : Type.values()) {
            types.put(type.name(), type.getDisplayName());
        }
        return types;
    }
    @GetMapping("/type/{id}")
    public String getType(@PathVariable int id) {
        return roomRepository.findById(id).get().getRoomType().getDisplayName();
    }

    @GetMapping("room/{id}")
    public Room getRoomById(@PathVariable int id){
        return  roomRepository.findById(id)
                .orElseThrow(()->new NotFoundException("room with id " + id));
    }

    @PutMapping("room/{id}")
    public Room updateRoom(@RequestBody Room newRoom,@PathVariable int id){
        return  roomRepository.findById(id)
                .map(room -> {
                    room.setRoomNumber(newRoom.getRoomNumber());
                    room.setRoomType(newRoom.getRoomType());
                    room.setAvailable(newRoom.isAvailable());
                    return roomRepository.save(room);
                }).orElseThrow(()->new NotFoundException("room with id " + id));
    }
    @DeleteMapping("room/{id}")
    public String deleteRoom(@PathVariable int id){
        if (!roomRepository.existsById(id)){
            throw new NotFoundException("room with id " + id);
        }
        roomRepository.deleteById(id);
        return "Room with room Id " + id + " has been deleted successfully!";
    }

    @PostMapping("room/comment")
    public String addComment(@RequestParam int roomId, @ModelAttribute @Valid Comment newComment){
        Optional<Room> result = roomRepository.findById(roomId);
        Room room = result.get();
        newComment.setRoom(room);
        newComment.setCreatedDate(LocalDate.now());
        commentRepository.save(newComment);
        return "redirect:/rooms";
    }
}
