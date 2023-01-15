package spring.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.hotel.model.Hotel;
import spring.hotel.repository.HotelRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    HotelRepository hotelRepository;

    @GetMapping
    public List<Hotel> getHotel(){
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels;
    }

    @PostMapping
    public String addHotel(@RequestBody Hotel hotel){

        boolean exists = hotelRepository.existsHotelByName(hotel.getName());
        if (exists)
            return "This Hotel already exists";

        Hotel savehotel = new Hotel();
        savehotel.setName(hotel.getName());
        hotelRepository.save(savehotel);

        return "Add hotel";
    }

    @DeleteMapping("/{id}")
    public String deleteHotel(@PathVariable Integer id){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (!optionalHotel.isPresent())
            return "Hotel not found";
        hotelRepository.deleteById(id);
        return "Hotel deleted";
    }

    @PutMapping("/{id}")
    public String editedHotel(@PathVariable Integer id,@RequestBody Hotel hotel){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (!optionalHotel.isPresent())
            return "Hotel not found";
        Hotel edithotel = optionalHotel.get();
        edithotel.setName(hotel.getName());
        hotelRepository.save(edithotel);
        return "Hotel edited";
    }
}
