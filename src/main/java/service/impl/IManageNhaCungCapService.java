/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.impl;

import domainModels.NhaCungCap;
import java.util.List;

/**
 *
 * @author Uy Tin
 */
public interface IManageNhaCungCapService {

    List<NhaCungCap> getAll();

    NhaCungCap getOne(String ten);

    Boolean them(NhaCungCap ncc);

    Boolean sua(NhaCungCap ncc);

    Boolean xoa(NhaCungCap ncc);
}
