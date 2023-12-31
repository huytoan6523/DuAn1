package repositories;


import domainModels.HoaDon1;
import domainModels.KhachHangHoaDon;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;


public class KhachHangRepository {
    List<KhachHangHoaDon> khachHangList = new ArrayList<>();
    Session hSession = HibernateUtil.getFACTORY().openSession();
    
    public void insert(KhachHangHoaDon kh)
    {
        Transaction transaction = this.hSession.getTransaction();
        try {
            transaction.begin();
            this.hSession.persist(kh);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }
    
    public int getMaMax(){
        Session hSession = HibernateUtil.getFACTORY().openSession();
        String soMaLonNhat = null;
        Query q = hSession.createQuery(" select A.Ma From DonViTinh A Where TrangThai = 1 ");
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
    

    public void update(KhachHangHoaDon kh)
    {
        Transaction transaction = this.hSession.getTransaction();
        try {
            transaction.begin();
            this.hSession.merge(kh);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public void delete(KhachHangHoaDon kh)
    {
        Transaction transaction = this.hSession.getTransaction();
        try {
            transaction.begin();
            this.hSession.delete(kh);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public KhachHangHoaDon findById(String id)
    {
        return this.hSession.find(KhachHangHoaDon.class, id);
    }

    public List<KhachHangHoaDon> findAll()
    {
        String hql = "SELECT obj FROM KhachHang obj";
        TypedQuery<KhachHangHoaDon> query = this.hSession.createQuery(hql, KhachHangHoaDon.class);
        return query.getResultList();
    }

    public KhachHangHoaDon findByMa(String ma)
    {
        String hql = "SELECT obj FROM KhachHang obj WHERE obj.Ma = ?1";
        TypedQuery<KhachHangHoaDon> query = this.hSession.createQuery(hql, KhachHangHoaDon.class);
        query.setParameter(1, ma);
        return query.getSingleResult();
    }
    
    public List<HoaDon1> getHoaDon(KhachHangHoaDon khachHang)
    {
        String hql = "SELECT obj FROM HoaDon obj WHERE obj.khachHang = ?1";
        TypedQuery<HoaDon1> query = this.hSession.createQuery(hql, HoaDon1.class);
        query.setParameter(1, khachHang);
        return query.getResultList();
    }
    
}
