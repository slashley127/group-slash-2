package org.launchcode.roomranger.controllers;

import jakarta.validation.Valid;
import org.launchcode.roomranger.data.CommentRepository;
import org.launchcode.roomranger.data.RoomAttendantRepository;
import org.launchcode.roomranger.data.RoomRepository;
import org.launchcode.roomranger.exception.NotFoundException;
import org.launchcode.roomranger.models.Room;
import org.launchcode.roomranger.models.Status;
import org.launchcode.roomranger.models.Type;
import org.launchcode.roomranger.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping("rooms")
public class RoomsController {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomService roomService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private RoomAttendantRepository roomAttendantRepository;

    @PostMapping("/room")
     public ResponseEntity<?> addRoom(@RequestBody @Valid Room newRoom){
        String roomNumber = newRoom.getRoomNumber();
        if (roomService.isRoomNumberExists(roomNumber)) {
            Map<String, String> errors = new HashMap<>();
            errors.put("roomNumber", "Room number already exists");
            return ResponseEntity.badRequest().body(errors);
        }
        return new ResponseEntity<>(roomRepository.save(newRoom),HttpStatus.OK);
    }

    @GetMapping()
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

    @GetMapping("/room/{id}")
    public Room getRoomById(@PathVariable int id){
        return  roomRepository.findById(id)
                .orElseThrow(()->new NotFoundException("room with id " + id));
    }

    @PutMapping("/room/{id}")
     public Room updateRoom(@RequestBody @Valid Room newRoom,@PathVariable int id){
         return roomRepository.findById(id)
                .map(room -> {
                    room.setRoomNumber(newRoom.getRoomNumber());
                    room.setRoomType(newRoom.getRoomType());
                    room.setAvailable(newRoom.isAvailable());
                    return roomRepository.save(room);
                }).orElseThrow(()->new NotFoundException("room with id " + id));
    }
    @DeleteMapping("/room/{id}")
       public String deleteRoom(@PathVariable int id){
        if (!roomRepository.existsById(id)){
            throw new NotFoundException("room with id " + id);
        }
        String roomNumber = roomRepository.findById(id).get().getRoomNumber();
        roomRepository.deleteById(id);
        return "Room with number " + roomNumber + " has been deleted successfully!";
    }

//    @GetMapping("/attendant{id}")
//    public Room getRoomByAttendant(@PathVariable int id){
//        return  roomRepository.findByAttendantId(id)
//                .orElseThrow(()->new NotFoundException("room with id " + id));
//    }

    @GetMapping("/status")
    public Map<String, String> getStatus() {
        Map<String, String> statusList = new HashMap<>();
        for (Status status : Status.values()) {
            statusList.put(status.name(), status.getDisplayName());
        }
        return statusList;
    }
}
