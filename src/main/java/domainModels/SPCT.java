/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainModels;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author tungs
 */
@Entity
@Table(name = "ChiTietDoGo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SPCT implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "Id", columnDefinition = "uniqueidentifier")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "IdSanPham")
    private SanPham sp;

    @ManyToOne
    @JoinColumn(name = "IdLoaiSp")
    private LoaiSP loai;

    @ManyToOne
    @JoinColumn(name = "IdDongGo")
    private DongGo dongGo;

    @ManyToOne
    @JoinColumn(name = "IdNhaCungCap")
    private NhaCungCap nhaCungCap;

    @ManyToOne
    @JoinColumn(name = "IdNguonGoc")
    private NguonGoc nguonGoc;

    @ManyToOne
    @JoinColumn(name = "IdDonViTinh")
    private DonViTinh donViTinh;

    @Column(name = "tenSP")
    private String ten;

    @Column(name = "SoLuong")
    private Integer sLg;

    @Column(name = "GiaNhap")
    private BigDecimal giaNhap;

    @Column(name = "GiaBan")
    private BigDecimal giaBan;

    @Column(name = "MoTa")
    private String moTa;

    @Column(name = "TrangThai")
    private Integer trangThai;

    public SPCT(SanPham sp, LoaiSP loai, DongGo dongGo, NhaCungCap nhaCungCap, NguonGoc nguonGoc, DonViTinh donViTinh, String ten, Integer sLg, BigDecimal giaNhap, BigDecimal giaBan, String moTa, Integer trangThai) {
        this.sp = sp;
        this.loai = loai;
        this.dongGo = dongGo;
        this.nhaCungCap = nhaCungCap;
        this.nguonGoc = nguonGoc;
        this.donViTinh = donViTinh;
        this.ten = ten;
        this.sLg = sLg;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }
}
