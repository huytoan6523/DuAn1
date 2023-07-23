/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;


import domainModels.KhuyenMai;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

/**
 *
 * @author PC
 */
public class KhuyenMaiRepository {
    List<KhuyenMai> khuyenMais = new ArrayList<>();
    Session hSession = HibernateUtil.getFACTORY().openSession();
    
    public void insert(KhuyenMai khuyenMai)
    {
        Transaction transaction = this.hSession.getTransaction();
        try {
            transaction.begin();
            this.hSession.persist(khuyenMai);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }
    
    public int getMaMax(){
        Session hSession = HibernateUtil.getFACTORY().openSession();
        String soMaLonNhat = null;
        Query q = hSession.createQuery(" select A.Ma From KhuyenMai A Where TrangThai = 1 ");
        List<String> mas = q.getResultList(); //Lay list String 
        if (mas.isEmpty()) {
            return 0;
        } else {
            List<Integer> c = new ArrayList<>(); //Convert list String sang List Integer
            for (String a : mas) {
                c.add(Integer.parseInt(a));
            }
            System.out.println(c + "\n");

            int max = c.get(0); //Từ list int lấy ra số lớn nhất
            for (int j = 0; j < c.size(); j++) {
                if (c.get(j).compareTo(max) > 0) {
                    max = c.get(j);
                }
            }

            return max;
        }
    }
    

    public void update(KhuyenMai khuyenMai)
    {
        Transaction transaction = this.hSession.getTransaction();
        try {
            transaction.begin();
            this.hSession.merge(khuyenMai);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public void delete(KhuyenMai khuyenMai)
    {
        Transaction transaction = this.hSession.getTransaction();
        try {
            transaction.begin();
            this.hSession.delete(khuyenMai);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public KhuyenMai findById(String id)
    {
        return this.hSession.find(KhuyenMai.class, id);
    }

    public List<KhuyenMai> findAll()
    {
        String hql = "SELECT obj FROM KhuyenMai obj";
        TypedQuery<KhuyenMai> query = this.hSession.createQuery(hql, KhuyenMai.class);
        return query.getResultList();
    }

    public KhuyenMai findByMa(String ma)
    {
        String hql = "SELECT obj FROM KhuyenMai obj WHERE obj.Ma = ?1";
        TypedQuery<KhuyenMai> query = this.hSession.createQuery(hql, KhuyenMai.class);
        query.setParameter(1, ma);
        return query.getSingleResult();
    }
    
    
}
