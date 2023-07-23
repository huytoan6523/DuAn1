/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import domainModels.HoaDon;
import domainModels.HoaDonChiTiet;
import domainModels.KhachHang;
import domainModels.KhuyenMai;
import domainModels.NhanVien;
import java.util.List;
import repositories.HoaDonRepository;

/**
 *
 * @author PhiLT
 */
public class HoaDonService {
    private HoaDonRepository hoaDonRepository = new HoaDonRepository();
    
    public List<HoaDon> getALHoaDon(){
        return hoaDonRepository.getALHoaDon();
    }
    public List<HoaDon> getHoaDonByMa(String ma){
        return hoaDonRepository.getHoaDonByMa(ma);
    }
    public HoaDon getOneHDByMa(String ma){
        return hoaDonRepository.getOneHDByMa(ma);
    }
    public Boolean addOrUpdateHoaDon(HoaDon hoaDon){
        return hoaDonRepository.addOrUpdateHoaDon(hoaDon);
    }
    
    public Boolean deleteHoaDon(String id){
        return hoaDonRepository.deleteHoaDon(id);
    }
    
    public List<HoaDonChiTiet> getALLHDCT(String idHD){
        return hoaDonRepository.getAllHDCT(idHD);
    }
     public HoaDonChiTiet getOneHDCT(String idSP, String idHD){
         return hoaDonRepository.getOneHDCT(idSP, idHD);
     }
    public Boolean addOrUpdateHDCT(HoaDonChiTiet hdct){
        return hoaDonRepository.addOrUpdateHDCT(hdct);
    }
    
    public Boolean deleteHDCT(String idSP, String idHD){
        return hoaDonRepository.deleteHDCT(idSP, idHD);
    }
    public List<KhuyenMai> getAllKM(){
        return hoaDonRepository.getAllKM();
    }
    public List<KhachHang> getAllKhachHang(){
        return hoaDonRepository.getAllKhachHang();
    }
    public List<NhanVien> getAllNhanVien(){
        return hoaDonRepository.getAllNhanVien();
    }
    
}
