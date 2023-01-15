package spring.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.hotel.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,Integer> {
    boolean existsHotelByName(String name);
}
