package hiber.dao;

import hiber.model.Car;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CarDaoImpl implements CarDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void add(Car car) {
        entityManager.persist(car);
    }

    @Override
    public List<Car> listCars() {
        return entityManager.createQuery("from Car", Car.class).getResultList();
    }
}
