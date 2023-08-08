/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainModels;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author PhiLT
 */
@Entity
@Table(name = "KhachHang")
public class KhachHangHoaDon implements Serializable{
    @Id
    @Column(name = "Id", columnDefinition = "uniqueidentifier")
    private String id;
    
    @Column(name = "Ma")
    private String ma;
    
    @Column(name = "TenKhachHang")
    private String tenKhachHang;
    
    @Column(name = "Sdt")
    private String sdt;
    
    @Column(name = "DiaChi")
    private String diaChi;
    
    @Column(name = "TrangThai")
    private String trangThai;

    public KhachHangHoaDon() {
    }

    public KhachHangHoaDon(String id, String ma, String tenKhachHang, String sdt, String diaChi, String trangThai) {
        this.id = id;
        this.ma = ma;
        this.tenKhachHang = tenKhachHang;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
    }

    public String getId() {
        return id;
    }

    public String getMa() {
        return ma;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public String getSdt() {
        return sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
}
