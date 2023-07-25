/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainModels;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;

/**
 *
 * @author PhiLT
 */
@Embeddable
public class HoaDonChiTietId implements Serializable{
    @JoinColumn(name = "IdChiTietDoGo", referencedColumnName = "Id")
    private String IdChiTietDoGo;
    
    @JoinColumn(name = "IdHoaDon", referencedColumnName = "Id")
    private String IdHoaDon;
}
