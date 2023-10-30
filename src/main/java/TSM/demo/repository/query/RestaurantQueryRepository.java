package TSM.demo.repository.query;

import TSM.demo.domain.place.Restaurant;
import TSM.demo.repository.PlaceRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RestaurantQueryRepository {

    private final EntityManager em;
    private final PlaceRepository placeRepository;
    public List<RestaurantQueryDto> restaurantQueryDtoList(){
        return em.createQuery("select new TSM.demo.repository.query.RestaurantQueryDto(r.id,r.name,r.description,r.url) " +
                " from restaurant r",RestaurantQueryDto.class)
                .getResultList();
    }
    public RestaurantQueryDto findRestaurantById(int id){
        return createRestaurantQueryDto(placeRepository.findRestaurantById(id));
    }
    public RestaurantQueryDto createRestaurantQueryDto(Restaurant restaurant){
        return new RestaurantQueryDto(restaurant.getId(),restaurant.getName(),restaurant.getDescription(),restaurant.getUrl());
    }
}
