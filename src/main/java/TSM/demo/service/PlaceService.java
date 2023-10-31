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
    private final RestaurantQueryRepository restaurantQueryRepository;
    private final RoomQueryRepository roomQueryRepository;
    private final TransportQueryRepository transportQueryRepository;
    private final TravelPlaceQueryRepository travelPlaceQueryRepository;
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

    public List<RestaurantQueryDto> findAllRestaurants(){
        return restaurantQueryRepository.restaurantQueryDtoList();
    }
    public RestaurantQueryDto findRestaurantById(int id){
        return restaurantQueryRepository.findRestaurantById(id);
    }
    public List<RoomQueryDto> findAllRooms(){
        return roomQueryRepository.roomQueryDtoList();
    }
    public RoomQueryDto findRoomById(int id){
        return roomQueryRepository.findRoomById(id);
    }

    public List<TransportQueryDto> findAllTransports(){
        return transportQueryRepository.transportQueryDtoList();
    }
    public TransportQueryDto findTransportById(int id){
        return transportQueryRepository.findTransportById(id);
    }

    public List<TravelPlaceQueryDto> findAllTravelPlaces(){
        return travelPlaceQueryRepository.travelPlaceQueryDtoList();
    }
    public TravelPlaceQueryDto findTravelPlaceByid(int id){
        return travelPlaceQueryRepository.findTravelPlaceById(id);
    }

}
