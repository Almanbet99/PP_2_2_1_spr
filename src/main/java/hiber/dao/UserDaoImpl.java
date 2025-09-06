package hiber.dao;

import hiber.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void add(User user) {
        User existingUser = entityManager.createQuery(
                        "SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", user.getCar())
                .getResultStream()
                .findFirst()
                .orElse(null);

        if (existingUser == null) {
            entityManager.persist(user);
        } else {
            existingUser.setCar(user.getCar());
            existingUser.setCar(user.getClass());
            existingUser.setCar(user.getCar()); // если нужно обновлять машину
            entityManager.merge(existingUser);
        }
    }

    @Override
    @Transactional
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public List<User> listUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }
}

