package TSM.demo.repository;


import TSM.demo.domain.UserHealth;
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

    public void save(Course course) {
        em.persist(course);
    }

    public Course findOneById(int id) {
        return em.find(Course.class, id);
    }

    public List<Course> findAll() {
        return em.createQuery("select c from course c", Course.class)
                .getResultList();
    }

    public Course findByName(String name) {
        return em.createQuery("select c from course c where c.name = :name", Course.class)
                .setParameter("name", name)
                .getSingleResult();
    }

//    public List<UserHealth> findUserHealth() {
//        return em.createQuery("select uh from user_health uh, course c where uh.health_id = c.course_id", UserHealth.class)
//                .getResultList();
//    }
}
