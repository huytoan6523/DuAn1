/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.LoaiSP;
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
public class LoaiSanPhamRepository {
    public List<LoaiSP> getAll() {

        Session session = HibernatUtil.getFACTORY().openSession();
        Query query = session.createQuery("From LoaiSP");
        List<LoaiSP> listOut = query.getResultList();

        session.close();
        return listOut;
    }
    
    public LoaiSP getOne(String ten) {
        LoaiSP loai = null;
        try ( Session s = HibernatUtil.getFACTORY().openSession()) {
            String hql = "from LoaiSP where TenDongSP = :ten";
            TypedQuery<LoaiSP> query = s.createQuery(hql, LoaiSP.class);
            query.setParameter("ten", ten);
            loai = query.getSingleResult();
        }
        return loai;
    }

    public Boolean add(LoaiSP lsp) {
        Transaction transaction;
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            transaction = session.beginTransaction();
            session.save(lsp);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Boolean update(LoaiSP lsp) {
        Transaction transaction;
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(lsp);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Boolean delete(LoaiSP lsp) {
        Transaction transaction;
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            transaction = session.beginTransaction();
            session.delete(lsp);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
