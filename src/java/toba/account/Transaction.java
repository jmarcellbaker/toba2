package toba.account;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import toba.account.Account.Type;

/**
 *
 * @author jmarc
 */
@Entity
public class Transaction implements Serializable {
    
    @ManyToOne
    private Account account;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionID;
    
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date transactionDate;
    
    private double amountTransfered;
    
    private double newBalance;
    
    @Column(name = "ACCOUNT_TYPE")
    @Enumerated(EnumType.STRING)
    public Type accountType = null;
    
    public Transaction() {
        
    }
    
    
    public Transaction(Account account, java.util.Date transactionDate,
                       double amountTransfered, double newBalance, Type accountType) {
    
        this.account = account;
        this.transactionDate = transactionDate;
        this.amountTransfered = amountTransfered;
        this.newBalance = newBalance;
        this.accountType = accountType;
    }
    
    public Account getAccount() {
        return account;
    }
    
      
}
