package com.example.du_an_1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QuanLyBanGiay.db";
    private static final int DATABASE_VERSION = 3;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

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
                "ten TEXT NOT NULL, " +
                "gia REAL NOT NULL, " +
                "size TEXT NOT NULL, " +
                "so_luong INTEGER NOT NULL, " +
                "mo_ta TEXT" +
                ");";
        db.execSQL(createSanPhamTable);
        // Tạo các bảng khác như SanPham, DonHang...

        // Mã hóa mật khẩu admin trước khi lưu vào cơ sở dữ liệu
        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO(null);  // Cần truyền context vào nếu cần
        String hashedPassword = taiKhoanDAO.hashPassword("admin123");

        // Thêm tài khoản admin với mật khẩu đã mã hóa
        String insertAdminAccount = "INSERT INTO TaiKhoan (ten, email, so_dien_thoai, mat_khau, vai_tro) " +
                "VALUES ('Admin', 'admin@example.com', '0123456789', ?, 'admin');";
        db.execSQL(insertAdminAccount, new Object[]{hashedPassword});
    }

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
