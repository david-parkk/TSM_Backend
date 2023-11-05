package TSM.demo.service;

import TSM.demo.domain.Matching;
import TSM.demo.domain.User;
import TSM.demo.domain.UserHealth;
import TSM.demo.repository.MatchingRepository;
import TSM.demo.repository.UserRepository;
import TSM.demo.repository.query.MatchingQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MatchingService {

    private final MatchingRepository matchingRepository;
    private final UserRepository userRepository;

    public List<MatchingQueryDto> findAllByVolunteerId(int id){
        return matchingRepository.findAllInfoByVolunteerId(id);
    }

    public void addMatchingByUnwell(int sickId, int requestType, int requestId, Timestamp startTime, Timestamp endTime){
        User findUser = userRepository.findOne(sickId);

        List<User> Volunteers = userRepository.findByIsVolunteer(1);

        for (User volunteer : Volunteers) {
            UserHealth userHealth =findUser.getUserHealth().copyUserHealth();
            matchingRepository.addMatchingByVolunteer(sickId,volunteer.getId(),requestType,requestId,startTime,endTime,userHealth);
        }
    }

    public void selectMatchingByVolunteer(int volunteerId,int requestId){
        List<Matching> findMatchings = matchingRepository.findAllByRequestId(requestId);
        for (Matching findMatching : findMatchings) {
            if(findMatching.getVolunteerId()==volunteerId)
                findMatching.setSuccess();
            else
                findMatching.setFail();
        }
    }

    public void requestHelpByUnwell(int sickId, int requestType, Timestamp startTime, Timestamp endTime, int requestId, UserHealth userHealth) {
        matchingRepository.addMatchingRowByUnwell(sickId, requestType, startTime, endTime, requestId, userHealth);
    }
}
