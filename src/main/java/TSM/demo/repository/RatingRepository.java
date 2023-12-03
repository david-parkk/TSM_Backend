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

    public List<Rating> findRatingsByReceiverId(int userId) {
        return em.createQuery("select r from rating r where r.receiverId.id = :userId", Rating.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public List<Rating> isReceiverIdRatedBySenderId(int senderId, int receiverId) {
        return em.createQuery("select r from rating r where r.senderId.id = :senderId and r.receiverId.id = :receiverId", Rating.class)
                .setParameter("senderId", senderId)
                .setParameter( "receiverId", receiverId)
                .getResultList();
    }
}
