package TSM.demo.repository;

import TSM.demo.domain.Matching;
import TSM.demo.domain.Rating;
import TSM.demo.domain.State;
import TSM.demo.domain.UserHealth;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import java.sql.Timestamp;
import java.util.*;
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

    public List<Matching> findAllByGroupId(int groupId){
        return em.createQuery("select i from matching i" +
                        " where i.groupId= :groupId")
                .setParameter("groupId",groupId)
                .getResultList();
    }

    public List<Matching> findAll(){
        return em.createQuery("select m from matching m " +
                " join fetch m.userHealth h"
                ,Matching.class)
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
        System.out.println("size: "+matchings.size());
        return matchings;
    }

    public List<Matching> findAllInfoWithCourseByUnwellId(int sickId){
        return em.createQuery("select m from matching m " +
                        " join fetch m.userHealth h" +
                        " where m.sickId=:sickId and" +
                        " m.requestType=1",Matching.class)
                .setParameter("sickId",sickId)
                .getResultList();
    }
    public List<Matching> findAllInfoWithPlaceByUnwellId(int sickId,int requestType){
        return em.createQuery("select m from matching m " +
                        " join fetch m.userHealth h" +
                        " where m.sickId=:sickId and" +
                        " m.requestType=:requestType",Matching.class)
                .setParameter("sickId",sickId)
                .setParameter("requestType",requestType)
                .getResultList();
    }
    public List<Matching>findAllInfoByUnwellId(int sickId){
        List<Matching>matchings=new ArrayList<>();
        matchings.addAll(findAllInfoWithCourseByUnwellId(sickId));
        for(int i=2;i<=5;i++){
            matchings.addAll(findAllInfoWithPlaceByUnwellId(sickId,i));
        }
        System.out.println("size: "+matchings.size());
        return matchings;
    }
    public void addMatchingByVolunteer(int volunteerId,Matching matching){

        Matching newMatching =new Matching(matching.getRequestId(), State.WAIT,matching.getSickId(),volunteerId,matching.getStartTime(),matching.getEndTime(),matching.getRequestType(),matching.getRequestId(),matching.getUserHealth());
        em.persist(newMatching);
    }

    public int getMaxGroupId() {
        try {
            return em.createQuery("select MAX(m.groupId) from matching m", Integer.class).getSingleResult();
        }catch (NullPointerException e) {
            return 0;
        }
    }

    public void addMatchingRowByUnwell(int sickId, int requestType, Timestamp startTime, Timestamp endTime, int requestId, UserHealth userHealth) {
        int maxGroupId = getMaxGroupId();
        Matching matching = new Matching(++maxGroupId, null, sickId, 0, startTime, endTime, requestType, requestId, userHealth.copyUserHealth());
        this.save(matching);
    }

    public void rateVolunteerByMatchingId(int matchingId, int rating) {
        Matching matching = findOneById(matchingId);
        matching.setRating(rating);
    }

    public List<Matching> findMatchingsByVolunteerId(int userId) {
        return em.createQuery("select m from matching m where m.volunteerId = :userId", Matching.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public List<Matching> isMatchingRatedByMatchingId(int matchingId) {
        return em.createQuery("select m from matching m where m.id = :matchingId", Matching.class)
                .setParameter("matchingId", matchingId)
                .getResultList();
    }
}
