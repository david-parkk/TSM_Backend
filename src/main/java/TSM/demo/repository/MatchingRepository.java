package TSM.demo.repository;

import TSM.demo.domain.Matching;
import TSM.demo.domain.State;
import TSM.demo.domain.UserHealth;
import TSM.demo.domain.place.Course;
import TSM.demo.repository.query.MatchingQueryDto;
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
    public List<MatchingQueryDto> findAllInfoWithCourseByVolunteerId(int volunteerId){
        List<MatchingQueryDto> matchingQueryDtos=em.createQuery("select new TSM.demo.repository.query.MatchingQueryDto(m.id,m.groupId,m.state,m.sickId,m.volunteerId, m.startTime, m.endTime, m.requestType, m.requestId,m.userHealth.id)" +
                        " from matching m " +
                        " join m.userHealth h" +
                        " where m.volunteerId= :volunteerId and" +
                        " m.requestType= 1", MatchingQueryDto.class)
                .setParameter("volunteerId",volunteerId)
                .getResultList();
        List<Integer>courseIds=matchingQueryDtos.stream()
                .map(o->o.getRequest_id())
                .collect(Collectors.toList());
        List<Course> courses=em.createQuery(
                        "select c from course c " +
                                " where c.id in :courseIds", Course.class)
                .setParameter("courseIds",courseIds)
                .getResultList();
        Map<Integer,Course> courseMap=courses.stream()
                .collect(Collectors.toMap(Course::getId, course -> course));
        matchingQueryDtos.forEach(o->o.setCourse(courseMap.get(o.getRequest_id())));
        return matchingQueryDtos;
    }
    public List<MatchingQueryDto> findAllInfoWithRestaurantByVolunteerId(int volunteerId){
        List<MatchingQueryDto> matchingQueryDtos=em.createQuery("select new TSM.demo.repository.query.MatchingQueryDto(m.id,m.groupId,m.state,m.sickId,m.volunteerId, m.startTime, m.endTime, m.requestType, m.requestId)" +
                        " from matching m " +
                        " where m.volunteerId= :volunteerId and" +
                        " m.requestType= 2", MatchingQueryDto.class)
                .setParameter("volunteerId",volunteerId)
                .getResultList();
        List<Integer>courseIds=matchingQueryDtos.stream()
                .map(o->o.getRequest_id())
                .collect(Collectors.toList());
        List<Course> courses=em.createQuery(
                        "select c from course c " +
                                " where c.id in :courseIds", Course.class)
                .setParameter("courseIds",courseIds)
                .getResultList();
        Map<Integer,Course> courseMap=courses.stream()
                .collect(Collectors.toMap(Course::getId, course -> course));
        matchingQueryDtos.forEach(o->o.setCourse(courseMap.get(o.getRequest_id())));
        return matchingQueryDtos;
    }
    public List<MatchingQueryDto> findAllInfoWithRoomByVolunteerId(int volunteerId){
        List<MatchingQueryDto> matchingQueryDtos=em.createQuery("select new TSM.demo.repository.query.MatchingQueryDto(m.id,m.groupId,m.state,m.sickId,m.volunteerId, m.startTime, m.endTime, m.requestType, m.requestId)" +
                        " from matching m " +
                        " where m.volunteerId= :volunteerId and" +
                        " m.requestType= 3", MatchingQueryDto.class)
                .setParameter("volunteerId",volunteerId)
                .getResultList();
        List<Integer>courseIds=matchingQueryDtos.stream()
                .map(o->o.getRequest_id())
                .collect(Collectors.toList());
        List<Course> courses=em.createQuery(
                        "select c from course c " +
                                " where c.id in :courseIds", Course.class)
                .setParameter("courseIds",courseIds)
                .getResultList();
        Map<Integer,Course> courseMap=courses.stream()
                .collect(Collectors.toMap(Course::getId, course -> course));
        matchingQueryDtos.forEach(o->o.setCourse(courseMap.get(o.getRequest_id())));
        return matchingQueryDtos;
    }
    public List<MatchingQueryDto> findAllInfoWithTransportByVolunteerId(int volunteerId){
        List<MatchingQueryDto> matchingQueryDtos=em.createQuery("select new TSM.demo.repository.query.MatchingQueryDto(m.id,m.groupId,m.state,m.sickId,m.volunteerId, m.startTime, m.endTime, m.requestType, m.requestId)" +
                        " from matching m " +
                        " where m.volunteerId= :volunteerId and" +
                        " m.requestType= 4", MatchingQueryDto.class)
                .setParameter("volunteerId",volunteerId)
                .getResultList();
        List<Integer>courseIds=matchingQueryDtos.stream()
                .map(o->o.getRequest_id())
                .collect(Collectors.toList());
        List<Course> courses=em.createQuery(
                        "select c from course c " +
                                " where c.id in :courseIds", Course.class)
                .setParameter("courseIds",courseIds)
                .getResultList();
        Map<Integer,Course> courseMap=courses.stream()
                .collect(Collectors.toMap(Course::getId, course -> course));
        matchingQueryDtos.forEach(o->o.setCourse(courseMap.get(o.getRequest_id())));
        return matchingQueryDtos;
    }
    public List<MatchingQueryDto> findAllInfoWithTravelPlaceByVolunteerId(int volunteerId){
        List<MatchingQueryDto> matchingQueryDtos=em.createQuery("select new TSM.demo.repository.query.MatchingQueryDto(m.id,m.groupId,m.state,m.sickId,m.volunteerId, m.startTime, m.endTime, m.requestType, m.requestId)" +
                        " from matching m " +
                        " where m.volunteerId= :volunteerId and" +
                        " m.requestType= 5", MatchingQueryDto.class)
                .setParameter("volunteerId",volunteerId)
                .getResultList();
        List<Integer>courseIds=matchingQueryDtos.stream()
                .map(o->o.getRequest_id())
                .collect(Collectors.toList());
        List<Course> courses=em.createQuery(
                        "select c from course c " +
                                " where c.id in :courseIds", Course.class)
                .setParameter("courseIds",courseIds)
                .getResultList();
        Map<Integer,Course> courseMap=courses.stream()
                .collect(Collectors.toMap(Course::getId, course -> course));
        matchingQueryDtos.forEach(o->o.setCourse(courseMap.get(o.getRequest_id())));
        return matchingQueryDtos;
    }
    public List<MatchingQueryDto> findAllInfoByVolunteerId(int volunteerId){
        List<MatchingQueryDto>matchingQueryDtos=new ArrayList<>();
        matchingQueryDtos.addAll(findAllInfoWithCourseByVolunteerId(volunteerId));
        matchingQueryDtos.addAll(findAllInfoWithRestaurantByVolunteerId(volunteerId));
        matchingQueryDtos.addAll(findAllInfoWithRoomByVolunteerId(volunteerId));
        matchingQueryDtos.addAll(findAllInfoWithTransportByVolunteerId(volunteerId));
        matchingQueryDtos.addAll(findAllInfoWithTravelPlaceByVolunteerId(volunteerId));
        return matchingQueryDtos;
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