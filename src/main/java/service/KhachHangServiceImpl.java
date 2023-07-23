package service;

import domainModels.HoaDon;
import domainModels.KhachHang;
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
    public List<KhachHang> getAll() {
        List<KhachHang> khachHangs = this.khachHangRepository.findAll();
        return khachHangs;
    }
    
    @Override
    public void addKhachHang(KhachHang khachHang) {
        this.khachHangRepository.insert(khachHang);
    }
    
    @Override
    public void updateKhachHang(KhachHang khachHang) {
        this.khachHangRepository.update(khachHang);
    }

    @Override
    public KhachHang findByMa(String ma) {
        return this.khachHangRepository.findByMa(ma);
    }

    @Override
    public void deleteKhachHang(KhachHang khachHang) {
        this.khachHangRepository.delete(khachHang);
    }

    @Override
    public List<HoaDon> getHoaDonByKhachHang(KhachHang khachHang) {
        return this.khachHangRepository.getHoaDon(khachHang);
    }
    
}
