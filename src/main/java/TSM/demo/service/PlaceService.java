package TSM.demo.service;

import TSM.demo.domain.place.Restaurant;
import TSM.demo.domain.place.Room;
import TSM.demo.domain.place.Transport;
import TSM.demo.domain.place.TravelPlace;
import TSM.demo.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
