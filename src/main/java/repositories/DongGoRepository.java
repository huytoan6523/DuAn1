/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.DongGo;
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
public class DongGoRepository {

    public List<DongGo> getAll() {

        Session session = HibernatUtil.getFACTORY().openSession();
        Query query = session.createQuery("From DongGo");
        List<DongGo> listOut = query.getResultList();

        session.close();
        return listOut;
    }
    
    public DongGo getOne(String ten) {
        DongGo dongGo = null;
        try ( Session s = HibernatUtil.getFACTORY().openSession()) {
            String hql = "from DongGo where TenLoaiGo = :ten";
            TypedQuery<DongGo> query = s.createQuery(hql, DongGo.class);
            query.setParameter("ten", ten);
            dongGo = query.getSingleResult();
        }
        return dongGo;
    }

    public Boolean add(DongGo dongGo) {
        Transaction transaction = null;
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            transaction = session.beginTransaction();
            session.save(dongGo);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Boolean update(DongGo dongGo) {
        Transaction transaction = null;
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(dongGo);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Boolean delete(DongGo dongGo) {
        Transaction transaction = null;
        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            transaction = session.beginTransaction();
            session.delete(dongGo);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
