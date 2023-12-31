/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.Hoadon;
import domainModels.KhachHang;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author admin
 */
public class QRHoaDonRepository {
    
     public List<Hoadon> getList() {
        try {
            Session session = HibernatUtil.getFACTORY().openSession(); //Ket noi DB thuc hien hien truy van
            Query q = session.createQuery("FROM HoaDon where TrangThai >= 2 order by convert(int,Ma) desc"); //Tao cau truy van lay du lieu tu bang dong go
            List<Hoadon> list = q.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }
     public static void main(String[] args) {
        QRHoaDonRepository hd = new QRHoaDonRepository();
        List<Hoadon> list = hd.getList();
         for (Hoadon hoaDon : list) {
             System.out.println(hoaDon);
         }
    }
    
}
