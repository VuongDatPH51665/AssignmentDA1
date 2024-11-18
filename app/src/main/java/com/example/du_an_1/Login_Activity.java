package com.example.du_an_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.du_an_1.Database.TaiKhoanDAO;

public class Login_Activity extends AppCompatActivity {

    private EditText edtEmail, edtPassword;
    private Button loginButton;
    private TaiKhoanDAO taiKhoanDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Ánh xạ các thành phần giao diện
        edtEmail = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtpass);
        loginButton = findViewById(R.id.btnLogin);

        // Khởi tạo TaiKhoanDAO
        taiKhoanDAO = new TaiKhoanDAO(this);

        // Xử lý sự kiện nút đăng nhập
        loginButton.setOnClickListener(v -> {
            String email = edtEmail.getText().toString().trim();
            String matKhau = edtPassword.getText().toString().trim();

            if (email.isEmpty() || matKhau.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                // Kiểm tra tài khoản và mật khẩu đã được mã hóa
                String vaiTro = taiKhoanDAO.dangNhap(email, matKhau);
                if (vaiTro == null) {
                    Toast.makeText(this, "Email hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                } else if (vaiTro.equals("admin")) {
                    // Nếu vai trò là admin, chuyển đến màn hình admin
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else if (vaiTro.equals("nguoi_mua")) {
                    // Nếu vai trò là người mua, chuyển đến màn hình người mua
                    Intent intent = new Intent(this, Manhinhchao_Activity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
