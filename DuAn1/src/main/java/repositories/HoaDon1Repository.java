/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.HoaDon1;
import domainModels.HoaDonChiTietHoaDon;
import domainModels.KhachHangHoaDon;
import domainModels.KhuyenMai;
import domainModels.NhanVien;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author PhiLT
 */
public class HoaDon1Repository {
    private Session s = HibernatUtil.getFACTORY().openSession();
    public List<HoaDon1> getALHoaDon(){
        List<HoaDon1> lst = new ArrayList<>();
        String hql = "From HoaDon1 order by ngayTao desc";
        lst = s.createQuery(hql, HoaDon1.class).getResultList();
        return lst;
    }
    public List<HoaDon1> getHoaDonByMa(String ma){
        List<HoaDon1> lst = new ArrayList<>();
        String hql = "From HoaDon1 where ma like :ma";
        Query query = s.createQuery(hql, HoaDon1.class);
        query.setParameter("ma", "%"+ma+"%");
        lst = query.getResultList();
        return lst;
    }
    public Boolean saveHoaDon(HoaDon1 hoaDon){
        Boolean check = false;
        Transaction tran = s.beginTransaction();
        try{
            s.persist(hoaDon);
            tran.commit();
            check = true;
        }catch(Exception e){
            e.printStackTrace();
            tran.rollback();
        }
        return check;
    }
    public Boolean updateHoaDon(HoaDon1 hoaDon){
        Boolean check = false;
        Transaction tran = s.beginTransaction();
        try{
            s.merge(hoaDon);
            tran.commit();
            check = true;
        }catch(Exception e){
            e.printStackTrace();
            tran.rollback();
        }
        return check;
    }
    public HoaDon1 getOneHDByMa(String ma){
        String hql = "from HoaDon1 where ma=:ma";
            TypedQuery<HoaDon1> query = s.createQuery(hql);
            query.setParameter("ma", ma);
            HoaDon1 hoaDon = query.getSingleResult();
        return hoaDon;
    }
    
    public Boolean deleteHoaDon(String id){
        Boolean check = false;
        String hql = "delete from HoaDon1 hd where id=:id";
        Transaction tran = s.beginTransaction();
        try{
            Query query = s.createQuery(hql);
            query.setParameter("id", id);
            query.executeUpdate();
            check = true;
            tran.commit();
        }catch(Exception e){
            e.printStackTrace();
            tran.rollback();
        }
        return check;
    }
    public List<HoaDonChiTietHoaDon> getAllHDCT(String idHD){
        List<HoaDonChiTietHoaDon> lst = new ArrayList<>();
        String hql = "from HoaDonChiTietHoaDon hdct where hdct.hoaDon.id=:idHD order by hdct.ChiTietDoGo.id desc";
        TypedQuery<HoaDonChiTietHoaDon> query = s.createQuery(hql, HoaDonChiTietHoaDon.class);
        query.setParameter("idHD", idHD);
        lst = query.getResultList();
        return lst;
    }
    
    public HoaDonChiTietHoaDon getOneHDCT(String idSP, String idHD){
       HoaDonChiTietHoaDon hdct = new HoaDonChiTietHoaDon();
        String hql = "From HoaDonChiTietHoaDon where  hdct.ChiTietDoGo.id=:idSP and hdct.hoaDon.id=:idHD";
        TypedQuery<HoaDonChiTietHoaDon> query = s.createQuery(hql, HoaDonChiTietHoaDon.class);
        query.setParameter("idSP", idSP);
        query.setParameter("idHD", idHD);
        hdct = query.getSingleResult();
        return hdct;
    }
    public Boolean addOrUpdateHDCT(HoaDonChiTietHoaDon hdct){
        Boolean check = false;
        Transaction tran = s.beginTransaction();
        try{
            s.saveOrUpdate(hdct);
            tran.commit();
            check = true;
        }catch(Exception e){
            e.printStackTrace();
            tran.rollback();
        }
        return check;
    }
    
     public Boolean deleteHDCT(String idSP, String idHD){
        Boolean check = false;
        String hql = "delete from HoaDonChiTietHoaDon hdct where  hdct.ChiTietDoGo.id=:idSP and hdct.hoaDon.id=:idHD";
        Transaction tran = s.beginTransaction();
        try{
            Query query = s.createQuery(hql);
            query.setParameter("idSP", idSP);
            query.setParameter("idHD", idHD);
            query.executeUpdate();
            check = true;
            tran.commit();
        }catch(Exception e){
            e.printStackTrace();
            tran.rollback();
        }
        return check;
    }
     
     public List<KhuyenMai> getAllKM(){
         List<KhuyenMai> lstKM = new ArrayList<>();
         String hql = "from KhuyenMai order by ngayKetThuc desc";
         TypedQuery<KhuyenMai> query = s.createQuery(hql, KhuyenMai.class);
         lstKM = query.getResultList();
         return lstKM;
     }
     
     public List<KhachHangHoaDon> getAllKhachHang(){
         List<KhachHangHoaDon> lstKH = new ArrayList<>();
         String hql = "from KhachHangHoaDon order by ma desc";
         TypedQuery<KhachHangHoaDon> query = s.createQuery(hql, KhachHangHoaDon.class);
         lstKH = query.getResultList();
         return lstKH;
     }
     
      public List<NhanVien> getAllNhanVien(){
         List<NhanVien> lstNV = new ArrayList<>();
         String hql = "from NhanVien order by ma desc";
         TypedQuery<NhanVien> query = s.createQuery(hql, NhanVien.class);
         lstNV = query.getResultList();
         return lstNV;
     }
}
