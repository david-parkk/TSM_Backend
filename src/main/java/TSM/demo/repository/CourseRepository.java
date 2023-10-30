package TSM.demo.repository;


import TSM.demo.domain.place.Course;
import TSM.demo.domain.place.TravelPlace;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CourseRepository {

    private final EntityManager em;

    public void save(Course course){
        em.persist(course);
    }
    public Course findOneById(int id){
        return em.find(Course.class,id);
    }
    public List<Course> findAll(){
        return em.createQuery("select i from course i", Course.class)
                .getResultList();
    }

}
