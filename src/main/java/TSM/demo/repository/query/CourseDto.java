package TSM.demo.repository.query;


import TSM.demo.domain.place.Restaurant;
import TSM.demo.domain.place.Room;
import TSM.demo.domain.place.Transport;
import TSM.demo.domain.place.TravelPlace;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class CourseDto {
    private int id;
    private String name;

    private String region;

    private String description;

    private String url;

    private UserHealthDto userHealth;

    private List<RestaurantDto> restaurantList;

    private List<RoomDto> roomList;
    
    private List<TransportDto> transportList;

    private List<TravelPlaceDto> travelPlaceList;

    public CourseDto(int id,String name, String region, String description, String url) {
        this.id=id;
        this.name = name;
        this.region = region;
        this.description = description;
        this.url = url;
        restaurantList = new ArrayList<>();
        roomList = new ArrayList<>();
        transportList = new ArrayList<>();
        travelPlaceList = new ArrayList<>();
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        if(!restaurantList.isEmpty()) {
            for (Restaurant restaurant : restaurantList) {
                this.restaurantList.add(restaurant.toDto());
            }
        }
    }

    public void setRoomList(List<Room> roomList) {
        if(!roomList.isEmpty()) {
            for (Room room : roomList) {
                this.roomList.add(room.toDto());
            }
        }
    }

    public void setTransportList(List<Transport> transportList) {
        if(!transportList.isEmpty()) {
            for (Transport transport : transportList) {
                this.transportList.add(transport.toDto());
            }
        }
    }

    public void setTravelPlaces(List<TravelPlace> travelPlaces) {
        if(!travelPlaces.isEmpty()) {
            for (TravelPlace travelPlace : travelPlaces) {
                this.travelPlaceList.add(travelPlace.toDto());
            }
        }
    }
}
