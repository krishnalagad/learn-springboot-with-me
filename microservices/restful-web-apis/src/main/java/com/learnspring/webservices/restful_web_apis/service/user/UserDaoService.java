package com.learnspring.webservices.restful_web_apis.service.user;

import com.learnspring.webservices.restful_web_apis.entity.user.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    static int userCount = 0;

    static {
        users.add(new User(++userCount, "John Doe", LocalDate.now().minusYears(45)));
        users.add(new User(++userCount, "Krish Lagad", LocalDate.now().minusYears(24)));
        users.add(new User(++userCount, "John Cena", LocalDate.now().minusYears(60)));
        users.add(new User(++userCount, "Liam Hemsworth", LocalDate.now().minusYears(34)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(Integer id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public void deleteById(Integer id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }

    public User save(User user) {
        user.setId(++userCount);
        users.add(user);
        return user;
    }
}