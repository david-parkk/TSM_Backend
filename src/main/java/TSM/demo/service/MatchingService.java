package TSM.demo.service;

import TSM.demo.domain.Matching;
import TSM.demo.domain.Rating;
import TSM.demo.domain.User;
import TSM.demo.domain.UserHealth;
import TSM.demo.repository.MatchingRepository;

import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.util.ArrayList;
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
    public List<Matching> findAllByUnwellId(int id){
        List<Matching> matchings=matchingRepository.findAllInfoByUnwellId(id);
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

    public void requestHelpByUnwell(User unwell, int requestType, Timestamp startTime, Timestamp endTime, int requestId, UserHealth userHealth) {
        matchingRepository.addMatchingRowByUnwell(unwell.getId(), requestType, startTime, endTime, requestId, userHealth);
    }
    public List<Matching>findAll(){
        return matchingRepository.findAll();
    }
    public List<Matching> findAllUnMatchingByVolunteer(){
        List<Matching> matchings = findAll();
        List<Matching> result=new ArrayList<>();
        for (Matching matching : matchings) {
            if(matching.getVolunteerId()!=0)
                continue;
            result.add(matching);


        }
        return result;
    }

    public void rateVolunteerByMatchingId(int matchingId, int rating) {
        matchingRepository.rateVolunteerByMatchingId(matchingId, rating);
    }

    public double getRatingAverage(int userId) {
        return calculateRatingAverage(matchingRepository.findMatchingsByVolunteerId(userId));
    }

    public int getRatingByMatchingId(int matchingId) {
        try {
            return matchingRepository.findOneById(matchingId).getRating();
        }catch (NullPointerException e) {
            return 0;
        }
    }

    public boolean isRated(int matchingId) {
        boolean b = !(matchingRepository.isMatchingRatedByMatchingId(matchingId).isEmpty());

        return b;
    }

    private double calculateRatingAverage(List<Matching> matchings) {
        int sum=0;
        int cnt=0;
        for (Matching matching : matchings) {
            try {
                sum += matching.getRating();
                cnt++;
            } catch (NullPointerException e) {
                continue;
            }
        }
        return (double) sum / cnt;
    }

}
