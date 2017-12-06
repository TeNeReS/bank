package arkhipov.bank;

import arkhipov.bank.models.Account;
import arkhipov.bank.models.Transaction;
import arkhipov.bank.models.Person;
import arkhipov.bank.repositories.CrudAccountRepository;
import arkhipov.bank.repositories.TransactionRepository;
import arkhipov.bank.repositories.PersonRepository;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.text.ParseException;

import static arkhipov.bank.models.BaseEntity.START_SEQ;

public class Bank {
    public static void main(String[] args) throws ParseException {

//        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
//        context.load("classpath:spring.xml"); //move from src.main.java to src.main.resources
//        context.refresh();
//
//        PersonRepository personRepository = context.getBean(PersonRepository.class);
//        TransactionRepository transactionRepository = context.getBean(TransactionRepository.class);
//        CrudAccountRepository accountRepository = context.getBean(CrudAccountRepository.class);

//        for (Person person : personRepository.findAll()) {
//            System.out.println(person.toString());
//        }
//
//        for (Account account : personRepository.getWithAccounts(100001).getAccountList()) {
//            System.out.println(account);
//        }
//
//        transactionRepository.execute(new Transaction(215, "To account 100008 from account 100003"), 100003, 100008);
//        transactionRepository.execute(new Transaction(115, "Store"), 100008, null );
//        transactionRepository.execute(new Transaction(315, "Dividend"), null, 100003);
//        transactionRepository.execute(new Transaction(315, null), null, 100003);
//
//        for (Account account : accountRepository.getAll(START_SEQ + 1)) {
//            System.out.println(account);
//        }
    }
}
