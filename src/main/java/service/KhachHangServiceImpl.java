package service;

import domainModels.HoaDon1;
import domainModels.KhachHangHoaDon;
import java.util.List;
import repositories.KhachHangRepository;
import service.impl.IManageKhachHangServive;




/**
 *
 * @author PC
 */
public class KhachHangServiceImpl implements IManageKhachHangServive {
    
    private KhachHangRepository khachHangRepository;
    
    public KhachHangServiceImpl() {
        khachHangRepository = new KhachHangRepository();
    }
    
    @Override
    public List<KhachHangHoaDon> getAll() {
        List<KhachHangHoaDon> khachHangs = this.khachHangRepository.findAll();
        return khachHangs;
    }
    
    @Override
    public void addKhachHang(KhachHangHoaDon khachHang) {
        this.khachHangRepository.insert(khachHang);
    }
    
    @Override
    public void updateKhachHang(KhachHangHoaDon khachHang) {
        this.khachHangRepository.update(khachHang);
    }

    @Override
    public KhachHangHoaDon findByMa(String ma) {
        return this.khachHangRepository.findByMa(ma);
    }

    @Override
    public void deleteKhachHang(KhachHangHoaDon khachHang) {
        this.khachHangRepository.delete(khachHang);
    }

    @Override
    public List<HoaDon1> getHoaDonByKhachHang(KhachHangHoaDon khachHang) {
        return this.khachHangRepository.getHoaDon(khachHang);
    }
    
}
