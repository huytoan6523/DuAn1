/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.NguonGoc;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author Uy Tin
 */
public class NguonGocRepository {

    public List<NguonGoc> getAll() {

        Session session = HibernatUtil.getFACTORY().openSession();
        Query query = session.createQuery("From NguonGoc");
        List<NguonGoc> listOut = query.getResultList();

        session.close();
        return listOut;
    }
    
    public NguonGoc getOne(String ten) {
        NguonGoc ng = null;
        try ( Session s = HibernatUtil.getFACTORY().openSession()) {
            String hql = "from NguonGoc where QuocGia = :ten";
            TypedQuery<NguonGoc> query = s.createQuery(hql, NguonGoc.class);
            query.setParameter("ten", ten);
            ng = query.getSingleResult();
        }
        return ng;
    }

    public Boolean add(NguonGoc ng) {
        Transaction transaction;
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            transaction = session.beginTransaction();
            session.save(ng);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Boolean update(NguonGoc ng) {
        Transaction transaction;
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(ng);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Boolean delete(NguonGoc ng) {
        Transaction transaction = null;
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            transaction = session.beginTransaction();
            session.delete(ng);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
