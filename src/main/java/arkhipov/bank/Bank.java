package arkhipov.bank;

import arkhipov.bank.models.Account;
import arkhipov.bank.models.Transaction;
import arkhipov.bank.models.User;
import arkhipov.bank.repositories.AccountRepository;
import arkhipov.bank.repositories.TransactionRepository;
import arkhipov.bank.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static arkhipov.bank.models.BaseEntity.START_SEQ;

@SpringBootApplication
public class Bank {
    private static final Logger log = LoggerFactory.getLogger(Bank.class);

    public static void main(String[] args) {
        SpringApplication.run(Bank.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository, AccountRepository accountRepository, TransactionRepository transactionRepository) {
        return (args) -> {

            for (User user : userRepository.findAll()) {
                log.info(user.toString());
            }

            for (Account account : accountRepository.getAll(START_SEQ + 1)) {
                log.info(account.toString());
            }

            for (Transaction transaction : transactionRepository.findAll()) {
                log.info(transaction.toString());
            }

            transactionRepository.execute(new Transaction(100003, 100008, 215, "To account 100008 from account 100003"));
            transactionRepository.execute(new Transaction(100008, null, 115, "Store"));
            transactionRepository.execute(new Transaction(null, 100003, 315, "Dividend"));

            for (Account account : accountRepository.getAll(START_SEQ + 1)) {
                log.info(account.toString());
            }

            String start = "2017-09-01";
            String end = "2017-09-10";

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

            System.out.println(format.parse(start));
            System.out.println(format.parse(end));

            for (Transaction transaction : transactionRepository.getBetween(format.parse(start), format.parse(end))) {
                log.info(transaction.toString());
            }
        };
    }
}
