package TSM.demo.service;

import TSM.demo.domain.place.Restaurant;
import TSM.demo.domain.place.Room;
import TSM.demo.domain.place.Transport;
import TSM.demo.domain.place.TravelPlace;
import TSM.demo.repository.PlaceRepository;
import TSM.demo.repository.query.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaceService {

    private final PlaceRepository placeRepository;

    @Transactional(readOnly = false)
    public int save(Restaurant restaurant){
        placeRepository.save(restaurant);
        return restaurant.getId();
    }

    @Transactional(readOnly = false)
    public int save(Room room){
        placeRepository.save(room);
        return room.getId();
    }

    @Transactional(readOnly = false)
    public int save(Transport transport){
        placeRepository.save(transport);
        return transport.getId();
    }

    @Transactional(readOnly = false)
    public int save(TravelPlace travelPlace){
        placeRepository.save(travelPlace);
        return travelPlace.getId();
    }
    public List<Restaurant> findAllRestaurants(){
        return placeRepository.findAllRestaurant();
    }
    public Restaurant findRestaurantById(int id){ return placeRepository.findRestaurantById(id); };

    public List<Room> findAllRooms(){ return placeRepository.findAllRoom(); }
    public Room findRoomById(int id){ return placeRepository.findRoomById(id); }

    public List<Transport> findAllTransports(){ return placeRepository.findAllTransport(); }
    public Transport findTransportById(int id){ return placeRepository.findTransportById(id); }

    public List<TravelPlace> findAllTravelPlaces(){ return placeRepository.findAllTravelPlace(); }
    public TravelPlace findTravelPlaceById(int id){ return placeRepository.findTravelPlaceById(id); }


}
