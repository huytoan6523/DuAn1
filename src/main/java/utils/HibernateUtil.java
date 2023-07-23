package utils;

import domainModels.ChucVu;
import domainModels.CuaHang;
import domainModels.DonViTinh;
import domainModels.DongGo;
import domainModels.HoaDon;
import domainModels.HoaDonChiTiet;
import domainModels.HoaDonChiTietId;
import domainModels.KhachHang;
import domainModels.KhuyenMai;
import domainModels.LoaiSP;
import domainModels.NguonGoc;
import domainModels.NhaCungCap;
import domainModels.NhanVien;
import domainModels.SPCT;
import domainModels.SanPham;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {

    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=DuAn1Hibernate");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "123456");
        properties.put(Environment.SHOW_SQL, "true");

        conf.setProperties(properties);
        conf.addAnnotatedClass(KhachHang.class);
        conf.addAnnotatedClass(KhuyenMai.class);
        conf.addAnnotatedClass(ChucVu.class);
        conf.addAnnotatedClass(CuaHang.class);
        conf.addAnnotatedClass(DonViTinh.class);
        conf.addAnnotatedClass(DongGo.class);
        conf.addAnnotatedClass(LoaiSP.class);
        conf.addAnnotatedClass(NguonGoc.class);
        conf.addAnnotatedClass(NhaCungCap.class);
        conf.addAnnotatedClass(NhanVien.class);
        conf.addAnnotatedClass(SPCT.class);
        conf.addAnnotatedClass(SanPham.class);
        conf.addAnnotatedClass(HoaDon.class);
        conf.addAnnotatedClass(HoaDonChiTiet.class);
        conf.addAnnotatedClass(HoaDonChiTietId.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static void main(String[] args) {
        System.out.println(getFACTORY());
    }
}
