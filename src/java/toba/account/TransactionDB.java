package toba.account;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import toba.account.Account.Type;
import toba.user.DBUtil;
import toba.user.User;

public class TransactionDB {
    
    public static void insert(Transaction transaction) {
    EntityManager em = DBUtil.getEmFactory().createEntityManager();
    EntityTransaction trans = em.getTransaction();
    trans.begin();        
    try {
        em.persist(transaction);
        trans.commit();
    } catch (Exception e) {
        System.out.println(e);
        trans.rollback();
    } finally {
        em.close();
    }
}
    
}
