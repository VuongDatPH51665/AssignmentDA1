package com.example.du_an_1.Database;

import com.example.du_an_1.Model.SanPham;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

    public class SanPhamDAO {
    private DbHelper dbHelper;

    public SanPhamDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    // Thêm sản phẩm
    public long insert(SanPham sanPham) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ten", sanPham.getTen());
        values.put("gia", sanPham.getGia());
        values.put("size", sanPham.getSize());
        values.put("so_luong", sanPham.getSoLuong());
        values.put("mo_ta", sanPham.getMoTa());
        long id = db.insert("SanPham", null, values);
        db.close();
        return id;
    }

    // Lấy danh sách sản phẩm
    public List<SanPham> getAll() {
        List<SanPham> sanPhamList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM SanPham", null);

        if (cursor.moveToFirst()) {
            do {
                SanPham sanPham = new SanPham();
                sanPham.setId(cursor.getInt(0));
                sanPham.setTen(cursor.getString(1));
                sanPham.setGia(cursor.getDouble(2));
                sanPham.setSize(cursor.getString(3));
                sanPham.setSoLuong(cursor.getInt(4));
                sanPham.setMoTa(cursor.getString(5));
                sanPhamList.add(sanPham);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return sanPhamList;
    }

    // Cập nhật sản phẩm
    public int update(SanPham sanPham) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ten", sanPham.getTen());
        values.put("gia", sanPham.getGia());
        values.put("size", sanPham.getSize());
        values.put("so_luong", sanPham.getSoLuong());
        values.put("mo_ta", sanPham.getMoTa());

        int rowsAffected = db.update("SanPham", values, "id = ?", new String[]{String.valueOf(sanPham.getId())});
        db.close();
        return rowsAffected;
    }

    // Xóa sản phẩm
    public void delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("SanPham", "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}