package spring.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.hotel.model.Room;

public interface RoomRepository extends JpaRepository<Room,Integer> {
    boolean existsRoomByNumber(long number);
}
