/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.SPCT;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author tungs
 */
public class SPCTRepository {
    
    public List<SPCT> getAll(){
        List<SPCT> lst = new ArrayList<>();
        try(Session s = HibernatUtil.getFACTORY().openSession()) {
            String hql = "from SPCT";
            TypedQuery<SPCT> query = s.createQuery(hql, SPCT.class);
            lst = query.getResultList();
        }
        return lst;
    }
    
    public SPCT getOne(String ten){
        SPCT spct = null;
        try ( Session s = HibernatUtil.getFACTORY().openSession()) {
            String hql = "from SPCT where ten = :ten";
            TypedQuery<SPCT> query = s.createQuery(hql, SPCT.class);
            query.setParameter("ten", ten);
            spct = query.getSingleResult();
        }
        return spct;
    }
    
    public boolean addOrSave(SPCT x) {
        boolean check = false;
        try ( Session s = HibernatUtil.getFACTORY().openSession()) {
            Transaction tran = s.beginTransaction();
            try {
                s.saveOrUpdate(x);
                tran.commit();
                check = true;
            } catch (Exception e) {
                e.printStackTrace();
                tran.rollback();
            }
        }
        return check;
    }
    
    public boolean delete(SPCT x) {
        boolean check = false;
        Transaction tran = null;
        try ( Session s = HibernatUtil.getFACTORY().openSession()) {
            tran = s.beginTransaction();
            s.delete(x);
            tran.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
            check = false;
            tran.rollback();
        }
        return check;
    }
}
