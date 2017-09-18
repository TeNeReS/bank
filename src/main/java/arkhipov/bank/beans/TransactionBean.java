package arkhipov.bank.beans;

import arkhipov.bank.models.Transaction;
import arkhipov.bank.repositories.TransactionRepository;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Component
@ManagedBean
@ViewScoped
public class TransactionBean implements Serializable {
    private TransactionRepository transactionRepository;

    private Date startDate;

    private Date endDate;

    private List<Transaction> transactionList;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Transaction> getTransactionList() {
        if (transactionList == null) return getAll();
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public TransactionBean(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAll() {
        return transactionList = transactionRepository.getAll();
    }

    public void filter() {
        if (startDate == null && endDate == null ) transactionList = transactionRepository.getAll();
        else {
            transactionList = transactionRepository.getBetween(getStartDate(), getEndDate());
            clearForm();
        }
    }

    public void clearForm(){
        setStartDate(null);
        setEndDate(null);
    }
}
