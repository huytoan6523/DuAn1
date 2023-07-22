/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.DonViTinh;
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
public class DonViTinhRepository {

    public List<DonViTinh> getAll() {

        Session session = HibernatUtil.getFACTORY().openSession();
        Query query = session.createQuery("From DonViTinh");
        List<DonViTinh> listOut = query.getResultList();

        session.close();
        return listOut;
    }
    
    public DonViTinh getOne(String ten) {
        DonViTinh dvt = null;
        try ( Session s = HibernatUtil.getFACTORY().openSession()) {
            String hql = "from DonViTinh where DonViTinh = :ten";
            TypedQuery<DonViTinh> query = s.createQuery(hql, DonViTinh.class);
            query.setParameter("ten", ten);
            dvt = query.getSingleResult();
        }
        return dvt;
    }

    public Boolean add(DonViTinh dvt) {
        Transaction transaction;
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            transaction = session.beginTransaction();
            session.save(dvt);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Boolean update(DonViTinh dvt) {
        Transaction transaction;
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(dvt);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Boolean delete(DonViTinh dvt) {
        Transaction transaction = null;
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            transaction = session.beginTransaction();
            session.delete(dvt);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
