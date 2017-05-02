package toba.account;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import toba.user.User;

@Entity
//@Table(name = "Account")
public class Account implements Serializable {
    
    @ManyToOne
    private User user;
    
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long accountNumber;
   public enum Type {CHECKING, SAVINGS};
   @Column(name = "ACCOUNT_TYPE")
   @Enumerated(EnumType.STRING)
   public Type accountType = null;
   public double balance;
     
   
   
   public Account () {
       
   }
   
   public Account(User user, double balance, Type accountType) {
       
       this.accountType = accountType;
       this.balance = balance;
       this.user = user;
   }
   
   
   public double getBalance() {
       return balance;
   }
   
   public void credit(double credit) {
       balance += credit;
   }
   
   public void debit(double debit) {
       balance -= debit;
   } 
    
   
} 
    

