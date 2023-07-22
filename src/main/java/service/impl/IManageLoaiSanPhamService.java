/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.impl;

import domainModels.LoaiSP;
import java.util.List;

/**
 *
 * @author Uy Tin
 */
public interface IManageLoaiSanPhamService {

    List<LoaiSP> getAll();

    LoaiSP getOne(String ten);

    boolean add(LoaiSP lsp);

    boolean update(LoaiSP lsp);

    boolean delete(LoaiSP lsp);
}
