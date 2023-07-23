/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.impl;

import domainModels.KhuyenMai;
import java.util.List;

/**
 *
 * @author PC
 */
public interface IManageKhuyenMaiServive {

    List<KhuyenMai> getAll();

    void addKhuyenMai(KhuyenMai khuyenMai);

    void updateKhuyenMai(KhuyenMai khuyenMai);

    KhuyenMai findByMa(String ma);

    void deleteKhuyenMai(KhuyenMai khuyenMai);

}
