package arkhipov.bank;

import arkhipov.bank.models.User;
import arkhipov.bank.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Bank {
    private static final Logger log = LoggerFactory.getLogger(Bank.class);

    public static void main(String[] args) {
//        System.out.println("Hello");
//        UserRepository userRepository = new UserRepositoryImpl();
//        userRepository.save(new User(12, "qwer", "wqrr", 23));
        SpringApplication.run(Bank.class, args);

    }

    @Bean
    public CommandLineRunner demo(UserRepository repository) {
        return (args) -> {
            repository.save(new User(null, "User1", "user1@mail.ru", 23));
            repository.save(new User(null, "User2", "user2@mail.ru", 24));
            repository.save(new User(null, "User3", "user3@mail.ru", 25));

            for (User user : repository.findAll()) {
                log.info(user.toString());
            }
        };
    }
}
