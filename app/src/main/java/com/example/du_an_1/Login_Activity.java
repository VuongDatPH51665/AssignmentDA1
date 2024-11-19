package com.example.du_an_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.du_an_1.Database.TaiKhoanDAO;

public class Login_Activity extends AppCompatActivity {

    private EditText edtUser, edtPassword;
    private Button loginButton;
    private TaiKhoanDAO taiKhoanDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Ánh xạ các thành phần giao diện
        edtUser = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtpass);
        loginButton = findViewById(R.id.btnLogin);

        // Khởi tạo TaiKhoanDAO
        taiKhoanDAO = new TaiKhoanDAO(this);

        // Xử lý sự kiện nút đăng nhập
        loginButton.setOnClickListener(v -> {
            String username = edtUser.getText().toString().trim();
            String matKhau = edtPassword.getText().toString().trim();

            // Kiểm tra nhập liệu
            if (username.isEmpty() || matKhau.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            // Đăng nhập
            String vaiTro = taiKhoanDAO.dangNhap(username, matKhau);
            if (vaiTro == null) {
                Toast.makeText(this, "Email hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
            } else {
                // Đăng nhập thành công
                if (vaiTro.equals("admin")) {
                    Intent intent = new Intent(this,QuanLySanPhamAdmin .class);
                    intent.putExtra("email", username); // Truyền email cho màn hình tiếp theo
                    startActivity(intent);
                    finish();
                } else if (vaiTro.equals("nguoi_mua")) {
                    Intent intent = new Intent(this, Manhinhchao_Activity.class);
                    intent.putExtra("email", username); // Truyền email cho màn hình tiếp theo
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
