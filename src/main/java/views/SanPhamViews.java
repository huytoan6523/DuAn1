/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import service.DonViTinhService;
import service.DongGoService;
import service.LoaiSanPhamService;
import service.NguonGocService;
import service.NhaCungCapService;
import service.SPCTService;
import service.SanPhamService;
import domainModels.DonViTinh;
import domainModels.DongGo;
import domainModels.LoaiSP;
import domainModels.NguonGoc;
import domainModels.NhaCungCap;
import domainModels.SPCT;
import domainModels.SanPham;
import java.awt.Color;
import java.awt.Font;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import service.impl.IManageDonViTinhService;
import service.impl.IManageDongGoService;
import service.impl.IManageLoaiSanPhamService;
import service.impl.IManageNguonGocService;
import service.impl.IManageNhaCungCapService;
import service.impl.IManageSanPhamService;
import service.impl.ISPCTService;

/**
 *
 * @author Uy Tin
 */
public class SanPhamViews extends javax.swing.JFrame {

    private DefaultTableModel model;

    private IManageSanPhamService sanPhamService = new SanPhamService();
    private IManageNhaCungCapService nhaCungCapService = new NhaCungCapService();
    private IManageDongGoService dongGoService = new DongGoService();
    private IManageLoaiSanPhamService loaiSanPhamService = new LoaiSanPhamService();
    private IManageNguonGocService nguonGocService = new NguonGocService();
    private IManageDonViTinhService donViTinhService = new DonViTinhService();

    private List<SPCT> lstSpct;
    private ISPCTService spctSv = new SPCTService();

    public SanPhamViews() {
        initComponents();
        LoadTableSP();
        LoadTableNSX();
        LoadTableDG();
        LoadTableLSP();
        LoadTableNg();
        LoadTablelDVT();
        loadTableSPCT();
    }

    private void loadTableSPCT() {
        lstSpct = spctSv.getAll();
        model = (DefaultTableModel) tbSPCT.getModel();
        model.setRowCount(0);
        for (SPCT x : lstSpct) {
            model.addRow(new Object[]{x.getSp().getTen(), x.getLoai().getTenDongSP(),
                x.getDongGo().getTenLoaiGo(), x.getNhaCungCap().getTenNCC(),
                x.getNguonGoc().getQuocGia(), x.getDonViTinh().getDonViTinh(),
                x.getTen(), x.getSLg(), x.getGiaNhap(), x.getGiaBan(),
                x.getMoTa(), x.getTrangThai() == 1 ? "Đang bán" : "Ngưng bán"});
        }
    }

    private void fillDataSPCT(int index) {
        SPCT spct = lstSpct.get(index);
        lbIdSPCT.setText(String.valueOf(spct.getId()));
        cbbSanPham.setSelectedItem(spct.getSp().getTen());
        cbbLoaiSP.setSelectedItem(spct.getLoai().getTenDongSP());
        cbbDongGo.setSelectedItem(spct.getDongGo().getTenLoaiGo());
        cbbNhaCungCap.setSelectedItem(spct.getNhaCungCap().getTenNCC());
        cbbNguonGoc.setSelectedItem(spct.getNguonGoc().getQuocGia());
        cbbDonViTinh.setSelectedItem(spct.getDonViTinh().getDonViTinh());
        txtTenSPCT.setText(spct.getTen());
        txtSLgSPCT.setText(String.valueOf(spct.getSLg()));
        txtGiaNhapSPCT.setText(String.valueOf(spct.getGiaNhap()));
        txtGiaBanSPCT.setText(String.valueOf(spct.getGiaBan()));
        txtMoTaSPCT.setText(spct.getMoTa());
        if (spct.getTrangThai() == 1) {
            cbbTrangThaiSPCT.setSelectedIndex(0);
        } else {
            cbbTrangThaiSPCT.setSelectedIndex(1);
        }

    }

    private SPCT getSpct() {
        String spStr = (String) cbbSanPham.getSelectedItem();
        String loaiStr = (String) cbbLoaiSP.getSelectedItem();
        String dongStr = (String) cbbDongGo.getSelectedItem();
        String nhaCungCapStr = (String) cbbNhaCungCap.getSelectedItem();
        String nguonGocStr = (String) cbbNguonGoc.getSelectedItem();
        String donViTinhStr = (String) cbbDonViTinh.getSelectedItem();
        String ten = txtTenSPCT.getText();
        Integer sLg = Integer.parseInt(txtSLgSPCT.getText());
        BigDecimal giaNhap = new BigDecimal(txtGiaNhapSPCT.getText());
        BigDecimal giaBan = new BigDecimal(txtGiaBanSPCT.getText());
        String moTa = txtMoTaSPCT.getText();
        Integer trangThai = cbbTrangThaiSPCT.getSelectedIndex() == 0 ? 1 : 0;

        SanPham sp = sanPhamService.getOne(spStr);
        LoaiSP loai = loaiSanPhamService.getOne(loaiStr);
        DongGo dong = dongGoService.getOne(dongStr);
        NhaCungCap nhaCungCap = nhaCungCapService.getOne(nhaCungCapStr);
        NguonGoc nguonGoc = nguonGocService.getOne(nguonGocStr);
        DonViTinh donViTinh = donViTinhService.getOne(donViTinhStr);

        SPCT spct = new SPCT(sp, loai, dong, nhaCungCap, nguonGoc, donViTinh, ten, sLg, giaNhap, giaBan, moTa, trangThai);

        return spct;
    }

    private void LoadTableSP() {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        cbbSanPham.removeAllItems();
        for (SanPham sp : sanPhamService.getAll()) {
            cbbSanPham.addItem(sp.getTen());
            Object[] row = {sp.getId(), sp.getMa(), sp.getTen()};
            model.addRow(row);
        }
    }

    private void LoadTableNSX() {
        DefaultTableModel model = (DefaultTableModel) tbNhaSanXuat.getModel();
        model.setRowCount(0);
        cbbNhaCungCap.removeAllItems();
        model.setColumnIdentifiers(new String[]{"ID", "Mã", "Tên Nhà cung cấp", "Địa chỉ", "số điện thoại"});
        for (NhaCungCap ncc : nhaCungCapService.getAll()) {
            cbbNhaCungCap.addItem(ncc.getTenNCC());
            Object[] row = {ncc.getId(), ncc.getMa(), ncc.getTenNCC(), ncc.getDiaChi(), ncc.getSdt()};
            model.addRow(row);
        }
    }

    private void LoadTableDG() {
        DefaultTableModel model = (DefaultTableModel) tblDongGo.getModel();
        model.setRowCount(0);
        cbbDongGo.removeAllItems();
        model.setColumnIdentifiers(new String[]{"ID", "Mã", "Tên Loai go"});
        for (DongGo dg : dongGoService.getAll()) {
            cbbDongGo.addItem(dg.getTenLoaiGo());
            Object[] row = {dg.getId(), dg.getMa(), dg.getTenLoaiGo()};
            model.addRow(row);
        }
    }

    private void LoadTableLSP() {
        DefaultTableModel model = (DefaultTableModel) tblLoaiSanPham.getModel();
        model.setRowCount(0);
        cbbLoaiSP.removeAllItems();
        model.setColumnIdentifiers(new String[]{"ID", "Mã", "Tên Dong sp"});
        for (LoaiSP lsp : loaiSanPhamService.getAll()) {
            cbbLoaiSP.addItem(lsp.getTenDongSP());
            Object[] row = {lsp.getId(), lsp.getMa(), lsp.getTenDongSP()};
            model.addRow(row);
        }
    }

    private void LoadTableNg() {
        DefaultTableModel model = (DefaultTableModel) tblNguonGoc.getModel();
        model.setRowCount(0);
        cbbNguonGoc.removeAllItems();
        model.setColumnIdentifiers(new String[]{"ID", "Mã", "Tên quoc gia"});
        for (NguonGoc ng : nguonGocService.getAll()) {
            cbbNguonGoc.addItem(ng.getQuocGia());
            Object[] row = {ng.getId(), ng.getMa(), ng.getQuocGia()};
            model.addRow(row);
        }
    }

    private void LoadTablelDVT() {
        DefaultTableModel model = (DefaultTableModel) TBDONVITINH.getModel();
        model.setRowCount(0);
        cbbDonViTinh.removeAllItems();
        model.setColumnIdentifiers(new String[]{"ID", "Mã", "Don VI Tinh"});
        for (DonViTinh dvt : donViTinhService.getAll()) {
            cbbDonViTinh.addItem(dvt.getDonViTinh());
            Object[] row = {dvt.getId(), dvt.getMa(), dvt.getDonViTinh()};
            model.addRow(row);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTimkiemSanPham = new javax.swing.JTextField();
        btntimkiemSanPham = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        txtMa = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLoaiSanPham = new javax.swing.JTable();
        txtMaLoaiSp = new javax.swing.JTextField();
        txtTenDongSp = new javax.swing.JTextField();
        btnSuaLoaiSp = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnXoaLoaiSp = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        btnThemLoaiSp = new javax.swing.JButton();
        btnTimKiem = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtIDLoaiSp = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblNguonGoc = new javax.swing.JTable();
        txtMaNguonGoc = new javax.swing.JTextField();
        txtQuocGia = new javax.swing.JTextField();
        btnSuaNG = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btnXoaNG = new javax.swing.JButton();
        txtTimKiemNG = new javax.swing.JTextField();
        btnThemNG = new javax.swing.JButton();
        btnTimKiemNguonGoc = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtIDNguonGoc = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDongGo = new javax.swing.JTable();
        txtMaDongGo = new javax.swing.JTextField();
        txtTenDongGo = new javax.swing.JTextField();
        btnSua1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btnXoa1 = new javax.swing.JButton();
        txtTimKiemDongGo = new javax.swing.JTextField();
        btnThem1 = new javax.swing.JButton();
        btnTimKiemDongGo = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtIDDongGo = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbNhaSanXuat = new javax.swing.JTable();
        txtma = new javax.swing.JTextField();
        txtdiachi = new javax.swing.JTextField();
        btnupdate = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        btndelete = new javax.swing.JButton();
        txttimkiem = new javax.swing.JTextField();
        btnadd = new javax.swing.JButton();
        btntimkiem = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        txtten = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtdienthoai = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        TBDONVITINH = new javax.swing.JTable();
        TXTMADVT = new javax.swing.JTextField();
        TXTDVT = new javax.swing.JTextField();
        btnSua2 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        btnXoa2 = new javax.swing.JButton();
        TXTTIMKIEMDVT = new javax.swing.JTextField();
        btnThem2 = new javax.swing.JButton();
        btnTimKiem1 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        TXTIDDVT = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        lbIdSPCT = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        cbbLoaiSP = new javax.swing.JComboBox<>();
        cbbDongGo = new javax.swing.JComboBox<>();
        cbbSanPham = new javax.swing.JComboBox<>();
        cbbNhaCungCap = new javax.swing.JComboBox<>();
        cbbNguonGoc = new javax.swing.JComboBox<>();
        cbbDonViTinh = new javax.swing.JComboBox<>();
        txtTenSPCT = new javax.swing.JTextField();
        txtSLgSPCT = new javax.swing.JTextField();
        txtGiaNhapSPCT = new javax.swing.JTextField();
        txtGiaBanSPCT = new javax.swing.JTextField();
        cbbTrangThaiSPCT = new javax.swing.JComboBox<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtMoTaSPCT = new javax.swing.JTextArea();
        txtTimKiemSPCT = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbSPCT = new javax.swing.JTable();
        btnThemSPCT = new javax.swing.JButton();
        btnSuaSPCT = new javax.swing.JButton();
        btnXoaSPCT = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Id:");

        txtTimkiemSanPham.setText("\n");

        btntimkiemSanPham.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btntimkiemSanPham.setText("Tìm Kiếm");
        btntimkiemSanPham.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Mã:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Tên:");

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Mã", "Tên"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        btnSua.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSua.setText("UPDATE");
        btnSua.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnXoa.setText("DELETE");
        btnXoa.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnThem.setText("ADD");
        btnThem.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(btnThem)
                .addGap(65, 65, 65)
                .addComponent(btnSua)
                .addGap(125, 125, 125)
                .addComponent(btnXoa)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(49, 49, 49)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(218, 218, 218)
                                .addComponent(txtTimkiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btntimkiemSanPham)))
                        .addGap(0, 330, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimkiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btntimkiemSanPham))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua)
                    .addComponent(btnThem)
                    .addComponent(btnXoa))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        jTabbedPane1.addTab("Sản Phẩm", jPanel1);

        tblLoaiSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Ma", "Tên Dòng SP"
            }
        ));
        tblLoaiSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLoaiSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblLoaiSanPham);

        btnSuaLoaiSp.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSuaLoaiSp.setText("UPDATE");
        btnSuaLoaiSp.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSuaLoaiSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaLoaiSpActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Id:");

        btnXoaLoaiSp.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnXoaLoaiSp.setText("DELETE");
        btnXoaLoaiSp.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnXoaLoaiSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaLoaiSpActionPerformed(evt);
            }
        });

        txtTimKiem.setText("Nhập để tìm kiếm\n");

        btnThemLoaiSp.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnThemLoaiSp.setText("ADD");
        btnThemLoaiSp.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnThemLoaiSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLoaiSpActionPerformed(evt);
            }
        });

        btnTimKiem.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Mã:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Tên Dòng SP:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(115, 115, 115)
                                .addComponent(btnThemLoaiSp)
                                .addGap(87, 87, 87)
                                .addComponent(btnSuaLoaiSp)
                                .addGap(125, 125, 125)
                                .addComponent(btnXoaLoaiSp))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(34, 34, 34)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtTenDongSp, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtMaLoaiSp, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtIDLoaiSp, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(206, 206, 206)
                                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnTimKiem))))
                        .addGap(0, 344, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtIDLoaiSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMaLoaiSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTenDongSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSuaLoaiSp)
                    .addComponent(btnThemLoaiSp)
                    .addComponent(btnXoaLoaiSp))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        jTabbedPane1.addTab("LoạiSP", jPanel2);

        tblNguonGoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Ma", "Tên Quốc Gia"
            }
        ));
        tblNguonGoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNguonGocMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblNguonGoc);

        btnSuaNG.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSuaNG.setText("UPDATE");
        btnSuaNG.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSuaNG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNGActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("Id:");

        btnXoaNG.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnXoaNG.setText("DELETE");
        btnXoaNG.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnXoaNG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNGActionPerformed(evt);
            }
        });

        txtTimKiemNG.setText("Nhập để tìm kiếm\n");

        btnThemNG.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnThemNG.setText("ADD");
        btnThemNG.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnThemNG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNGActionPerformed(evt);
            }
        });

        btnTimKiemNguonGoc.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnTimKiemNguonGoc.setText("Tìm Kiếm");
        btnTimKiemNguonGoc.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Mã:");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setText("Tên Quốc gia:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 857, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtQuocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaNguonGoc, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIDNguonGoc, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(115, 115, 115)
                                .addComponent(btnThemNG)
                                .addGap(78, 78, 78)
                                .addComponent(btnSuaNG)
                                .addGap(125, 125, 125)
                                .addComponent(btnXoaNG))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(256, 256, 256)
                                .addComponent(txtTimKiemNG, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnTimKiemNguonGoc)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemNG, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiemNguonGoc))
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtIDNguonGoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtMaNguonGoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtQuocGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSuaNG)
                    .addComponent(btnThemNG)
                    .addComponent(btnXoaNG))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        jTabbedPane1.addTab("Nguồn Gốc", jPanel3);

        tblDongGo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Mã", "Tên Loại Gỗ"
            }
        ));
        tblDongGo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDongGoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblDongGo);

        btnSua1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSua1.setText("UPDATE");
        btnSua1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua1ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setText("Id:");

        btnXoa1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnXoa1.setText("DELETE");
        btnXoa1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnXoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa1ActionPerformed(evt);
            }
        });

        txtTimKiemDongGo.setText("Nhập để tìm kiếm\n");

        btnThem1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnThem1.setText("ADD");
        btnThem1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });

        btnTimKiemDongGo.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnTimKiemDongGo.setText("Tìm Kiếm");
        btnTimKiemDongGo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setText("Mã:");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setText("Tên:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(btnThem1)
                .addGap(99, 99, 99)
                .addComponent(btnSua1)
                .addGap(125, 125, 125)
                .addComponent(btnXoa1)
                .addContainerGap(338, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(49, 49, 49)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenDongGo, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaDongGo, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIDDongGo, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(202, 202, 202)
                                .addComponent(txtTimKiemDongGo, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnTimKiemDongGo)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemDongGo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiemDongGo))
                .addGap(39, 39, 39)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtIDDongGo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtMaDongGo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtTenDongGo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua1)
                    .addComponent(btnThem1)
                    .addComponent(btnXoa1))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        jTabbedPane1.addTab("Dòng Gỗ", jPanel4);

        tbNhaSanXuat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbNhaSanXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNhaSanXuatMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbNhaSanXuat);

        btnupdate.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnupdate.setText("UPDATE");
        btnupdate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setText("Id:");

        btndelete.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btndelete.setText("DELETE");
        btndelete.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        txttimkiem.setText("Nhập để tìm kiếm\n");

        btnadd.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnadd.setText("ADD");
        btnadd.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });

        btntimkiem.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btntimkiem.setText("Tìm Kiếm");
        btntimkiem.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setText("Mã:");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel15.setText("Tên:");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel19.setText("Địa Chỉ:");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel20.setText("SDT:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(btnadd)
                .addGap(134, 134, 134)
                .addComponent(btnupdate)
                .addGap(93, 93, 93)
                .addComponent(btndelete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 857, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtma, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtten, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtdienthoai, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(247, 247, 247)
                                .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btntimkiem)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btntimkiem))
                .addGap(42, 42, 42)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtdienthoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnadd)
                    .addComponent(btnupdate)
                    .addComponent(btndelete))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Nhà Sản Xuất", jPanel5);

        TBDONVITINH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TBDONVITINH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBDONVITINHMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(TBDONVITINH);

        btnSua2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSua2.setText("UPDATE");
        btnSua2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSua2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua2ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel16.setText("Id:");

        btnXoa2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnXoa2.setText("DELETE");
        btnXoa2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnXoa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa2ActionPerformed(evt);
            }
        });

        TXTTIMKIEMDVT.setText("Nhập để tìm kiếm\n");

        btnThem2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnThem2.setText("ADD");
        btnThem2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnThem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem2ActionPerformed(evt);
            }
        });

        btnTimKiem1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnTimKiem1.setText("Tìm Kiếm");
        btnTimKiem1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel17.setText("Mã:");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel18.setText("Đơn Vị Tính:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 857, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TXTDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TXTMADVT, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TXTIDDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(115, 115, 115)
                                .addComponent(btnThem2)
                                .addGap(115, 115, 115)
                                .addComponent(btnSua2)
                                .addGap(125, 125, 125)
                                .addComponent(btnXoa2))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(219, 219, 219)
                                .addComponent(TXTTIMKIEMDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnTimKiem1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXTTIMKIEMDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem1))
                .addGap(42, 42, 42)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(TXTIDDVT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(TXTMADVT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(TXTDVT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua2)
                    .addComponent(btnThem2)
                    .addComponent(btnXoa2))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        jTabbedPane1.addTab("Đơn Vị Tính", jPanel6);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel21.setText("SPCT");

        lbIdSPCT.setText("ID");

        jLabel23.setText("Sản phẩm");

        jLabel22.setText("Loại sản phẩm");

        jLabel24.setText("Dòng gỗ");

        jLabel25.setText("Nhà cung cấp");

        jLabel26.setText("Nguồn gốc");

        jLabel27.setText("Đơn vị tính");

        jLabel28.setText("Tên sản phẩm");

        jLabel29.setText("Số lượng");

        jLabel30.setText("Giá nhập");

        jLabel31.setText("Giá bán");

        jLabel32.setText("Mô tả");

        jLabel33.setText("Trạng thái");

        cbbLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbDongGo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbNhaCungCap.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNhaCungCapActionPerformed(evt);
            }
        });

        cbbNguonGoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbDonViTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbTrangThaiSPCT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang bán", "Ngưng bán" }));

        txtMoTaSPCT.setColumns(20);
        txtMoTaSPCT.setRows(5);
        jScrollPane7.setViewportView(txtMoTaSPCT);

        txtTimKiemSPCT.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        txtTimKiemSPCT.setForeground(new java.awt.Color(153, 153, 153));
        txtTimKiemSPCT.setText("Tìm kiếm...");
        txtTimKiemSPCT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimKiemSPCTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTimKiemSPCTFocusLost(evt);
            }
        });
        txtTimKiemSPCT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemSPCTKeyReleased(evt);
            }
        });

        tbSPCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Sản phẩm", "Loại sản phẩm", "Dòng gỗ", "Nhà cung cấp", "Nguồn gốc", "Đơn vị tính", "Tên sản phẩm", "Số lượng", "Giá nhập", "Giá bán", "Mô tả", "Trạng thái"
            }
        ));
        tbSPCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSPCTMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tbSPCT);

        btnThemSPCT.setText("Thêm");
        btnThemSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPCTActionPerformed(evt);
            }
        });

        btnSuaSPCT.setText("Sửa");
        btnSuaSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSPCTActionPerformed(evt);
            }
        });

        btnXoaSPCT.setText("Xóa");
        btnXoaSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPCTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbIdSPCT)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtTimKiemSPCT, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel22)
                                            .addComponent(jLabel24)
                                            .addComponent(jLabel23)
                                            .addComponent(jLabel25)
                                            .addComponent(jLabel33))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cbbSanPham, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbbLoaiSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbbDongGo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbbNhaCungCap, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbbNguonGoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbbDonViTinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbbTrangThaiSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel28)
                                            .addComponent(jLabel29)
                                            .addComponent(jLabel30)
                                            .addComponent(jLabel31)
                                            .addComponent(jLabel32)
                                            .addComponent(jLabel21))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtTenSPCT)
                                                .addComponent(txtGiaNhapSPCT)
                                                .addComponent(txtGiaBanSPCT)
                                                .addComponent(txtSLgSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(btnThemSPCT)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnSuaSPCT)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnXoaSPCT)))))
                        .addGap(0, 317, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbIdSPCT)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(cbbSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(txtTenSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(cbbLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(txtSLgSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(cbbDongGo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(txtGiaNhapSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(cbbNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(txtGiaBanSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(cbbNguonGoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(cbbDonViTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel32))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbbTrangThaiSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnThemSPCT)
                        .addComponent(btnSuaSPCT)
                        .addComponent(btnXoaSPCT)))
                .addGap(18, 18, 18)
                .addComponent(txtTimKiemSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("SPCT", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 869, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 202, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 191, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    private void validateSP(){
//    if (txtMa.getText().trim().equals("") || txtTen.getText().trim().equals("")) {
//            JOptionPane.showMessageDialog(this, "Du lieu khong duoc de trong");
//            return;
//        }
//    
//    }
    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:

        if (txtMa.getText().trim().equals("") || txtTen.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Du lieu khong duoc de trong");
            return;
        }

        SanPham sp = new SanPham();
        sp.setMa(txtMa.getText());
        sp.setTen(txtTen.getText());
        sp.setTrangThai(1);
        if (sanPhamService.add(sp)) {
            JOptionPane.showMessageDialog(this, "them thanh coong");

        } else {
            JOptionPane.showMessageDialog(this, "them thất bại ");

        }
        LoadTableSP();

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        SanPham sp = new SanPham();
        int index = tblSanPham.getSelectedRow();
        String id = tblSanPham.getValueAt(index, 0).toString();
        sp.setId(id);
        sp.setMa(txtMa.getText());
        sp.setTen(txtTen.getText());
        sp.setTrangThai(1);
        if (sanPhamService.update(sp)) {
            JOptionPane.showMessageDialog(this, "sua thanh coong");
            LoadTableSP();
        }

    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        SanPham sp = new SanPham();
        int index = tblSanPham.getSelectedRow();
        String id = tblSanPham.getValueAt(index, 0).toString();
        sp.setId(id);
        if (sanPhamService.delete(sp)) {
            JOptionPane.showMessageDialog(this, "Xoa thanh cong");
            LoadTableSP();
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int index = tblSanPham.getSelectedRow();
        txtID.setText(tblSanPham.getValueAt(index, 0).toString());
        txtMa.setText(tblSanPham.getValueAt(index, 1).toString());
        txtTen.setText(tblSanPham.getValueAt(index, 2).toString());
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void tblNguonGocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNguonGocMouseClicked
        int index = tblNguonGoc.getSelectedRow();

        txtIDNguonGoc.setText(tblNguonGoc.getValueAt(index, 0).toString());
        txtMaNguonGoc.setText(tblNguonGoc.getValueAt(index, 1).toString());
        txtQuocGia.setText(tblNguonGoc.getValueAt(index, 2).toString());        // TODO add your handling code here:
    }//GEN-LAST:event_tblNguonGocMouseClicked

    private void btnThemLoaiSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLoaiSpActionPerformed
        LoaiSP lsp = new LoaiSP();
        lsp.setMa(txtMaLoaiSp.getText());
        lsp.setTenDongSP(txtTenDongSp.getText());
        lsp.setTrangThai(1);
        if (loaiSanPhamService.add(lsp)) {
            JOptionPane.showMessageDialog(this, "them thanh coong");
            LoadTableLSP();

        }
    }//GEN-LAST:event_btnThemLoaiSpActionPerformed

    private void btnThemNGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNGActionPerformed
        NguonGoc ng = new NguonGoc();
        ng.setMa(txtMaNguonGoc.getText());
        ng.setQuocGia(txtQuocGia.getText());
        ng.setTrangThai(1);
        if (nguonGocService.add(ng)) {
            JOptionPane.showMessageDialog(this, "them thanh coong");
            LoadTableNg();
        }

    }//GEN-LAST:event_btnThemNGActionPerformed

    private void btnThem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem2ActionPerformed
        DonViTinh dvt = new DonViTinh();
        dvt.setMa(TXTMADVT.getText());
        dvt.setDonViTinh(TXTDVT.getText());
        dvt.setTrangThai(1);
        if (donViTinhService.add(dvt)) {
            JOptionPane.showMessageDialog(this, "them thanh coong");
            LoadTablelDVT();

        }
    }//GEN-LAST:event_btnThem2ActionPerformed

    private void TBDONVITINHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBDONVITINHMouseClicked
        int index = TBDONVITINH.getSelectedRow();
        TXTIDDVT.setText(TBDONVITINH.getValueAt(index, 0).toString());
        TXTMADVT.setText(TBDONVITINH.getValueAt(index, 1).toString());
        TXTDVT.setText(TBDONVITINH.getValueAt(index, 2).toString());
    }//GEN-LAST:event_TBDONVITINHMouseClicked

    private void btnXoa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa2ActionPerformed
        DonViTinh dvt = new DonViTinh();
        int index = TBDONVITINH.getSelectedRow();
        String id = TBDONVITINH.getValueAt(index, 0).toString();
        dvt.setId(id);
        if (donViTinhService.delete(dvt)) {
            JOptionPane.showMessageDialog(this, "Xoa thanh cong");
            LoadTablelDVT();
        }
    }//GEN-LAST:event_btnXoa2ActionPerformed

    private void btnSua2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua2ActionPerformed
        DonViTinh dvt = new DonViTinh();
        int index = TBDONVITINH.getSelectedRow();
        String id = TBDONVITINH.getValueAt(index, 0).toString();
        dvt.setId(id);
        dvt.setMa(TXTMADVT.getText());
        dvt.setDonViTinh(TXTDVT.getText());
        dvt.setTrangThai(1);
        if (donViTinhService.update(dvt)) {
            JOptionPane.showMessageDialog(this, "update thanh cong");
            LoadTablelDVT();
        }
    }//GEN-LAST:event_btnSua2ActionPerformed

    private void btnXoaNGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNGActionPerformed
        NguonGoc ng = new NguonGoc();
        int index = tblNguonGoc.getSelectedRow();
        String id = tblNguonGoc.getValueAt(index, 0).toString();
        ng.setId(id);
        if (nguonGocService.delete(ng)) {

            JOptionPane.showMessageDialog(this, "Xoa thanh cong");
            LoadTableNg();
        }


    }//GEN-LAST:event_btnXoaNGActionPerformed

    private void btnSuaNGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNGActionPerformed
        NguonGoc ng = new NguonGoc();
        int index = tblNguonGoc.getSelectedRow();
        String id = tblNguonGoc.getValueAt(index, 0).toString();
        ng.setId(id);
        ng.setMa(txtMaNguonGoc.getText());
        ng.setQuocGia(txtQuocGia.getText());
        ng.setTrangThai(1);
        if (nguonGocService.update(ng)) {
            JOptionPane.showMessageDialog(this, "update thanh cong");
            LoadTableNg();

        }
    }//GEN-LAST:event_btnSuaNGActionPerformed

    private void tblLoaiSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLoaiSanPhamMouseClicked
        int index = tblLoaiSanPham.getSelectedRow();

        txtIDLoaiSp.setText(tblLoaiSanPham.getValueAt(index, 0).toString());
        txtMaLoaiSp.setText(tblLoaiSanPham.getValueAt(index, 1).toString());
        txtTenDongSp.setText(tblLoaiSanPham.getValueAt(index, 2).toString());
    }//GEN-LAST:event_tblLoaiSanPhamMouseClicked

    private void btnSuaLoaiSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaLoaiSpActionPerformed
        LoaiSP lsp = new LoaiSP();
        int index = tblLoaiSanPham.getSelectedRow();
        String id = tblLoaiSanPham.getValueAt(index, 0).toString();
        lsp.setId(id);
        lsp.setMa(txtMaLoaiSp.getText());
        lsp.setTenDongSP(txtTenDongSp.getText());
        lsp.setTrangThai(1);
        if (loaiSanPhamService.update(lsp)) {
            JOptionPane.showMessageDialog(this, "sua thanh cong");

            LoadTableLSP();

        }

    }//GEN-LAST:event_btnSuaLoaiSpActionPerformed

    private void btnXoaLoaiSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaLoaiSpActionPerformed
        LoaiSP lsp = new LoaiSP();
        int index = tblLoaiSanPham.getSelectedRow();
        String id = tblLoaiSanPham.getValueAt(index, 0).toString();
        lsp.setId(id);

        if (loaiSanPhamService.delete(lsp)) {
            JOptionPane.showMessageDialog(this, "Xoa thanh cong");

            LoadTableLSP();
        }
    }//GEN-LAST:event_btnXoaLoaiSpActionPerformed

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        // TODO add your handling code here:
        DongGo dg = new DongGo();
        dg.setMa(txtMaDongGo.getText());
        dg.setTenLoaiGo(txtTenDongGo.getText());
        dg.setTrangThai(1);
        if (dongGoService.them(dg)) {
            JOptionPane.showMessageDialog(this, "them thanh coong");
            LoadTableDG();

        }
    }//GEN-LAST:event_btnThem1ActionPerformed

    private void tblDongGoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDongGoMouseClicked
        int index = tblDongGo.getSelectedRow();

        txtIDDongGo.setText(tblDongGo.getValueAt(index, 0).toString());
        txtMaDongGo.setText(tblDongGo.getValueAt(index, 1).toString());
        txtTenDongGo.setText(tblDongGo.getValueAt(index, 2).toString());
    }//GEN-LAST:event_tblDongGoMouseClicked

    private void btnSua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua1ActionPerformed
        DongGo dg = new DongGo();
        int index = tblDongGo.getSelectedRow();
        String id = tblDongGo.getValueAt(index, 0).toString();
        dg.setId(id);
        dg.setMa(txtMaDongGo.getText());
        dg.setTenLoaiGo(txtTenDongGo.getText());
        dg.setTrangThai(1);
        if (dongGoService.sua(dg)) {

            JOptionPane.showMessageDialog(this, "sửa thanh công");
            LoadTableDG();
        }

    }//GEN-LAST:event_btnSua1ActionPerformed

    private void btnXoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa1ActionPerformed
        DongGo dg = new DongGo();
        int index = tblDongGo.getSelectedRow();
        String id = tblDongGo.getValueAt(index, 0).toString();
        dg.setId(id);

        if (dongGoService.xoa(dg)) {
            JOptionPane.showMessageDialog(this, "xóa thanh công");
            LoadTableDG();
        }
    }//GEN-LAST:event_btnXoa1ActionPerformed

    private void tbNhaSanXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNhaSanXuatMouseClicked
        int index = tbNhaSanXuat.getSelectedRow();

        txtid.setText(tbNhaSanXuat.getValueAt(index, 0).toString());
        txtma.setText(tbNhaSanXuat.getValueAt(index, 1).toString());
        txtten.setText(tbNhaSanXuat.getValueAt(index, 2).toString());
        txtdiachi.setText(tbNhaSanXuat.getValueAt(index, 3).toString());
        txtdienthoai.setText(tbNhaSanXuat.getValueAt(index, 4).toString());
    }//GEN-LAST:event_tbNhaSanXuatMouseClicked

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        NhaCungCap nc = new NhaCungCap();
        nc.setMa(txtma.getText());
        nc.setTenNCC(txtten.getText());
        nc.setDiaChi(txtdiachi.getText());
        nc.setSdt(txtdienthoai.getText());
        nc.setTrangThai(1);

        if (nhaCungCapService.them(nc)) {
            JOptionPane.showMessageDialog(this, "them thanh công");
            LoadTableNSX();
        }
    }//GEN-LAST:event_btnaddActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
        NhaCungCap nc = new NhaCungCap();
        int index = tbNhaSanXuat.getSelectedRow();
        String id = tbNhaSanXuat.getValueAt(index, 0).toString();
        nc.setId(id);
        nc.setMa(txtma.getText());
        nc.setTenNCC(txtten.getText());
        nc.setDiaChi(txtdiachi.getText());
        nc.setSdt(txtdienthoai.getText());
        nc.setTrangThai(1);

        if (nhaCungCapService.sua(nc)) {
            JOptionPane.showMessageDialog(this, "sua thanh công");
            LoadTableNSX();
        }
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        NhaCungCap nc = new NhaCungCap();
        int index = tbNhaSanXuat.getSelectedRow();
        String id = tbNhaSanXuat.getValueAt(index, 0).toString();
        nc.setId(id);
        if (nhaCungCapService.xoa(nc)) {
            JOptionPane.showMessageDialog(this, "xóa thanh công");
            LoadTableNSX();
        }
    }//GEN-LAST:event_btndeleteActionPerformed

    private void cbbNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNhaCungCapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbNhaCungCapActionPerformed

    private void btnThemSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPCTActionPerformed
        SPCT spct = getSpct();
        String mess = "Thêm " + this.spctSv.addOrSave(spct);
        JOptionPane.showMessageDialog(this, mess);
        loadTableSPCT();
    }//GEN-LAST:event_btnThemSPCTActionPerformed

    private void btnSuaSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSPCTActionPerformed
        SPCT spct = getSpct();
        UUID id = UUID.fromString(lbIdSPCT.getText());
        spct.setId(id);
        String mess = "Sửa " + this.spctSv.addOrSave(spct);
        JOptionPane.showMessageDialog(this, mess);
        loadTableSPCT();
    }//GEN-LAST:event_btnSuaSPCTActionPerformed

    private void btnXoaSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPCTActionPerformed
        SPCT spct = getSpct();
        UUID id = UUID.fromString(lbIdSPCT.getText());
        spct.setId(id);
        String mess = "Xóa " + this.spctSv.delete(spct);
        JOptionPane.showMessageDialog(this, mess);
        loadTableSPCT();
    }//GEN-LAST:event_btnXoaSPCTActionPerformed

    private void tbSPCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSPCTMouseClicked
        int index = tbSPCT.getSelectedRow();
        fillDataSPCT(index);
    }//GEN-LAST:event_tbSPCTMouseClicked

    private void txtTimKiemSPCTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemSPCTFocusGained
        if (txtTimKiemSPCT.getText().equals("Tìm kiếm...")) {
            txtTimKiemSPCT.setText("");
            txtTimKiemSPCT.setForeground(new Color(0, 0, 0));
            txtTimKiemSPCT.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        }
    }//GEN-LAST:event_txtTimKiemSPCTFocusGained

    private void txtTimKiemSPCTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemSPCTFocusLost
        if (txtTimKiemSPCT.getText().equals("")) {
            txtTimKiemSPCT.setText("Tìm kiếm...");
            txtTimKiemSPCT.setForeground(new Color(153, 153, 153));
            txtTimKiemSPCT.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        }
    }//GEN-LAST:event_txtTimKiemSPCTFocusLost

    private void txtTimKiemSPCTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemSPCTKeyReleased
        model = (DefaultTableModel) tbSPCT.getModel();
        String search = txtTimKiemSPCT.getText();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        tbSPCT.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + search));
    }//GEN-LAST:event_txtTimKiemSPCTKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            //  java.util.logging.Logger.getLogger(SanPhamViews.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            //    java.util.logging.Logger.getLogger(SanPhamViews.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            //  java.util.logging.Logger.getLogger(SanPhamViews.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            //  java.util.logging.Logger.getLogger(SanPhamViews.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SanPhamViews().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TBDONVITINH;
    private javax.swing.JTextField TXTDVT;
    private javax.swing.JTextField TXTIDDVT;
    private javax.swing.JTextField TXTMADVT;
    private javax.swing.JTextField TXTTIMKIEMDVT;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSua1;
    private javax.swing.JButton btnSua2;
    private javax.swing.JButton btnSuaLoaiSp;
    private javax.swing.JButton btnSuaNG;
    private javax.swing.JButton btnSuaSPCT;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThem1;
    private javax.swing.JButton btnThem2;
    private javax.swing.JButton btnThemLoaiSp;
    private javax.swing.JButton btnThemNG;
    private javax.swing.JButton btnThemSPCT;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTimKiem1;
    private javax.swing.JButton btnTimKiemDongGo;
    private javax.swing.JButton btnTimKiemNguonGoc;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoa1;
    private javax.swing.JButton btnXoa2;
    private javax.swing.JButton btnXoaLoaiSp;
    private javax.swing.JButton btnXoaNG;
    private javax.swing.JButton btnXoaSPCT;
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btntimkiem;
    private javax.swing.JButton btntimkiemSanPham;
    private javax.swing.JButton btnupdate;
    private javax.swing.JComboBox<String> cbbDonViTinh;
    private javax.swing.JComboBox<String> cbbDongGo;
    private javax.swing.JComboBox<String> cbbLoaiSP;
    private javax.swing.JComboBox<String> cbbNguonGoc;
    private javax.swing.JComboBox<String> cbbNhaCungCap;
    private javax.swing.JComboBox<String> cbbSanPham;
    private javax.swing.JComboBox<String> cbbTrangThaiSPCT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbIdSPCT;
    private javax.swing.JTable tbNhaSanXuat;
    private javax.swing.JTable tbSPCT;
    private javax.swing.JTable tblDongGo;
    private javax.swing.JTable tblLoaiSanPham;
    private javax.swing.JTable tblNguonGoc;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGiaBanSPCT;
    private javax.swing.JTextField txtGiaNhapSPCT;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtIDDongGo;
    private javax.swing.JTextField txtIDLoaiSp;
    private javax.swing.JTextField txtIDNguonGoc;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMaDongGo;
    private javax.swing.JTextField txtMaLoaiSp;
    private javax.swing.JTextField txtMaNguonGoc;
    private javax.swing.JTextArea txtMoTaSPCT;
    private javax.swing.JTextField txtQuocGia;
    private javax.swing.JTextField txtSLgSPCT;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTenDongGo;
    private javax.swing.JTextField txtTenDongSp;
    private javax.swing.JTextField txtTenSPCT;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTimKiemDongGo;
    private javax.swing.JTextField txtTimKiemNG;
    private javax.swing.JTextField txtTimKiemSPCT;
    private javax.swing.JTextField txtTimkiemSanPham;
    private javax.swing.JTextField txtdiachi;
    private javax.swing.JTextField txtdienthoai;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtma;
    private javax.swing.JTextField txtten;
    private javax.swing.JTextField txttimkiem;
    // End of variables declaration//GEN-END:variables
}
