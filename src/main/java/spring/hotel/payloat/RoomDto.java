package spring.hotel.payloat;

import lombok.Data;

@Data
public class RoomDto {
    private long number;
    private Integer floor;
    private String size;
    private Integer hotelId;
}
