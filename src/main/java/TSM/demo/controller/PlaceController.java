package TSM.demo.controller;

import TSM.demo.domain.place.Restaurant;
import TSM.demo.domain.place.Room;
import TSM.demo.domain.place.Transport;
import TSM.demo.domain.place.TravelPlace;
import TSM.demo.repository.PlaceRepository;
import TSM.demo.repository.query.*;
import TSM.demo.service.PlaceService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;


    @PostMapping("/place/restaurant")
    public CreatePlaceResponse createRestaurant(@RequestBody @Valid Restaurant restaurant){
        int id=placeService.save(restaurant);
        return new CreatePlaceResponse(id);
    }

    @GetMapping("/place/restaurants")
    public List<RestaurantDto> showRestaurants(){
        List<RestaurantDto> restaurantDtos=new ArrayList<>();
        placeService.findAllRestaurants().stream().forEach(o->restaurantDtos.add(o.toDto()));
        return restaurantDtos;
    }
    @GetMapping("/place/restaurant/{id}")
    public RestaurantDto showRestaurant(@PathVariable int id){
        return placeService.findRestaurantById(id).toDto();
    }

    @PostMapping("/place/room")
    public CreatePlaceResponse createRoom(@RequestBody @Valid Room room){
        int id=placeService.save(room);
        return new CreatePlaceResponse(id);
    }
    @GetMapping("/place/rooms")
    public List<RoomDto> showRooms(){
        List<RoomDto> roomDtos=new ArrayList<>();
        placeService.findAllRooms().stream().forEach(o->roomDtos.add(o.toDto()));
        return roomDtos;
    }
    @GetMapping("/place/room/{id}")
    public RoomDto showRoom(@PathVariable int id){

        return placeService.findRoomById(id).toDto();
    }

    @PostMapping("/place/transport")
    public CreatePlaceResponse createTransport(@RequestBody @Valid Transport transport){
        int id=placeService.save(transport);
        return new CreatePlaceResponse(id);
    }
    @GetMapping("/place/transports")
    public List<TransportDto> showTransports(){
        List<TransportDto> transportDtos=new ArrayList<>();
        placeService.findAllTransports().stream().forEach(o->transportDtos.add(o.toDto()));
        return transportDtos;

    }
    @GetMapping("/place/transport/{id}")
    public TransportDto showTransport(@PathVariable int id){

        return placeService.findTransportById(id).toDto();
    }

    @PostMapping("/place/travelPlace")
    public CreatePlaceResponse createTravelPlace(@RequestBody @Valid TravelPlace travelPlace){
        int id=placeService.save(travelPlace);
        return new CreatePlaceResponse(id);
    }
    @GetMapping("/place/travelPlaces")
    public List<TravelPlaceDto> showTravelPlaces(){
        List<TravelPlaceDto>travelPlaceDtos=new ArrayList<>();
        placeService.findAllTravelPlaces().stream().forEach(o->travelPlaceDtos.add(o.toDto()));
        return travelPlaceDtos;
    }
    @GetMapping("/place/travelPlace/{id}")
    public TravelPlaceDto showTravelPlace(@PathVariable int id){

        return placeService.findTravelPlaceById(id).toDto();
    }

    @Setter
    @Getter
    @ToString
    static class CreatePlaceResponse{
        private int id;
        public CreatePlaceResponse(int id){
            this.id=id;
        }
    }
}