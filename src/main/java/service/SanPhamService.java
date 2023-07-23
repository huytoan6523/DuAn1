/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import domainModels.SanPham;
import java.util.ArrayList;
import java.util.List;
import repositories.SanPhamRepository;
import service.impl.IManageSanPhamService;
import viewModel.ViewModelSanPham;

/**
 *
 * @author ktkha
 */
public class SanPhamService implements IManageSanPhamService {

    private SanPhamRepository spRp = new SanPhamRepository();

    @Override
    public List<SanPham> getAll() {
        return spRp.getAll();
    }

    @Override
    public boolean add(SanPham sp) {
        return spRp.add(sp);
    }

    @Override
    public boolean update(SanPham sp) {
        return spRp.update(sp);
    }

    @Override
    public boolean delete(SanPham sp) {
        return spRp.delete(sp);
    }

    @Override
    public SanPham getOne(String ten) {
        return spRp.getOne(ten);
    }

}
