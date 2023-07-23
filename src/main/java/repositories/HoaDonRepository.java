/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.HoaDon;
import domainModels.HoaDonChiTiet;
import domainModels.KhachHang;
import domainModels.KhuyenMai;
import domainModels.NhanVien;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;


/**
 *
 * @author PhiLT
 */
public class HoaDonRepository {
    private Session s = HibernateUtil.getFACTORY().openSession();
    public List<HoaDon> getALHoaDon(){
        List<HoaDon> lst = new ArrayList<>();
        String hql = "From HoaDon order by ngayTao desc";
        lst = s.createQuery(hql, HoaDon.class).getResultList();
        return lst;
    }
    public List<HoaDon> getHoaDonByMa(String ma){
        List<HoaDon> lst = new ArrayList<>();
        String hql = "From HoaDon where ma like :ma";
        Query query = s.createQuery(hql, HoaDon.class);
        query.setParameter("ma", "%"+ma+"%");
        lst = query.getResultList();
        return lst;
    }
    public Boolean addOrUpdateHoaDon(HoaDon hoaDon){
        Boolean check = false;
        Transaction tran = s.beginTransaction();
        try{
            s.merge(hoaDon);
            tran.commit();
            check = true;
            tran.commit();
        }catch(Exception e){
            e.printStackTrace();
            tran.rollback();
        }
        return check;
    }
    public HoaDon getOneHDByMa(String ma){
        String hql = "from HoaDon where ma=:ma";
            TypedQuery<HoaDon> query = s.createQuery(hql);
            query.setParameter("ma", ma);
            HoaDon hoaDon = query.getSingleResult();
        return hoaDon;
    }
    
    public Boolean deleteHoaDon(String id){
        Boolean check = false;
        String hql = "delete from HoaDon hd where id=:id";
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
    public List<HoaDonChiTiet> getAllHDCT(String idHD){
        List<HoaDonChiTiet> lst = new ArrayList<>();
        String hql = "from HoaDonChiTiet hdct where hdct.hoaDon.id=:idHD";
        TypedQuery<HoaDonChiTiet> query = s.createQuery(hql, HoaDonChiTiet.class);
        query.setParameter("idHD", idHD);
        lst = query.getResultList();
        return lst;
    }
    
    public HoaDonChiTiet getOneHDCT(String idSP, String idHD){
       HoaDonChiTiet hdct = new HoaDonChiTiet();
        String hql = "From HoaDonChiTiet where  hdct.ChiTietDoGo.id=:idSP and hdct.hoaDon.id=:idHD";
        TypedQuery<HoaDonChiTiet> query = s.createQuery(hql, HoaDonChiTiet.class);
        query.setParameter("idSP", idSP);
        query.setParameter("idHD", idHD);
        hdct = query.getSingleResult();
        return hdct;
    }
    public Boolean addOrUpdateHDCT(HoaDonChiTiet hdct){
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
        String hql = "delete from HoaDonChiTiet hdct where  hdct.ChiTietDoGo.id=:idSP and hdct.hoaDon.id=:idHD and order by soLuong desc";
        Transaction tran = s.beginTransaction();
        try{
            TypedQuery<HoaDonChiTiet> query = s.createQuery(hql, HoaDonChiTiet.class);
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
     
     public List<KhachHang> getAllKhachHang(){
         List<KhachHang> lstKH = new ArrayList<>();
         String hql = "from KhachHang order by ma desc";
         TypedQuery<KhachHang> query = s.createQuery(hql, KhachHang.class);
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
