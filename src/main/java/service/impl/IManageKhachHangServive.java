/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.impl;


import domainModels.HoaDon1;
import domainModels.KhachHangHoaDon;
import java.util.List;

/**
 *
 * @author PC
 */
public interface IManageKhachHangServive {

    List<KhachHangHoaDon> getAll();

    List<HoaDon1> getHoaDonByKhachHang(KhachHangHoaDon khachHang);

    void addKhachHang(KhachHangHoaDon khachHang);

    void updateKhachHang(KhachHangHoaDon khachHang);

    KhachHangHoaDon findByMa(String ma);

    void deleteKhachHang(KhachHangHoaDon khachHang);

}
