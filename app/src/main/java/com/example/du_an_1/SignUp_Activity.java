//package com.example.du_an_1;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.du_an_1.Database.TaiKhoanDAO;
//
//public class SignUp_Activity extends AppCompatActivity {
//    Button btnRegister;
//    EditText edtName, edtEmail, edtPassword, edtConfirmPassword;
//    TaiKhoanDAO taiKhoanDAO;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_up);
//
//        // Ánh xạ các thành phần giao diện
//        edtEmail = findViewById(R.id.edtUser); // TextInputEditText cho email
//        edtPassword = findViewById(R.id.edtpass); // TextInputEditText cho mật khẩu
//        edtConfirmPassword = findViewById(R.id.edtRePass); // TextInputEditText xác nhận mật khẩu
//        btnRegister = findViewById(R.id.Register);
//
//        // Khởi tạo DAO
//        taiKhoanDAO = new TaiKhoanDAO(this);
//
//        // Đăng ký sự kiện click
//        btnRegister.setOnClickListener(v -> {
//            String email = edtEmail.getText().toString().trim();
//            String password = edtPassword.getText().toString().trim();
//            String confirmPassword = edtConfirmPassword.getText().toString().trim();
//
//            // Kiểm tra thông tin nhập
//            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
//                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            if (!password.equals(confirmPassword)) {
//                Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            // Đăng ký tài khoản
//            long userId = taiKhoanDAO.dangKy(name, email, password);
//
//            if (userId != -1) {
//                // Lưu thông tin người dùng vào SharedPreferences
//                getSharedPreferences("USER_PREFS", MODE_PRIVATE)
//                        .edit()
//                        .putLong("user_id", userId) // Lưu ID người dùng
//                        .putString("user_name", name)
//                        .putString("user_email", email)
//                        .apply();
//
//                Toast.makeText(this, "Đăng ký thành công! Chào mừng bạn, " + name, Toast.LENGTH_SHORT).show();
//
//                // Chuyển đến màn hình người mua
//                Intent intent = new Intent(this, Manhinhchao_Activity.class);
//                startActivity(intent);
//                finish();
//            } else {
//                Toast.makeText(this, "Email đã tồn tại. Vui lòng sử dụng email khác.", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}
