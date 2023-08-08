/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import domainModels.HoaDon1;
import domainModels.HoaDonChiTietHoaDon;
import domainModels.KhachHangHoaDon;
import domainModels.KhuyenMai;
import domainModels.NhanVien;
import java.util.List;
import repositories.HoaDon1Repository;

/**
 *
 * @author PhiLT
 */
public class HoaDon1Service {
    private HoaDon1Repository hoaDonRepository = new HoaDon1Repository();
    
    public List<HoaDon1> getALHoaDon(){
        return hoaDonRepository.getALHoaDon();
    }
    public List<HoaDon1> getHoaDonByMa(String ma){
        return hoaDonRepository.getHoaDonByMa(ma);
    }
    public HoaDon1 getOneHDByMa(String ma){
        return hoaDonRepository.getOneHDByMa(ma);
    }
    public Boolean addOrUpdateHoaDon(HoaDon1 hoaDon){
        return hoaDonRepository.addOrUpdateHoaDon(hoaDon);
    }
    
    public Boolean deleteHoaDon(String id){
        return hoaDonRepository.deleteHoaDon(id);
    }
    
    public List<HoaDonChiTietHoaDon> getALLHDCT(String idHD){
        return hoaDonRepository.getAllHDCT(idHD);
    }
     public HoaDonChiTietHoaDon getOneHDCT(String idSP, String idHD){
         return hoaDonRepository.getOneHDCT(idSP, idHD);
     }
    public Boolean addOrUpdateHDCT(HoaDonChiTietHoaDon hdct){
        return hoaDonRepository.addOrUpdateHDCT(hdct);
    }
    
    public Boolean deleteHDCT(String idSP, String idHD){
        return hoaDonRepository.deleteHDCT(idSP, idHD);
    }
    public List<KhuyenMai> getAllKM(){
        return hoaDonRepository.getAllKM();
    }
    public List<KhachHangHoaDon> getAllKhachHang(){
        return hoaDonRepository.getAllKhachHang();
    }
    public List<NhanVien> getAllNhanVien(){
        return hoaDonRepository.getAllNhanVien();
    }
    
}
