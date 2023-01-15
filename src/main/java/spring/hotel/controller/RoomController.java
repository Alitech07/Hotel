package spring.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import spring.hotel.model.Hotel;
import spring.hotel.model.Room;
import spring.hotel.payloat.RoomDto;
import spring.hotel.repository.HotelRepository;
import spring.hotel.repository.RoomRepository;

import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    RoomRepository roomRepository;
    //BARCHA ROOM LARNI KORSATUVHI FUNKSIYA
    @GetMapping
    public Page<Room> getRoom(@RequestParam int page){
        Pageable roomPage = PageRequest.of(page,5);
        Page<Room> rooms = roomRepository.findAll(roomPage);
        return rooms;
    }

    //YANGI ROOM QO'SHISH
    @PostMapping
    public String addRoom(@RequestBody RoomDto roomDto){
        Optional<Hotel> hotel = hotelRepository.findById(roomDto.getHotelId());
        if (!hotel.isPresent())
            return "Hotel not found";
        boolean exists = roomRepository.existsRoomByNumber(roomDto.getNumber());
        if (exists)
            return "This room already exists";
        Room room = new Room();
        room.setNumber(roomDto.getNumber());
        room.setFloor(roomDto.getFloor());
        room.setSize(roomDto.getSize());
        room.setHotel(hotel.get());
        roomRepository.save(room);
        return "Added room";
    }

    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable Integer id){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent())
            return "Not found room";
        roomRepository.deleteById(id);
        return "Deleted Room";
    }

    @PutMapping("/{id}")
    public String editRoom(@PathVariable Integer id,@RequestBody RoomDto roomDto){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent())
            return "Room not found";
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        Room room = optionalRoom.get();
        room.setNumber(roomDto.getNumber());
        room.setFloor(roomDto.getFloor());
        room.setSize(roomDto.getSize());
        room.setHotel(optionalHotel.get());
        roomRepository.save(room);
        return "Edit Room";
    }

}
