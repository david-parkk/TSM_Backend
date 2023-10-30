package TSM.demo.repository.query;

import TSM.demo.domain.place.Restaurant;
import TSM.demo.domain.place.Room;
import TSM.demo.repository.PlaceRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoomQueryRepository {

    private final EntityManager em;
    private final PlaceRepository placeRepository;

    public List<RoomQueryDto> roomQueryDtoList(){
        return em.createQuery("select new TSM.demo.repository.query.RoomQueryDto(r.id,r.name,r.description,r.url) " +
                        " from restaurant r",RoomQueryDto.class)
                .getResultList();
    }
    public RoomQueryDto findRoomById(int id){
        return createRoomQueryDto(placeRepository.findRoomById(id));
    }
    public RoomQueryDto createRoomQueryDto(Room room){
        return new RoomQueryDto(room.getId(),room.getName(),room.getDescription(),room.getUrl());
    }

}
