package com.example.du_an_1.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TaiKhoanDAO {
    private DbHelper dbHelper;

    public TaiKhoanDAO(Context context) {
        dbHelper = new DbHelper(context);
    }
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            return Base64.encodeToString(hash, Base64.DEFAULT);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String dangNhap(String email, String matKhau) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT vai_tro, mat_khau FROM TaiKhoan WHERE email = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});

        if (cursor.moveToFirst()) {
            String hashedPassword = cursor.getString(cursor.getColumnIndex("mat_khau"));
            String vaiTro = cursor.getString(cursor.getColumnIndex("vai_tro"));

            cursor.close();

            // So sánh mật khẩu đã mã hóa
            if (hashedPassword.equals(hashPassword(matKhau))) {
                return vaiTro;  // Trả về vai trò nếu mật khẩu đúng
            }
        }

        cursor.close();
        return null;  // Trả về null nếu không tìm thấy tài khoản hoặc mật khẩu sai
    }
    public long dangKy(String ten, String email, String matKhau) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "INSERT INTO TaiKhoan (ten, email, mat_khau, vai_tro) VALUES (?, ?, ?, 'nguoi_mua')";
        try {
            db.execSQL(query, new Object[]{ten, email, matKhau});
            Cursor cursor = db.rawQuery("SELECT last_insert_rowid()", null); // Lấy ID vừa tạo
            if (cursor.moveToFirst()) {
                long userId = cursor.getLong(0);
                cursor.close();
                return userId; // Trả về ID
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Nếu thất bại
    }

}
