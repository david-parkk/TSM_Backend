package TSM.demo.service;

import TSM.demo.domain.Matching;
import TSM.demo.domain.User;
import TSM.demo.domain.UserHealth;
import TSM.demo.repository.MatchingRepository;
import TSM.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MatchingService {

    private final MatchingRepository matchingRepository;


    public List<Matching> findAllByVolunteerId(int id){
        List<Matching> matchings = matchingRepository.findAllInfoByVolunteerId(id);
        return matchings;
    }
    public List<Matching> findAllByGroupId(int groupId){
        return matchingRepository.findAllByGroupId(groupId);
    }

    public void addMatchingByUnwell(int volunteerId, int matchingGroupId){
        List<Matching> matchingList= matchingRepository.findAllByGroupId(matchingGroupId);

        Matching matching=matchingList.get(0);
        if(matching.getState()== null) {
            matching.setWait();
            matching.setVolunteerId(volunteerId);
        }
        else{

            matchingRepository.addMatchingByVolunteer(volunteerId,matching);
        }


        

    }

    public void selectMatchingByVolunteer(int volunteerId,int groupId){
        List<Matching> findMatchings = matchingRepository.findAllByGroupId(groupId);
        for (Matching findMatching : findMatchings) {
            if(findMatching.getVolunteerId()==volunteerId)
                findMatching.setSuccess();
            else
                findMatching.setFail();
        }
    }

    public void requestHelpByUnwell(int sickId, int requestType, Timestamp startTime, Timestamp endTime, int requestId) {
        matchingRepository.addMatchingRowByUnwell(sickId, requestType, startTime, endTime, requestId);
    }
}
