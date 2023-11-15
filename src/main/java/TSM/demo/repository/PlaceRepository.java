package TSM.demo.repository;

import TSM.demo.domain.Matching;
import TSM.demo.domain.place.*;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PlaceRepository {

    private final EntityManager em;

    public void save(Place place){
        em.persist(place);
    }
    public void save(Restaurant restaurant){
        em.persist(restaurant);
    }
    public void save(Room room){
        em.persist(room);
    }
    public void save(Transport transport){
        em.persist(transport);
    }
    public void save(TravelPlace travelPlace){
        em.persist(travelPlace);
    }

    public Restaurant findRestaurantById(int id){
        return em.find(Restaurant.class,id);
    }
    public List<Restaurant> findAllRestaurant(){
        return em.createQuery("select i from restaurant i",Restaurant.class)
                .getResultList();
    }
    public Room findRoomById(int id){
        return em.find(Room.class,id);
    }
    public List<Room> findAllRoom(){
        return em.createQuery("select i from room i",Room.class)
                .getResultList();
    }
    public Transport findTransportById(int id){
        return em.find(Transport.class,id);
    }
    public List<Transport> findAllTransport(){
        return em.createQuery("select i from transport i",Transport.class)
                .getResultList();
    }
    public TravelPlace findTravelPlaceById(int id){
        return em.find(TravelPlace.class,id);
    }

    public List<TravelPlace> findAllTravelPlace(){
        return em.createQuery("select i from travel_place i",TravelPlace.class)
                .getResultList();
    }

}
