/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import domainModels.LoaiSP;
import java.util.List;
import repositories.LoaiSanPhamRepository;
import service.impl.IManageLoaiSanPhamService;

/**
 *
 * @author Uy Tin
 */
public class LoaiSanPhamService implements IManageLoaiSanPhamService {

    private LoaiSanPhamRepository loaiSanPhamRepository = new LoaiSanPhamRepository();

    @Override
    public List<LoaiSP> getAll() {
        return loaiSanPhamRepository.getAll();
    }

    @Override
    public boolean add(LoaiSP lsp) {
        return loaiSanPhamRepository.add(lsp);
    }

    @Override
    public boolean update(LoaiSP lsp) {
        return loaiSanPhamRepository.update(lsp);
    }

    @Override
    public boolean delete(LoaiSP lsp) {
        return loaiSanPhamRepository.delete(lsp);
    }

    @Override
    public LoaiSP getOne(String ten) {
        return loaiSanPhamRepository.getOne(ten);
    }

}
