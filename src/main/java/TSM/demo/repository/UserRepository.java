package TSM.demo.repository;

import TSM.demo.domain.User;
import TSM.demo.domain.UserHealth;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;
    public void save(User user) {em.persist(user);}

    public User findOne(int id) {
        return em.find(User.class, id);
    }

    public List<User> findAll() {
        return em.createQuery("select u from user u", User.class)
                .getResultList();
    }

    public User findByName(String name) {
        return em.createQuery("select u from user u where u.name = :name", User.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public User findByEmail(String email) {
        try {
            return em.createQuery("select u from user u where u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        }catch (NoResultException e) {
            return null;
        }
    }
    public List<User> findByIsVolunteer(int isVolunteer){
        return em.createQuery("select u from user u" +
                        " where isVolunteer = :isVolunteer", User.class)
                .setParameter("isVolunteer",isVolunteer)
                .getResultList();
    }

    public boolean findUserByOauthId(String oauthId) {
        try {
            User user = em.createQuery("select u from user u where u.oauthId = :oauthId", User.class)
                    .setParameter("oauthId", oauthId)
                    .getSingleResult();
        }catch(NoResultException e) {
            return false;
        }
        return true;
    }
}
