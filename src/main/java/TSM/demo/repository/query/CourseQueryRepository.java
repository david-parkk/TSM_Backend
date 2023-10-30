package TSM.demo.repository.query;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CourseQueryRepository {

    private final EntityManager em;

    public List<CourseQueryDto> findCourseQueryDto(){
        return em.createQuery("" +
                "select new " +
                "TSM.demo.repository.query.CourseQueryDto(c.id, c.name, c.region, c.description, c.url)" +
                "from course c " +
                "join c.restaurantList r", CourseQueryDto.class)
                .getResultList();

    }
}
