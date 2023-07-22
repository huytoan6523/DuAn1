/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.NhaCungCap;
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
public class NhaCungCapRepository {

    public List<NhaCungCap> getAll() {

        Session session = HibernatUtil.getFACTORY().openSession();
        Query query = session.createQuery("From NhaCungCap");
        List<NhaCungCap> listOut = query.getResultList();

        session.close();
        return listOut;
    }
    
    public NhaCungCap getOne(String ten) {
        NhaCungCap ncc = null;
        try ( Session s = HibernatUtil.getFACTORY().openSession()) {
            String hql = "from NhaCungCap where TenNCC = :ten";
            TypedQuery<NhaCungCap> query = s.createQuery(hql, NhaCungCap.class);
            query.setParameter("ten", ten);
            ncc = query.getSingleResult();
        }
        return ncc;
    }

    public Boolean add(NhaCungCap ncc) {
        Transaction transaction;
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            transaction = session.beginTransaction();
            session.save(ncc);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Boolean update(NhaCungCap ncc) {
        Transaction transaction;
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(ncc);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Boolean delete(NhaCungCap ncc) {
        Transaction transaction;
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            transaction = session.beginTransaction();
            session.delete(ncc);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
