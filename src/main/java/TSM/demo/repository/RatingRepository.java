package TSM.demo.repository;

import TSM.demo.domain.Rating;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RatingRepository {
    private final EntityManager em;

    public void save(Rating rating) {
        em.persist(rating);
    }

    public List<Rating> findRatingsByUserId(int userId) {
        return em.createQuery("select r from rating r where r.receiverId.id = :userId", Rating.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
