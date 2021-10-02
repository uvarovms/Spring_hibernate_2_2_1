package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");

        Car car1 = new Car("Audi", 111);
        Car car2 = new Car("Mazda", 222);
        Car car3 = new Car("Lexus", 333);

        user1.setCar(car1);
        user2.setCar(car2);
        user3.setCar(car3);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car model: " + user.getCar().getModel() + ", " + user.getCar().getSeries() + " series");
            System.out.println();
        }
        System.out.println("Driver car " +
                userService.getUserByCar("Lexus", 333).getCar().getModel() + " " +
                userService.getUserByCar("Lexus", 333).getCar().getSeries() + " --> " +
                userService.getUserByCar("Lexus", 333).getFirstName() + " " +
                userService.getUserByCar("Lexus", 333).getLastName());
        context.close();
    }
}
