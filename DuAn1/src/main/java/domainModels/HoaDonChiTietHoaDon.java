/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainModels;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author PhiLT
 */
@Entity
@Table(name = "HoaDonChiTiet")
public class HoaDonChiTietHoaDon implements Serializable {
    @Id
    @EmbeddedId
    private HoaDonChiTietId id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdChiTietDoGo", referencedColumnName = "Id", insertable = false, updatable = false)
    private ChiTietDoGoHoaDon ChiTietDoGo;
      
   
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdHoaDon", referencedColumnName = "Id", insertable = false, updatable = false)
    private HoaDon1 hoaDon;
            
    @Column(name = "SoLuong")
    private Integer soLuong;
    
    @Column(name = "DonGia")
    private BigDecimal donGia;

    public HoaDonChiTietHoaDon() {
    }

    public HoaDonChiTietHoaDon(HoaDonChiTietId id, ChiTietDoGoHoaDon ChiTietDoGo, HoaDon1 hoaDon, Integer soLuong, BigDecimal donGia) {
        this.id = id;
        this.ChiTietDoGo = ChiTietDoGo;
        this.hoaDon = hoaDon;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public HoaDonChiTietId getId() {
        return id;
    }

    public ChiTietDoGoHoaDon getChiTietDoGo() {
        return ChiTietDoGo;
    }

    public HoaDon1 getHoaDon() {
        return hoaDon;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setId(HoaDonChiTietId id) {
        this.id = id;
    }

    public void setChiTietDoGo(ChiTietDoGoHoaDon ChiTietDoGo) {
        this.ChiTietDoGo = ChiTietDoGo;
    }

    public void setHoaDon(HoaDon1 hoaDon) {
        this.hoaDon = hoaDon;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }
    
}
