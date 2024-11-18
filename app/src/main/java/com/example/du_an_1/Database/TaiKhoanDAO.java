package com.example.du_an_1.Database;

import android.content.ContentValues;
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

    public String hashPassword(String password) {
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
        try (Cursor cursor = db.rawQuery(query, new String[]{email})) {
            if (cursor.moveToFirst()) {
                String hashedPassword = cursor.getString(cursor.getColumnIndexOrThrow("mat_khau"));
                String vaiTro = cursor.getString(cursor.getColumnIndexOrThrow("vai_tro"));
                if (hashedPassword.equals(hashPassword(matKhau))) {
                    return vaiTro;
                }
            }
        }
        return null;
    }

    public long dangKy(String ten, String email, String matKhau, String soDienThoai) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String hashedPassword = hashPassword(matKhau);

        ContentValues values = new ContentValues();
        values.put("ten", ten);
        values.put("email", email);
        values.put("so_dien_thoai", soDienThoai);
        values.put("mat_khau", hashedPassword);
        values.put("vai_tro", "nguoi_mua");

        try {
            return db.insertOrThrow("TaiKhoan", null, values);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
