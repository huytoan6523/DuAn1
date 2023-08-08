/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainModels;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author PhiLT
 */
@Entity
@Table(name = "HoaDon")
public class HoaDon1 implements Serializable{
    @Id
    @Column(name = "Id")
    private String id;
    
    @Column(name = "Ma")
    private String ma;
    
    @Column(name = "NgayTao")
    private Date ngayTao;
    
    @Column(name = "NgayThanhToan")
    private Date ngayThanhToan;
    
    @Column(name = "TrangThaiHoaDon")
    private Integer trangThaiHoaDon;
    
    @Column(name = "NgayNhan")
    private Date ngayNhan;
    
    @Column(name = "TrangThai")
    private Integer trangThai;
    
    @Column(name = "ThanhTien")
    private BigDecimal thanhTien;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdKhuyenMai", referencedColumnName = "Id")
    private KhuyenMai khuyenMai;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdKhachHang", referencedColumnName = "Id")
    private KhachHangHoaDon KhachHang;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdNhanVien", referencedColumnName = "Id")
    private NhanVien nhanVien;

    @OneToMany(mappedBy = "hoaDon", fetch = FetchType.LAZY)
    private List<HoaDonChiTietHoaDon> dsHDCT;
    
    public HoaDon1() {
    }

    public HoaDon1(String id, String ma, Date ngayTao, Date ngayThanhToan, Integer trangThaiHoaDon, Date ngayNhan, Integer trangThai, BigDecimal thanhTien, KhuyenMai khuyenMai, KhachHangHoaDon KhachHang, NhanVien nhanVien) {
        this.id = id;
        this.ma = ma;
        this.ngayTao = ngayTao;
        this.ngayThanhToan = ngayThanhToan;
        this.trangThaiHoaDon = trangThaiHoaDon;
        this.ngayNhan = ngayNhan;
        this.trangThai = trangThai;
        this.thanhTien = thanhTien;
        this.khuyenMai = khuyenMai;
        this.KhachHang = KhachHang;
        this.nhanVien = nhanVien;
    }

    public String getId() {
        return id;
    }

    public String getMa() {
        return ma;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public Integer getTrangThaiHoaDon() {
        return trangThaiHoaDon;
    }

    public Date getNgayNhan() {
        return ngayNhan;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public KhuyenMai getKhuyenMai() {
        return khuyenMai;
    }

    public KhachHangHoaDon getKhachHang() {
        return KhachHang;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public void setTrangThaiHoaDon(Integer trangThaiHoaDon) {
        this.trangThaiHoaDon = trangThaiHoaDon;
    }

    public void setNgayNhan(Date ngayNhan) {
        this.ngayNhan = ngayNhan;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }

    public void setKhuyenMai(KhuyenMai khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public void setKhachHang(KhachHangHoaDon KhachHang) {
        this.KhachHang = KhachHang;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }
    
    
}
