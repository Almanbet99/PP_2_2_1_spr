package hiber;

import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(hiber.config.AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        if (userService.listUsers().isEmpty()) {
            Car car1 = new Car("BMW", 7);
            Car car2 = new Car("Camry", 70);

            userService.add(new User("Mike", "Boxes", "mike@boxes.com", car1));
            userService.add(new User("Roy", "Jones", "roy@boxes.ru", car2));
        }

        List<User> allUsers = userService.listUsers();
        System.out.println("Количество пользователей: " + allUsers.size());

        allUsers.forEach(user -> System.out.println(
                "User: " + user.getFirstName() + " " + user.getLastName()
                        + " | Email: " + user.getEmail()
                        + " | Car: " + user.getCar().getModel() + " " + user.getCar().getSeries()
        ));

        List<User> foundUsers = userService.findByCar("BMW", 7);
        System.out.println("Найдено владельцев BMW 7: " + foundUsers.size());

        foundUsers.forEach(user ->
                System.out.println("Найден владелец BMW 7: " + user.getFirstName() + " " + user.getLastName())
        );

        context.close();
    }
}
