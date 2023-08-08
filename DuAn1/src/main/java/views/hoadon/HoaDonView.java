/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.hoadon;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import domainModels.HoaDon1;
import domainModels.HoaDonChiTietHoaDon;
import domainModels.KhachHangHoaDon;
import domainModels.KhuyenMai;
import domainModels.NhanVien;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import services.HoaDonService1;
import views.MenuView;

/**
 *
 * @author PhiLT
 */
public class HoaDonView extends javax.swing.JPanel {
private HoaDonService1 hoaDonService = new HoaDonService1();
private SimpleDateFormat dfm = new SimpleDateFormat("yy-MM-dd");
    /**
     * Creates new form HoaDonView
     */
private FrameTest frameTest;
String IdNV;
    String TenNV;
    String CV;
    public HoaDonView(String Id, String Ten, String cv, FrameTest frameTest) {
        initComponents();
        this.frameTest = frameTest;
        
        tbModelHD  =  (DefaultTableModel) tbHoaDon.getModel();
        tbModelHDCT = (DefaultTableModel) tbHDCT.getModel();
        cbbLocTrangThaiHD.setSelectedIndex(0);
        IdNV = Id;
        TenNV = Ten;
        CV = cv;
        loadCbbKM();
        loadCbbKH();
        loadCbbNV();
    }
    
    private List<HoaDon1> lstHoaDon ;
    private List<HoaDonChiTietHoaDon> lstHDCT;
    private List<KhuyenMai> lstKM;
    private List<KhachHangHoaDon> lstKH;
    private List<NhanVien> lstNV;
    private DefaultTableModel tbModelHD;
    private DefaultTableModel tbModelHDCT;
    private DefaultComboBoxModel cbbKMModel;
    private DefaultComboBoxModel cbbKHModel;
    private DefaultComboBoxModel cbbNVModel;
    
    
    private void loadCbbKM(){
        cbbKMModel = (DefaultComboBoxModel) cbbKM.getModel();
        lstKM = hoaDonService.getAllKM();
        for (KhuyenMai km : lstKM) {
            cbbKMModel.addElement(km.getTenKhuyenMai());
        }
    }
    
    private void loadCbbKH(){
        cbbKHModel = (DefaultComboBoxModel) cbbKhachHang.getModel();
        lstKH = hoaDonService.getAllKhachHang();
        for (KhachHangHoaDon kh : lstKH) {
            cbbKHModel.addElement(kh.getTenKhachHang());
        }
    }
    
    private void loadCbbNV(){
        cbbNVModel = (DefaultComboBoxModel) cbbNhanVien.getModel();
        lstNV = hoaDonService.getAllNhanVien();
        for (NhanVien nv : lstNV) {
            cbbNVModel.addElement(nv.getHoTen());
        }
    }
    
private void loadTableHoaDon(){
    lstHoaDon = new ArrayList<>();
    lstHoaDon = hoaDonService.getALHoaDon();
    tbModelHD.setRowCount(0);
    for (HoaDon1 hoaDon : lstHoaDon) {
        System.out.println(hoaDon.toString());
        tbModelHD.addRow(new Object[]{
        hoaDon.getMa(),
            hoaDon.getNgayTao(),
            hoaDon.getNgayThanhToan(),
            hoaDon.getKhuyenMai()==null? "":hoaDon.getKhuyenMai().getTenKhuyenMai(),
            hoaDon.getKhachHang()==null? "":hoaDon.getKhachHang().getTenKhachHang(),
            hoaDon.getNhanVien()==null? "":hoaDon.getNhanVien().getHoTen(),
            hoaDon.getNgayNhan(),
            hoaDon.getThanhTien()
        });
    }
    
}

private void loadTableHDCT(String idHD){
    lstHDCT = new ArrayList<>();
    lstHDCT = hoaDonService.getALLHDCT(idHD);
    tbModelHDCT.setRowCount(0);
    for (HoaDonChiTietHoaDon hdct : lstHDCT) {
        tbModelHDCT.addRow(new Object[]{
            hdct.getHoaDon()==null? "": hdct.getHoaDon().getMa(),
            hdct.getChiTietDoGo()==null? "":hdct.getChiTietDoGo().getTenSP(),
            hdct.getSoLuong(),
            hdct.getDonGia()
        });
    }
    
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popUpMenuTbHDCT = new javax.swing.JPopupMenu();
        updateHDCT = new javax.swing.JMenuItem();
        deleteHDCT = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        cbbLocTrangThaiHD = new javax.swing.JComboBox<>();
        txtTimKiemHD = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbHoaDon = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHDCT = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnCreate = new javax.swing.JButton();
        btnExportInvoice = new javax.swing.JButton();
        txtThanhTien = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        dcNgayNhan = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        cbbKhachHang = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        dcNgayTT = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        dcNgayTao = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        cbbKM = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cbbNhanVien = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        cbbTrangThai = new javax.swing.JComboBox<>();
        btnSendMail = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        popUpMenuTbHDCT.setPreferredSize(new java.awt.Dimension(200, 50));

        updateHDCT.setText("Update");
        updateHDCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateHDCTActionPerformed(evt);
            }
        });
        popUpMenuTbHDCT.add(updateHDCT);

        deleteHDCT.setText("Delete");
        deleteHDCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteHDCTActionPerformed(evt);
            }
        });
        popUpMenuTbHDCT.add(deleteHDCT);

        setBackground(new java.awt.Color(204, 204, 204));
        setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        setMaximumSize(new java.awt.Dimension(1199, 853));
        setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 6, 6, 6, new java.awt.Color(0, 0, 0)));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(null);

        cbbLocTrangThaiHD.setBackground(new java.awt.Color(255, 153, 51));
        cbbLocTrangThaiHD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Chờ thanh toán", "Đã thanh toán" }));
        cbbLocTrangThaiHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocTrangThaiHDActionPerformed(evt);
            }
        });
        jPanel1.add(cbbLocTrangThaiHD);
        cbbLocTrangThaiHD.setBounds(1090, 80, 117, 30);

        txtTimKiemHD.setBackground(new java.awt.Color(255, 204, 153));
        txtTimKiemHD.setText("Nhập mã hóa đơn cần tìm");
        txtTimKiemHD.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtTimKiemHD.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemHDCaretUpdate(evt);
            }
        });
        txtTimKiemHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKiemHDMouseClicked(evt);
            }
        });
        jPanel1.add(txtTimKiemHD);
        txtTimKiemHD.setBounds(460, 80, 370, 30);

        tbHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã", "Ngày tạo", "Ngày thanh toán", "Khuyến mại", "Khách hàng", "Nhân viên", "Ngày nhận", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbHoaDon.setMaximumSize(new java.awt.Dimension(1199, 850));
        tbHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHoaDonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbHoaDon);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(460, 130, 750, 300);

        tbHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Tên sản phẩm", "Số lượng", "Đơn giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbHDCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbHDCTMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbHDCT);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(460, 480, 750, 310);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Quản lý hóa đơn");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(530, 20, 189, 32);

        btnDelete.setBackground(new java.awt.Color(255, 255, 153));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(btnDelete);
        btnDelete.setBounds(230, 570, 140, 41);

        btnUpdate.setBackground(new java.awt.Color(255, 255, 153));
        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpdate);
        btnUpdate.setBounds(230, 510, 140, 41);

        btnCreate.setBackground(new java.awt.Color(255, 255, 153));
        btnCreate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCreate.setText("Create");
        btnCreate.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });
        jPanel1.add(btnCreate);
        btnCreate.setBounds(40, 510, 140, 41);

        btnExportInvoice.setBackground(new java.awt.Color(255, 255, 153));
        btnExportInvoice.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnExportInvoice.setText("Export invoice");
        btnExportInvoice.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        btnExportInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportInvoiceActionPerformed(evt);
            }
        });
        jPanel1.add(btnExportInvoice);
        btnExportInvoice.setBounds(40, 570, 140, 41);

        txtThanhTien.setEditable(false);
        txtThanhTien.setBackground(new java.awt.Color(255, 255, 255));
        txtThanhTien.setActionCommand("<Not Set>");
        txtThanhTien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtThanhTien);
        txtThanhTien.setBounds(230, 440, 140, 29);

        jLabel16.setBackground(new java.awt.Color(0, 0, 0));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Thành tiền");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(230, 410, 70, 23);

        dcNgayNhan.setBackground(new java.awt.Color(255, 255, 255));
        dcNgayNhan.setDateFormatString("yyyy-MM-dd");
        jPanel1.add(dcNgayNhan);
        dcNgayNhan.setBounds(230, 360, 140, 30);

        jLabel14.setBackground(new java.awt.Color(0, 0, 0));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Ngày nhận");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(230, 330, 70, 23);

        cbbKhachHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn khách hàng" }));
        jPanel1.add(cbbKhachHang);
        cbbKhachHang.setBounds(230, 280, 140, 30);

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Khách hàng");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(230, 250, 70, 23);

        dcNgayTT.setBackground(new java.awt.Color(255, 255, 255));
        dcNgayTT.setDateFormatString("yyyy-MM-dd");
        jPanel1.add(dcNgayTT);
        dcNgayTT.setBounds(230, 190, 140, 30);

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Ngày thanh toán");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(230, 160, 91, 23);

        txtMa.setActionCommand("<Not Set>");
        txtMa.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtMa);
        txtMa.setBounds(230, 110, 140, 29);

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Mã");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(230, 80, 70, 23);

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("ID");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(40, 80, 70, 23);

        txtId.setEditable(false);
        txtId.setBackground(new java.awt.Color(255, 255, 255));
        txtId.setActionCommand("<Not Set>");
        txtId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtId);
        txtId.setBounds(40, 110, 140, 29);

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Ngày tạo");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(40, 160, 70, 23);

        dcNgayTao.setBackground(new java.awt.Color(255, 255, 255));
        dcNgayTao.setDateFormatString("yyyy-MM-dd");
        jPanel1.add(dcNgayTao);
        dcNgayTao.setBounds(40, 190, 140, 30);

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Khuyến mại");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(40, 250, 70, 23);

        cbbKM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn khuyến mại" }));
        jPanel1.add(cbbKM);
        cbbKM.setBounds(40, 280, 140, 30);

        jLabel13.setBackground(new java.awt.Color(0, 0, 0));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Nhân viên");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(40, 330, 70, 23);

        cbbNhanVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn nhân viên" }));
        jPanel1.add(cbbNhanVien);
        cbbNhanVien.setBounds(40, 360, 140, 30);

        jLabel15.setBackground(new java.awt.Color(0, 0, 0));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Trạng thái");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(40, 410, 70, 23);

        cbbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chờ thanh toán", "Đã thanh toán" }));
        jPanel1.add(cbbTrangThai);
        cbbTrangThai.setBounds(40, 440, 140, 30);

        btnSendMail.setBackground(new java.awt.Color(255, 255, 153));
        btnSendMail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSendMail.setText("Send mail");
        btnSendMail.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        btnSendMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendMailActionPerformed(evt);
            }
        });
        jPanel1.add(btnSendMail);
        btnSendMail.setBounds(230, 630, 140, 41);

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("Back");
        jButton3.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(0, 0, 130, 50);

        add(jPanel1);
        jPanel1.setBounds(0, 0, 1250, 830);
    }// </editor-fold>//GEN-END:initComponents

    
    private void tbHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonMouseClicked
        // TODO add your handling code here:
        int row = tbHoaDon.getSelectedRow();
        String ma = tbHoaDon.getValueAt(row, 0)+"";
        HoaDon1 hd = hoaDonService.getOneHDByMa(ma);
        txtId.setText(hd.getId());
        txtMa.setText(hd.getMa());
        dcNgayTao.setDate(hd.getNgayTao());
        dcNgayTT.setDate(hd.getNgayThanhToan());
        if(hd.getKhuyenMai()==null){
            cbbKM.setSelectedIndex(0);
        }else{
        cbbKM.setSelectedItem(hd.getKhuyenMai().getTenKhuyenMai());
        }
        if(hd.getKhachHang()==null){
            cbbKhachHang.setSelectedIndex(0);
        }else{
        cbbKhachHang.setSelectedItem(hd.getKhachHang().getTenKhachHang());
        }
        if(hd.getNhanVien()==null){
            cbbNhanVien.setSelectedIndex(0);
        }else{
        cbbNhanVien.setSelectedItem(hd.getNhanVien().getHoTen());
        }
        dcNgayNhan.setDate(hd.getNgayNhan());
        cbbTrangThai.setSelectedIndex(hd.getTrangThaiHoaDon());
        txtThanhTien.setText(hd.getThanhTien()+"");
        loadTableHDCT(hd.getId());
    }//GEN-LAST:event_tbHoaDonMouseClicked

    private void cbbLocTrangThaiHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocTrangThaiHDActionPerformed
        // TODO add your handling code here:
        String check = (String) cbbLocTrangThaiHD.getSelectedItem();
        tbModelHD.setRowCount(0);
        if(check.equalsIgnoreCase("Chờ thanh toán")){
                for (HoaDon1 hd : lstHoaDon) {
                if(hd.getTrangThaiHoaDon()==0){
                    tbModelHD.addRow(new Object[]{
        hd.getMa(),
            hd.getNgayTao(),
            hd.getNgayThanhToan(),
            hd.getKhuyenMai()==null? "":hd.getKhuyenMai().getTenKhuyenMai(),
            hd.getKhachHang()==null? "":hd.getKhachHang().getTenKhachHang(),
            hd.getNhanVien()==null? "":hd.getNhanVien().getHoTen(),
            hd.getNgayNhan(),
            hd.getThanhTien()
        });
                }
            }
    }else if(check.equalsIgnoreCase("Đã thanh toán")){
            for (HoaDon1 hd : lstHoaDon) {
                if(hd.getTrangThaiHoaDon()==1){
                    tbModelHD.addRow(new Object[]{
        hd.getMa(),
            hd.getNgayTao(),
            hd.getNgayThanhToan(),
            hd.getKhuyenMai()==null? "":hd.getKhuyenMai().getTenKhuyenMai(),
            hd.getKhachHang()==null? "":hd.getKhachHang().getTenKhachHang(),
            hd.getNhanVien()==null? "":hd.getNhanVien().getHoTen(),
            hd.getNgayNhan(),
            hd.getThanhTien()
        });
                }
            }
        }else{
        loadTableHoaDon();
    }
    }//GEN-LAST:event_cbbLocTrangThaiHDActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int checkTT = cbbTrangThai.getSelectedIndex();
        if(checkTT==0){
        String id = txtId.getText();
        if(id.trim().length()==0){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn");
        }else{
            if(hoaDonService.deleteHoaDon(id)){
               int check = cbbLocTrangThaiHD.getSelectedIndex();
               cbbLocTrangThaiHD.setSelectedIndex(check);
                JOptionPane.showMessageDialog(this, "Xóa hóa đơn thành công");
            }else{
                JOptionPane.showMessageDialog(this, "Không thể xóa hóa đơn");
            }
        }
        }else{
            JOptionPane.showMessageDialog(this, "Hóa đơn đã thanh toán không thể xóa");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtTimKiemHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemHDMouseClicked
        // TODO add your handling code here:
        txtTimKiemHD.setText("");
    }//GEN-LAST:event_txtTimKiemHDMouseClicked

    private void txtTimKiemHDCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemHDCaretUpdate
        // TODO add your handling code here:
        String ma = txtTimKiemHD.getText();
        loadTBHDTimKiem(ma);
    }//GEN-LAST:event_txtTimKiemHDCaretUpdate
private List<HoaDon1> lstHDTimKiem = new ArrayList<>();
    private void loadTBHDTimKiem(String ma){
        tbModelHD.setRowCount(0);
    lstHDTimKiem = hoaDonService.getHoaDonByMa(ma);
    for (HoaDon1 hd : lstHDTimKiem) {
                
                    tbModelHD.addRow(new Object[]{
        hd.getMa(),
            hd.getNgayTao(),
            hd.getNgayThanhToan(),
            hd.getKhuyenMai()==null? "":hd.getKhuyenMai().getTenKhuyenMai(),
            hd.getKhachHang()==null? "":hd.getKhachHang().getTenKhachHang(),
            hd.getNhanVien()==null? "":hd.getNhanVien().getHoTen(),
            hd.getNgayNhan(),
            hd.getThanhTien()
        });
                }
            
}
    private void updateHDCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateHDCTActionPerformed
        // TODO add your handling code here:
        int row = tbHDCT.getSelectedRow();
        if (row != -1) {
            
          String maHD = tbHDCT.getValueAt(row, 0)+"";
          String tenSP = tbHDCT.getValueAt(row, 1)+"";
          HoaDonChiTietHoaDon hdct = lstHDCT.get(row);
          int check = hdct.getHoaDon().getTrangThaiHoaDon();
          if(check==1){
              JOptionPane.showMessageDialog(this, "Hóa đơn đã thanh toán, không thể sửa");
              return;
          }
         String sl = JOptionPane.showInputDialog(this, "Mời bạn nhập lại số lượng");
            Pattern pattern = Pattern.compile("\\d");
            Matcher matcher = pattern.matcher(sl);
            if(!matcher.matches()){
                JOptionPane.showMessageDialog(this, "Chỉ được nhập số");
                return;
            }
            hdct.setSoLuong(Integer.valueOf(sl));
           if(hoaDonService.addOrUpdateHDCT(hdct)){
               HoaDon1 hd = hoaDonService.getOneHDByMa(maHD);
               loadTableHDCT(hd.getId());
               JOptionPane.showMessageDialog(this, "Sửa thành công");
           }else{
               JOptionPane.showMessageDialog(this, "Sửa không thành công");
           }
        }
    }//GEN-LAST:event_updateHDCTActionPerformed

    private void deleteHDCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteHDCTActionPerformed
        // TODO add your handling code here:
        int row = tbHDCT.getSelectedRow();
        if (row != -1) {
          String maHD = tbHDCT.getValueAt(row, 0)+"";
          String tenSP = tbHDCT.getValueAt(row, 1)+"";
          HoaDonChiTietHoaDon hdct = lstHDCT.get(row);
           int check = hdct.getHoaDon().getTrangThaiHoaDon();
          if(check==1){
              JOptionPane.showMessageDialog(this, "Hóa đơn đã thanh toán, không thể xóa");
              return;
          }
           if(hoaDonService.deleteHDCT(hdct.getChiTietDoGo().getId(), hdct.getHoaDon().getId())){
               HoaDon1 hd = hoaDonService.getOneHDByMa(maHD);
               loadTableHDCT(hd.getId());
               JOptionPane.showMessageDialog(this, "Xóa thành công");
           }else{
               JOptionPane.showMessageDialog(this, "Xóa không thành công");
           }
        }
    }//GEN-LAST:event_deleteHDCTActionPerformed

    private void tbHDCTMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHDCTMouseReleased
        // TODO add your handling code here:
        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (evt.isPopupTrigger() && tbHoaDon.getSelectedRowCount() != 0) {
                popUpMenuTbHDCT.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
    }//GEN-LAST:event_tbHDCTMouseReleased

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        Integer trangThai = cbbTrangThai.getSelectedIndex();
        if(trangThai==0){

            HoaDon1 hoaDon = new HoaDon1();
            String id = txtId.getText();
            String ma = txtMa.getText();

            Date ngayTao = dcNgayTao.getDate();

            if(ngayTao==null){
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>Check ngay tao"+ ngayTao);
                hoaDon.setNgayTao(null);
            }else{

                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>Check ngay tao: "+ ngayTao);
                try {
                    hoaDon.setNgayTao(dfm.parse(dfm.format(ngayTao)));
                } catch (ParseException ex) {
                    Logger.getLogger(HoaDonView.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            Date ngayTT = dcNgayTT.getDate();

            if(ngayTT==null){
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>Check ngayTT"+ ngayTT);
                hoaDon.setNgayThanhToan(null);
            }else{

                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>Check ngayTT"+ ngayTT);
                try {
                    hoaDon.setNgayThanhToan(dfm.parse(dfm.format(ngayTT)));
                } catch (ParseException ex) {
                    Logger.getLogger(HoaDonView.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            int viTriKM = cbbKM.getSelectedIndex();
            int viTriKH = cbbKhachHang.getSelectedIndex();
            int viTriNV = cbbNhanVien.getSelectedIndex();
            if(viTriKM==0){
                hoaDon.setKhuyenMai(null);
            }else{
                KhuyenMai km = lstKM.get(viTriKM-1);
                hoaDon.setKhuyenMai(km);
            }
            if(viTriKH==0){
                hoaDon.setKhachHang(null);
            }else{
                KhachHangHoaDon kh = lstKH.get(viTriKH-1);
                hoaDon.setKhachHang(kh);
            }
            if(viTriNV==0){
                hoaDon.setNhanVien(null);
            }else{
                NhanVien nv = lstNV.get(viTriNV-1);
                hoaDon.setNhanVien(nv);
            }

            Date ngayNhan = dcNgayNhan.getDate();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>"+ ngayNhan);
            if(ngayNhan==null){
                hoaDon.setNgayNhan(null);
            }else{

                try {
                    hoaDon.setNgayNhan(dfm.parse(dfm.format(ngayNhan)));
                } catch (ParseException ex) {
                    Logger.getLogger(HoaDonView.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            BigDecimal thanhTien = new BigDecimal(txtThanhTien.getText().equals("")? "0":txtThanhTien.getText());

            hoaDon.setId(id);
            hoaDon.setMa(ma);

            hoaDon.setTrangThaiHoaDon(trangThai);
            hoaDon.setThanhTien(thanhTien);
            if(hoaDonService.updateHoaDon(hoaDon)==false){
                JOptionPane.showMessageDialog(this, "Sửa hóa đơn không thành công");
            }else{
                int check = cbbLocTrangThaiHD.getSelectedIndex();
                cbbLocTrangThaiHD.setSelectedIndex(check);
                JOptionPane.showMessageDialog(this, "Sửa hóa đơn thành công");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Hóa đơn đã được thanh toán, không thể sửa");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // TODO add your handling code here:
            HoaDon1 hoaDon = new HoaDon1();
            String ma = txtMa.getText();
            if(ma.trim().length()==0){
                JOptionPane.showMessageDialog(this, "Mã không được bỏ trống");
            }
            Date ngayTao = dcNgayTao.getDate();

            if(ngayTao==null){
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>Check ngay tao"+ ngayTao);
                hoaDon.setNgayTao(null);
            }else{

                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>Check ngay tao: "+ ngayTao);
                try {
                    hoaDon.setNgayTao(dfm.parse(dfm.format(ngayTao)));
                } catch (ParseException ex) {
                    Logger.getLogger(HoaDonView.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            Date ngayTT = dcNgayTT.getDate();

            if(ngayTT==null){
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>Check ngayTT"+ ngayTT);
                hoaDon.setNgayThanhToan(null);
            }else{

                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>Check ngayTT"+ ngayTT);
                try {
                    hoaDon.setNgayThanhToan(dfm.parse(dfm.format(ngayTT)));
                } catch (ParseException ex) {
                    Logger.getLogger(HoaDonView.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            int viTriKM = cbbKM.getSelectedIndex();
            int viTriKH = cbbKhachHang.getSelectedIndex();
            int viTriNV = cbbNhanVien.getSelectedIndex();
            if(viTriKM==0){
                hoaDon.setKhuyenMai(null);
            }else{
                KhuyenMai km = lstKM.get(viTriKM-1);
                hoaDon.setKhuyenMai(km);
            }
            if(viTriKH==0){
                hoaDon.setKhachHang(null);
            }else{
                KhachHangHoaDon kh = lstKH.get(viTriKH-1);
                hoaDon.setKhachHang(kh);
            }
            if(viTriNV==0){
                hoaDon.setNhanVien(null);
            }else{
                NhanVien nv = lstNV.get(viTriNV-1);
                hoaDon.setNhanVien(nv);
            }

            Date ngayNhan = dcNgayNhan.getDate();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>"+ ngayNhan);
            if(ngayNhan==null){
                hoaDon.setNgayNhan(null);
            }else{

                try {
                    hoaDon.setNgayNhan(dfm.parse(dfm.format(ngayNhan)));
                } catch (ParseException ex) {
                    Logger.getLogger(HoaDonView.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Check thanhtien"+ txtThanhTien.getText()+"");
            BigDecimal thanhTien = new BigDecimal(txtThanhTien.getText().equals("")? "0":txtThanhTien.getText());

            hoaDon.setId(UUID.randomUUID()+"");
            hoaDon.setMa(ma);
            int trangThai = cbbTrangThai.getSelectedIndex();
            hoaDon.setTrangThaiHoaDon(trangThai);
            hoaDon.setThanhTien(thanhTien);
            if(hoaDonService.saveHoaDon(hoaDon)==false){
                JOptionPane.showMessageDialog(this, "Thêm hóa đơn không thành công");
            }else{
                int check = cbbLocTrangThaiHD.getSelectedIndex();
                cbbLocTrangThaiHD.setSelectedIndex(check);
                JOptionPane.showMessageDialog(this, "Thêm hóa đơn thành công");
            }
        
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnExportInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportInvoiceActionPerformed
        // TODO add your handling code here:
        int row = tbHoaDon.getSelectedRow();
        if(row<0){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn trên bảng");
            return;
        }
        String ma = tbHoaDon.getValueAt(row, 0)+"";
        if(ma.length()==0){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn");
            return;
        }
        HoaDon1 hoaDon = hoaDonService.getOneHDByMa(ma);
        String path = "C:\\Users\\PC\\OneDrive\\Máy tính\\DuAn1\\DuAn1\\DuAn1\\invoicePdf\\"+ma+".pdf";
    PdfWriter pdfWriter = null;
    try {
        pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);
        pdfDocument.setDefaultPageSize(PageSize.A5);
        
        float colNameShop[]={400};
        Table tableNameShop = new Table(colNameShop);
        tableNameShop.addCell(new Cell().add("Wooden plus").setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER).setFontSize(20f).setBorder(Border.NO_BORDER));
        
        float col = 280f;
        float columnWidth[] = {col, col};
        Table table = new Table(columnWidth);
        
        table.setFontColor(Color.BLACK);
        String ngayTT = hoaDon.getNgayThanhToan()==null? "...":dfm.format(hoaDon.getNgayThanhToan());
        table.addCell(new Cell(1, 0).add("Date: "+ngayTT)
        .setTextAlignment(TextAlignment.LEFT)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setMarginTop(30f)
                .setMarginBottom(30f)
                .setBorder(Border.NO_BORDER)   
        );
        table.addCell(new Cell().add("Tel: "+024.35563067).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().setFontColor(Color.BLACK)
                .add("Code invoice: "+ma)
        .setTextAlignment(TextAlignment.LEFT)
                .setMarginTop(30f)
                .setMarginBottom(30f)
                .setBorder(Border.NO_BORDER)
//                .setMarginRight(10f)
        );
        
        float colTitleWith[] = {500f};
        Table tableTitle = new Table(colTitleWith);
        tableTitle.addCell(new Cell(0,1).add("INVOICE")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(30f)
                .setBorder(Border.NO_BORDER)
        .setBold()
        );
        
        float colWidth[] = {80, 300, 100, 80};
        Table customerInforTable = new Table(colWidth);
        
        String tenNV = hoaDon.getNhanVien()==null? "...":hoaDon.getNhanVien().getHoTen();
        customerInforTable.addCell(new Cell().add("Seller").setBorder(Border.NO_BORDER));
        customerInforTable.addCell(new Cell(0, 3).add(tenNV).setBorder(Border.NO_BORDER));
        
        String tenKH = hoaDon.getKhachHang()==null? "...":hoaDon.getKhachHang().getTenKhachHang();
        String sdtKH = hoaDon.getKhachHang()==null? "...":hoaDon.getKhachHang().getSdt();
        String addressKH = hoaDon.getKhachHang()==null?"...":hoaDon.getKhachHang().getDiaChi();
        customerInforTable.addCell(new Cell().add("Customer").setBorder(Border.NO_BORDER));
        customerInforTable.addCell(new Cell().add(tenKH).setBorder(Border.NO_BORDER));
        customerInforTable.addCell(new Cell().add("Sdt").setBorder(Border.NO_BORDER));
        customerInforTable.addCell(new Cell().add(sdtKH).setBorder(Border.NO_BORDER));
        
        customerInforTable.addCell(new Cell().add("Address").setBorder(Border.NO_BORDER));
        customerInforTable.addCell(new Cell(0, 3).add(addressKH).setBorder(Border.NO_BORDER));
        
        
        
        float colWidthContent[] = {140, 140, 140, 140};
        Table tableContent = new Table(colWidthContent);
        List<HoaDonChiTietHoaDon> lstHDCTInvoice = hoaDonService.getALLHDCT(hoaDon.getId());
        tableContent.addCell(new Cell().add("Product name").setBorder(Border.NO_BORDER));
        tableContent.addCell(new Cell().add("Quantity").setBorder(Border.NO_BORDER));
        tableContent.addCell(new Cell().add("Unit price").setBorder(Border.NO_BORDER));
        tableContent.addCell(new Cell().add("Amount").setBorder(Border.NO_BORDER));
        BigDecimal totalAmount = new BigDecimal("0");
       BigDecimal amount = new BigDecimal("0");
        for (HoaDonChiTietHoaDon hdct : lstHDCTInvoice) {
           amount  =   new BigDecimal(hdct.getSoLuong()+"").multiply(hdct.getDonGia());
           
            tableContent.addCell(new Cell().add(hdct.getChiTietDoGo().getTenSP()).setBorder(Border.NO_BORDER));
            tableContent.addCell(new Cell().add(hdct.getSoLuong()+"").setBorder(Border.NO_BORDER));
            tableContent.addCell(new Cell().add(hdct.getDonGia()+"").setBorder(Border.NO_BORDER));
            tableContent.addCell(new Cell().add(amount+"").setBorder(Border.NO_BORDER));
            
            totalAmount = totalAmount.add(amount);
            
            amount = new BigDecimal("0");
        }
        
        tableContent.addCell(new Cell(0, 3).add("Total").setBold().setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
        tableContent.addCell(new Cell().add(totalAmount+" VNĐ").setBold().setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
        
        document.add(tableNameShop);
        document.add(table);
        document.add(tableTitle);
        document.add(customerInforTable);
//        document.add(tableSeller);
        document.add(new Paragraph("\n"));
        document.add(tableContent);
        document.close();
        System.out.println("PDF created");
    } catch (FileNotFoundException ex) {
        ex.printStackTrace();
    }
       
    
    }//GEN-LAST:event_btnExportInvoiceActionPerformed

    private void btnSendMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendMailActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmSendMail().setVisible(true);
            }
        });
    }//GEN-LAST:event_btnSendMailActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        MenuView v = new MenuView(IdNV, TenNV, CV);
        v.setLocationRelativeTo(null);
        v.setVisible(true);

        this.frameTest.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

  
    
    
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExportInvoice;
    private javax.swing.JButton btnSendMail;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbbKM;
    private javax.swing.JComboBox<String> cbbKhachHang;
    private javax.swing.JComboBox<String> cbbLocTrangThaiHD;
    private javax.swing.JComboBox<String> cbbNhanVien;
    private javax.swing.JComboBox<String> cbbTrangThai;
    private com.toedter.calendar.JDateChooser dcNgayNhan;
    private com.toedter.calendar.JDateChooser dcNgayTT;
    private com.toedter.calendar.JDateChooser dcNgayTao;
    private javax.swing.JMenuItem deleteHDCT;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu popUpMenuTbHDCT;
    private javax.swing.JTable tbHDCT;
    private javax.swing.JTable tbHoaDon;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtTimKiemHD;
    private javax.swing.JMenuItem updateHDCT;
    // End of variables declaration//GEN-END:variables
}
