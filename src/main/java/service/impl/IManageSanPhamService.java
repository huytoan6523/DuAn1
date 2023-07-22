/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.impl;

import domainModels.SanPham;
import java.util.List;

import viewModel.ViewModelSanPham;

/**
 *
 * @author ktkha
 */
public interface IManageSanPhamService {

    List<SanPham> getAll();
    
    SanPham getOne(String ten);

    boolean add(SanPham sp);

    boolean update(SanPham sp);

    boolean delete(SanPham sp);
}
