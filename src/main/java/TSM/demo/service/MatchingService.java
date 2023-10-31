package TSM.demo.service;

import TSM.demo.repository.MatchingRepository;
import TSM.demo.repository.query.MatchingQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchingService {

    private final MatchingRepository matchingRepository;

    public List<MatchingQueryDto> findAllByVolunteerId(int id){
        return matchingRepository.findAllInfoByVolunteerId(id);
    }


}
