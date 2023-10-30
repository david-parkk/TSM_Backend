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
    private final RestaurantQueryRepository restaurantQueryRepository;
    private final RoomQueryRepository roomQueryRepository;
    private final TransportQueryRepository transportQueryRepository;
    private final TravelPlaceQueryRepository travelPlaceQueryRepository;

    @PostMapping("/api/place/restaurant")
    public CreatePlaceResponse createRestaurant(@RequestBody @Valid Restaurant restaurant){
        int id=placeService.save(restaurant);
        return new CreatePlaceResponse(id);
    }

    @GetMapping("/api/place/restaurants")
    public List<RestaurantQueryDto> showRestaurants(){
        return restaurantQueryRepository.restaurantQueryDtoList();
    }
    @GetMapping("/api/place/restaurant/{id}")
    public RestaurantQueryDto showRestaurant(@PathVariable int id){
        return restaurantQueryRepository.findRestaurantById(id);
    }

    @PostMapping("/api/place/room")
    public CreatePlaceResponse createRoom(@RequestBody @Valid Room room){
        int id=placeService.save(room);
        return new CreatePlaceResponse(id);
    }
    @GetMapping("/api/place/rooms")
    public List<RoomQueryDto> showRooms(){
        return roomQueryRepository.roomQueryDtoList();
    }
    @GetMapping("/api/place/room/{id}")
    public RoomQueryDto showRoom(@PathVariable int id){ return roomQueryRepository.findRoomById(id); }

    @PostMapping("/api/place/transport")
    public CreatePlaceResponse createTransport(@RequestBody @Valid Transport transport){
        int id=placeService.save(transport);
        return new CreatePlaceResponse(id);
    }
    @GetMapping("/api/place/transports")
    public List<TransportQueryDto> showTransports(){
        return transportQueryRepository.transportQueryDtoList();
    }
    @GetMapping("/api/place/transport/{id}")
    public TransportQueryDto showTransport(@PathVariable int id){ return transportQueryRepository.findTransportById(id); }

    @PostMapping("/api/place/travelPlace")
    public CreatePlaceResponse createTravelPlace(@RequestBody @Valid TravelPlace travelPlace){
        int id=placeService.save(travelPlace);
        return new CreatePlaceResponse(id);
    }
    @GetMapping("/api/place/travelPlaces")
    public List<TravelPlaceQueryDto> showTravelPlaces(){
        return travelPlaceQueryRepository.travelPlaceQueryDtoList();
    }
    @GetMapping("/api/place/travelPlace/{id}")
    public TravelPlaceQueryDto showTravelPlace(@PathVariable int id){ return travelPlaceQueryRepository.findTravelPlaceById(id); }

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
