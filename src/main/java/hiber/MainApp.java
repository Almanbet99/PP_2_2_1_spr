package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        List<User> users = Arrays.asList(
                new User("Mike", "Boxes", "mike@boxes.com"),
                new User("Roy", "Jones", "roy@boxes.ru")
        );

        List<Car> cars = Arrays.asList(
                new Car("BMW", 7),
                new Car("Camry", 70)
        );

        users.forEach(userService::add);
        cars.forEach(carService::add);

        List<User> savedUsers = userService.listUsers();
        List<Car> savedCars = carService.listCars();

        savedUsers.get(0).setCar(savedCars.get(0));
        savedUsers.get(1).setCar(savedCars.get(1));

        savedUsers.forEach(userService::update);

        context.close();
    }
}
