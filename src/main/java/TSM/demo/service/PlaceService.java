package TSM.demo.service;

import TSM.demo.domain.Matching;
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

import java.util.*;

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
    public List<Restaurant> findAllRestaurantByMatching(List<Matching> matchings){
        List<Restaurant> restaurants = findAllRestaurants();
        Map<Integer,Integer> map=new HashMap<>();
        for (Matching matching : matchings) {
            map.put(matching.getRequestId(),2);

        }
        List<Restaurant> result=new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            Integer value=map.get(restaurant.getId());
            if (value != null && value == 1) {
                result.add(restaurant);
            }
        }
        return result;
    }

    public List<Room> findAllRoomByMatching(List<Matching> matchings){
        List<Room> rooms = findAllRooms();
        Map<Integer,Integer> map=new HashMap<>();
        for (Matching matching : matchings) {
            map.put(matching.getRequestId(),3);

        }
        List<Room> result=new ArrayList<>();
        for (Room room : rooms) {
            Integer value=map.get(room.getId());
            if (value != null && value == 1) {
                result.add(room);
            }
        }
        return result;
    }

    public List<Transport> findAllTransportByMatching(List<Matching> matchings){
        List<Transport> transports= findAllTransports();
        Map<Integer,Integer> map=new HashMap<>();
        for (Matching matching : matchings) {
            map.put(matching.getRequestId(),4);

        }
        List<Transport> result=new ArrayList<>();
        for (Transport transport : transports) {
            Integer value=map.get(transport.getId());
            if (value != null && value == 1) {
                result.add(transport);
            }
        }
        return result;
    }

    public List<TravelPlace> findAllTravelPlaceByMatching(List<Matching> matchings){
        List<TravelPlace> travelPlaces= findAllTravelPlaces();
        Map<Integer,Integer> map=new HashMap<>();
        for (Matching matching : matchings) {
            map.put(matching.getRequestId(),5);

        }
        List<TravelPlace> result=new ArrayList<>();
        for (TravelPlace travelPlace : travelPlaces) {
            Integer value=map.get(travelPlace.getId());
            if (value != null && value == 1) {
                result.add(travelPlace);
            }
        }
        return result;
    }

}
