package TSM.demo.repository.query;

import TSM.demo.domain.place.Room;
import TSM.demo.domain.place.Transport;
import TSM.demo.repository.PlaceRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TransportQueryRepository {

    private final EntityManager em;
    private final PlaceRepository placeRepository;

    public List<TransportQueryDto> transportQueryDtoList(){
        return em.createQuery("select new TSM.demo.repository.query.TransportQueryDto(r.id,r.name,r.description,r.url) " +
                        " from transport r",TransportQueryDto.class)
                .getResultList();
    }
    public TransportQueryDto findTransportById(int id){
        return createTransportQueryDto(placeRepository.findTransportById(id));
    }
    public TransportQueryDto createTransportQueryDto(Transport transport){
        return new TransportQueryDto(transport.getId(),transport.getName(),transport.getDescription(),transport.getUrl());
    }
}
