package TSM.demo.repository.query;

import TSM.demo.domain.place.Transport;
import TSM.demo.domain.place.TravelPlace;
import TSM.demo.repository.PlaceRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TravelPlaceQueryRepository {

    private final EntityManager em;
    private final PlaceRepository placeRepository;

    public List<TravelPlaceQueryDto> travelPlaceQueryDtoList(){
        return em.createQuery("select new TSM.demo.repository.query.TravelPlaceQueryDto(r.id,r.name,r.description,r.url) " +
                        " from transport r",TravelPlaceQueryDto.class)
                .getResultList();
    }
    public TravelPlaceQueryDto findTravelPlaceById(int id){
        return createTravelPlaceQueryDto(placeRepository.findTravelPlaceById(id));
    }
    public TravelPlaceQueryDto createTravelPlaceQueryDto(TravelPlace travelPlace){
        return new TravelPlaceQueryDto(travelPlace.getId(),travelPlace.getName(),travelPlace.getDescription(),travelPlace.getUrl());
    }
}
