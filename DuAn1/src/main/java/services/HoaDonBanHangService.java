/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.Hoadon;
import domainModels.NhanVien;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import repositories.BanHangHoaDonRepository;
import services.impl.IManageHoaDonBanHangService;
import viewModel.ViewModelHoaDonBanHang;

/**
 *
 * @author Admin
 */
public class HoaDonBanHangService implements IManageHoaDonBanHangService {

    private BanHangHoaDonRepository i = new BanHangHoaDonRepository();

    @Override
    public List<ViewModelHoaDonBanHang> getList() {
        List<Hoadon> hds = i.getList();
        List<ViewModelHoaDonBanHang> lists = new ArrayList<>();;
        try {
            for (Hoadon a : hds) {
                ViewModelHoaDonBanHang b = new ViewModelHoaDonBanHang();
                b.setId(a.getId());
                b.setMa(a.getMa());
                b.setNgayTao(a.getNgayTao() + "");
                if (a.getTrangThaiHoaDon() == 0) {
                    b.setTrangThaiHoaDon("Chưa Thanh Toán");
                }
                b.setTenNV(a.getIdNhanVien().getHoTen());

                lists.add(b);
            }

            return lists;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean add(Hoadon hd) {
        try {
            return i.addhoadon(hd);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int maxma() {
        return i.getMaxMa();
    }

    @Override
    public boolean update(String id, BigDecimal thanhTien ,String idkh , String idKM) {
        try {
            return i.update(id, thanhTien, idkh, idKM);
        } catch (Exception e) {
            return false;
        }
    }
}
