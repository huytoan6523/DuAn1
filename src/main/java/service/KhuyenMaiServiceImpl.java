package service;

import domainModels.KhuyenMai;
import java.util.List;
import repositories.KhuyenMaiRepository;
import service.impl.IManageKhuyenMaiServive;


public class KhuyenMaiServiceImpl implements IManageKhuyenMaiServive {
    
    private KhuyenMaiRepository khuyenMaiRepository;
    
    public KhuyenMaiServiceImpl() {
        khuyenMaiRepository = new KhuyenMaiRepository();
    }
    
    @Override
    public List<KhuyenMai> getAll() {
        List<KhuyenMai> khuyenMais = this.khuyenMaiRepository.findAll();
        return khuyenMais;
    }
    
    @Override
    public void addKhuyenMai(KhuyenMai khuyenMai) {
        this.khuyenMaiRepository.insert(khuyenMai);
    }
    
    @Override
    public void updateKhuyenMai(KhuyenMai khuyenMai) {
        this.khuyenMaiRepository.update(khuyenMai);
    }
    
    @Override
    public KhuyenMai findByMa(String ma) {
        return this.khuyenMaiRepository.findByMa(ma);
    }
    
    @Override
    public void deleteKhuyenMai(KhuyenMai khuyenMai) {
        this.khuyenMaiRepository.delete(khuyenMai);
    }
    
}
