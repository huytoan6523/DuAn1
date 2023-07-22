/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.SanPham;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author ktkha
 */
public class SanPhamRepository {

    public List<SanPham> getAll() {

        Session session = HibernatUtil.getFACTORY().openSession();
        Query query = session.createQuery("From SanPham");
        List<SanPham> listOut = query.getResultList();

        session.close();
        return listOut;
    }
    
    public SanPham getOne(String ten) {
        SanPham sanPham = null;
        try ( Session s = HibernatUtil.getFACTORY().openSession()) {
            String hql = "from SanPham where Ten = :ten";
            TypedQuery<SanPham> query = s.createQuery(hql, SanPham.class);
            query.setParameter("ten", ten);
            sanPham = query.getSingleResult();
        }
        return sanPham;
    }

    public Boolean add(SanPham sp) {
        Transaction transaction;
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            transaction = session.beginTransaction();
            session.save(sp);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Boolean update(SanPham sp) {
        Transaction transaction;
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(sp);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Boolean delete(SanPham sp) {
        Transaction transaction = null;
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            transaction = session.beginTransaction();
            session.delete(sp);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
