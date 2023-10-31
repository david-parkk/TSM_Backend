package TSM.demo.service;

import TSM.demo.domain.User;
import TSM.demo.domain.UserHealth;
import TSM.demo.domain.place.*;
import TSM.demo.repository.CourseRepository;
import TSM.demo.repository.MatchingRepository;
import TSM.demo.repository.PlaceRepository;
import TSM.demo.repository.UserRepository;
import TSM.demo.repository.query.MatchingQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchingService {

    private final MatchingRepository matchingRepository;
    private final UserRepository userRepository;

    public List<MatchingQueryDto> findAllByVolunteerId(int id){
        return matchingRepository.findAllInfoByVolunteerId(id);
    }
    @Transactional(readOnly = false)
    public void addMatchingByVolunteer(int sickId, int requestType, int requestId, Timestamp startTime, Timestamp endTime){
        User findUser = userRepository.findOne(sickId);

        List<User> Volunteers = userRepository.findByIsVolunteer(1);

        for (User volunteer : Volunteers) {
            UserHealth userHealth =findUser.getUserHealth().copyUserHealth();
            matchingRepository.addMatchingByVolunteer(sickId,volunteer.getId(),requestType,requestId,startTime,endTime,userHealth);
        }
    }
    


}
