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
    public List<RestaurantQueryDto> showRestaurants(){
        return placeService.findAllRestaurants();

    }
    @GetMapping("/place/restaurant/{id}")
    public RestaurantQueryDto showRestaurant(@PathVariable int id){
        return placeService.findRestaurantById(id);
    }

    @PostMapping("/place/room")
    public CreatePlaceResponse createRoom(@RequestBody @Valid Room room){
        int id=placeService.save(room);
        return new CreatePlaceResponse(id);
    }
    @GetMapping("/place/rooms")
    public List<RoomQueryDto> showRooms(){

        return placeService.findAllRooms();
    }
    @GetMapping("/place/room/{id}")
    public RoomQueryDto showRoom(@PathVariable int id){
        return placeService.findRoomById(id);
    }

    @PostMapping("/place/transport")
    public CreatePlaceResponse createTransport(@RequestBody @Valid Transport transport){
        int id=placeService.save(transport);
        return new CreatePlaceResponse(id);
    }
    @GetMapping("/place/transports")
    public List<TransportQueryDto> showTransports(){

        return placeService.findAllTransports();
    }
    @GetMapping("/place/transport/{id}")
    public TransportQueryDto showTransport(@PathVariable int id){
        return placeService.findTransportById(id);
    }

    @PostMapping("/place/travelPlace")
    public CreatePlaceResponse createTravelPlace(@RequestBody @Valid TravelPlace travelPlace){
        int id=placeService.save(travelPlace);
        return new CreatePlaceResponse(id);
    }
    @GetMapping("/place/travelPlaces")
    public List<TravelPlaceQueryDto> showTravelPlaces(){

        return placeService.findAllTravelPlaces();
    }
    @GetMapping("/place/travelPlace/{id}")
    public TravelPlaceQueryDto showTravelPlace(@PathVariable int id){
        return placeService.findTravelPlaceById(id);
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