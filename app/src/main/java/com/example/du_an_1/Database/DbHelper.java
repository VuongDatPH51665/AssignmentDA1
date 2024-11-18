package com.example.du_an_1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    // Tên database và phiên bản
    private static final String DATABASE_NAME = "QuanLyBanGiay.db";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Tạo các bảng trong cơ sở dữ liệu
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng TaiKhoan
        String createTaiKhoanTable = "CREATE TABLE TaiKhoan (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ten TEXT NOT NULL, " +
                "email TEXT NOT NULL UNIQUE, " +
                "so_dien_thoai TEXT NOT NULL, " +
                "mat_khau TEXT NOT NULL, " +
                "vai_tro TEXT NOT NULL CHECK (vai_tro IN ('admin', 'nguoi_mua'))" +
                ");";
        db.execSQL(createTaiKhoanTable);

        // Tạo bảng SanPham
        String createSanPhamTable = "CREATE TABLE SanPham (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ten_san_pham TEXT NOT NULL, " +
                "mo_ta TEXT, " +
                "gia REAL NOT NULL, " +
                "so_luong INTEGER NOT NULL, " +
                "hinh_anh TEXT" +
                ");";
        db.execSQL(createSanPhamTable);

        // Tạo bảng DonHang
        String createDonHangTable = "CREATE TABLE DonHang (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "khach_hang_id INTEGER NOT NULL, " +
                "ngay_dat DATE NOT NULL, " +
                "tong_tien REAL NOT NULL, " +
                "trang_thai TEXT NOT NULL, " +
                "FOREIGN KEY (khach_hang_id) REFERENCES TaiKhoan(id)" +
                ");";
        db.execSQL(createDonHangTable);

        // Tạo bảng ChiTietDonHang
        String createChiTietDonHangTable = "CREATE TABLE ChiTietDonHang (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "don_hang_id INTEGER NOT NULL, " +
                "san_pham_id INTEGER NOT NULL, " +
                "so_luong INTEGER NOT NULL, " +
                "gia REAL NOT NULL, " +
                "FOREIGN KEY (don_hang_id) REFERENCES DonHang(id), " +
                "FOREIGN KEY (san_pham_id) REFERENCES SanPham(id)" +
                ");";
        db.execSQL(createChiTietDonHangTable);

        // Tạo bảng ThongKe
        String createThongKeTable = "CREATE TABLE ThongKe (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "san_pham_id INTEGER NOT NULL, " +
                "so_luong_ban INTEGER NOT NULL, " +
                "thang INTEGER NOT NULL, " +
                "nam INTEGER NOT NULL, " +
                "FOREIGN KEY (san_pham_id) REFERENCES SanPham(id)" +
                ");";
        db.execSQL(createThongKeTable);

        // Thêm tài khoản admin cố định
        String insertAdminAccount = "INSERT INTO TaiKhoan (ten, email, so_dien_thoai, mat_khau, vai_tro) " +
                "VALUES ('Admin', 'admin@example.com', '0123456789', 'matkhau_admin_mahoa', 'admin');";
        db.execSQL(insertAdminAccount);
    }

    // Nâng cấp cơ sở dữ liệu
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TaiKhoan");
        db.execSQL("DROP TABLE IF EXISTS SanPham");
        db.execSQL("DROP TABLE IF EXISTS DonHang");
        db.execSQL("DROP TABLE IF EXISTS ChiTietDonHang");
        db.execSQL("DROP TABLE IF EXISTS ThongKe");
        onCreate(db);
    }
}
