/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

/**
 *
 * @author Uy Tin
 */
public class ViewModelSanPham {

    private String Id;
    private String Ma;
    private String Ten;
    private int TrangThai;

    public ViewModelSanPham() {
    }

    public ViewModelSanPham(String Id, String Ma, String Ten, int TrangThai) {
        this.Id = Id;
        this.Ma = Ma;
        this.Ten = Ten;
        this.TrangThai = TrangThai;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getMa() {
        return Ma;
    }

    public void setMa(String Ma) {
        this.Ma = Ma;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    public void setTrangThai(String còn_Hàng) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
