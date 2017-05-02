package toba.account;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import toba.account.Account.Type;
import toba.user.DBUtil;
import toba.user.User;

public class AccountDB {
    
    public static void insert(Account account) {
    EntityManager em = DBUtil.getEmFactory().createEntityManager();
    EntityTransaction trans = em.getTransaction();
    trans.begin();        
    try {
        em.persist(account);
        trans.commit();
    } catch (Exception e) {
        System.out.println(e);
        trans.rollback();
    } finally {
        em.close();
    }
}
    
    
    public static void update(Account account) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();       
        try {
            em.merge(account);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static Account selectChecking(String userName, Type checking) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM Account u " +
                "WHERE u.user.userName = :userName AND u.accountType = :checking";
        TypedQuery<Account> q = em.createQuery(qString, Account.class);
        q.setParameter("userName", userName);
        q.setParameter("checking", checking);
        try {
            Account user = q.getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static boolean checkingExists(String userName, Type checking) {
        checking = Type.CHECKING;
        Account u = selectChecking(userName, checking);   
        return u != null;
    }
    
    
    public static Account selectSavings(String userName, Type savings) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM Account u " +
                "WHERE u.user.userName = :userName AND u.accountType = :savings";
        TypedQuery<Account> q = em.createQuery(qString, Account.class);
        q.setParameter("userName", userName);
        q.setParameter("savings", savings);
        try {
            Account user = q.getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static boolean savingsExists(String userName, Type savings) {
        savings = Type.SAVINGS;
        Account u = selectSavings(userName, savings);   
        return u != null;
    }
    
}
