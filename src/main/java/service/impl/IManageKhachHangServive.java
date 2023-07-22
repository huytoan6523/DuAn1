/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.impl;


import domainModels.HoaDon;
import domainModels.KhachHang;
import java.util.List;

/**
 *
 * @author PC
 */
public interface IManageKhachHangServive {

    List<KhachHang> getAll();

    List<HoaDon> getHoaDonByKhachHang(KhachHang khachHang);

    void addKhachHang(KhachHang khachHang);

    void updateKhachHang(KhachHang khachHang);

    KhachHang findByMa(String ma);

    void deleteKhachHang(KhachHang khachHang);

}
