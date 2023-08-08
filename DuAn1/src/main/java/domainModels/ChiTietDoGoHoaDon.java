/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainModels;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author PhiLT
 */
@Entity
@Table(name = "ChiTietDoGo")
public class ChiTietDoGoHoaDon implements Serializable{
    
    @Id
    @Column(name = "Id")
    private String id;
    
    private String TenSP;
    
    private Integer SoLuong;
    
    private BigDecimal GiaNhap;
    
    private BigDecimal GiaBan;
    
    private String MoTa;
    
    private Integer TrangThai;

    public ChiTietDoGoHoaDon() {
    }

    public ChiTietDoGoHoaDon(String id, String TenSP, Integer SoLuong, BigDecimal GiaNhap, BigDecimal GiaBan, String MoTa, Integer TrangThai) {
        this.id = id;
        this.TenSP = TenSP;
        this.SoLuong = SoLuong;
        this.GiaNhap = GiaNhap;
        this.GiaBan = GiaBan;
        this.MoTa = MoTa;
        this.TrangThai = TrangThai;
    }

    public String getId() {
        return id;
    }

    public String getTenSP() {
        return TenSP;
    }

    public Integer getSoLuong() {
        return SoLuong;
    }

    public BigDecimal getGiaNhap() {
        return GiaNhap;
    }

    public BigDecimal getGiaBan() {
        return GiaBan;
    }

    public String getMoTa() {
        return MoTa;
    }

    public Integer getTrangThai() {
        return TrangThai;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public void setSoLuong(Integer SoLuong) {
        this.SoLuong = SoLuong;
    }

    public void setGiaNhap(BigDecimal GiaNhap) {
        this.GiaNhap = GiaNhap;
    }

    public void setGiaBan(BigDecimal GiaBan) {
        this.GiaBan = GiaBan;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public void setTrangThai(Integer TrangThai) {
        this.TrangThai = TrangThai;
    }
    
}
