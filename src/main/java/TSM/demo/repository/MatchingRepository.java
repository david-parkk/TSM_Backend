package TSM.demo.repository;

import TSM.demo.domain.Matching;
import TSM.demo.domain.State;
import TSM.demo.domain.UserHealth;
import TSM.demo.domain.place.Course;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MatchingRepository {

    private final EntityManager em;

    public void save(Matching matching){
        em.persist(matching);
    }

    public Matching findOneById(int id){
        return em.find(Matching.class,id);
    }

    public List<Matching> findAll(){
        return em.createQuery("select i from matching i", Matching.class)
                .getResultList();
    }

    public List<Matching> findAllByVolunteerId(int volunteerId){
        return em.createQuery("select i from matching i where i.volunteerId= :volunteer_id", Matching.class)
                .setParameter("volunteer_id",volunteerId)
                .getResultList();
    }
    public List<Matching>findAllByRequestId(int requestId){
        return em.createQuery("select i from matching i" +
                " where i.requestId= :requestId")
                .setParameter("requestId",requestId)
                .getResultList();
    }

    public List<Matching> findAllBySickId(int sickId){
        return em.createQuery("select i from matching i where i.sickId= :sick_id", Matching.class)
                .setParameter("sick_id",sickId)
                .getResultList();
    }
    public List<Matching> findAllInfoWithCourseByVolunteerId(int volunteerId){
        return em.createQuery("select m from matching m " +
                " join fetch m.userHealth h" +
                " where m.volunteerId=:volunteerId and" +
                " m.requestType=1",Matching.class)
                .setParameter("volunteerId",volunteerId)
                .getResultList();
    }
    public List<Matching> findAllInfoWithPlaceByVolunteerId(int volunteerId,int requestType){
        return em.createQuery("select m from matching m " +
                        " join fetch m.userHealth h" +
                        " where m.volunteerId=:volunteerId and" +
                        " m.requestType=:requestType",Matching.class)
                .setParameter("volunteerId",volunteerId)
                .setParameter("requestType",requestType)
                .getResultList();
    }

    public List<Matching> findAllInfoByVolunteerId(int volunteerId){

        List<Matching>matchings=new ArrayList<>();
        matchings.addAll(findAllInfoWithCourseByVolunteerId(volunteerId));
        for(int i=2;i<=5;i++){
            matchings.addAll(findAllInfoWithPlaceByVolunteerId(volunteerId,i));
        }
        return matchings;
    }

    public void addMatchingByVolunteer(int sickId, int volunteerId, int requestType, int requestId, Timestamp startTime, Timestamp endTime, UserHealth userHealth){

        Matching matching =new Matching(requestId, State.WAIT,sickId,volunteerId,startTime,endTime,requestType,requestId,userHealth);
        save(matching);
    }

    public int getMaxGroupId() {
        try {
            return em.createQuery("select MAX(m.groupId) from matching m", Integer.class).getSingleResult();
        }catch (NullPointerException e) {
            return 0;
        }
    }

    public void addMatchingRowByUnwell(int sickId, int requestType, Timestamp startTime, Timestamp endTime, int requestId) {
        int maxGroupId = getMaxGroupId();
        Matching matching = new Matching(++maxGroupId, null, sickId, 0, startTime, endTime, requestType, requestId);
        this.save(matching);
    }

}
