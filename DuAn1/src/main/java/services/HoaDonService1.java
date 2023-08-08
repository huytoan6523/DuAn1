/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

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
public class HoaDonService1 {
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
    public Boolean saveHoaDon(HoaDon1 hoaDon){
        return hoaDonRepository.saveHoaDon(hoaDon);
    }
    public Boolean updateHoaDon(HoaDon1 hoaDon){
        return hoaDonRepository.updateHoaDon(hoaDon);
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
