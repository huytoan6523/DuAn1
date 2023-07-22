/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import domainModels.KhuyenMai;
import java.util.List;
import repositories.KhuyenMaiRepository;
import service.impl.IManageKhuyenMaiServive;

/**
 *
 * @author PC
 */
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
    public void addKhachHang(KhuyenMai khuyenMai) {
        this.khuyenMaiRepository.insert(khuyenMai);
    }
    
    @Override
    public void updateKhachHang(KhuyenMai khuyenMai) {
        this.khuyenMaiRepository.update(khuyenMai);
    }
    
    @Override
    public KhuyenMai findByMa(String ma) {
        return this.khuyenMaiRepository.findByMa(ma);
    }
    
    @Override
    public void deleteKhachHang(KhuyenMai khuyenMai) {
        this.khuyenMaiRepository.delete(khuyenMai);
    }
    
}
