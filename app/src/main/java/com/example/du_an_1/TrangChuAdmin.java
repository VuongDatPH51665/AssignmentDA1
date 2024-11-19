package com.example.du_an_1;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

public class TrangChuAdmin extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private FrameLayout frameLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu_admin);

        // Áp dụng WindowInsets để hỗ trợ Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ View
        toolbar = findViewById(R.id.toolbar);
        frameLayout = findViewById(R.id.content_frame);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        // Kiểm tra null
        if (drawerLayout == null || navigationView == null || toolbar == null) {
            Log.e("TrangChuAdmin", "Một hoặc nhiều View bị null!");
            return;
        }

        // Thiết lập Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Cài đặt Drawer Toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Navigation Listener
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if (item.getItemId() == R.id.nav_qlsp) {
                    toolbar.setTitle("Quản lý sản phẩm");
                    fragment = QuanLySanPhamFragment.newInstance();
                } else if (item.getItemId() == R.id.nav_orders) {
                    toolbar.setTitle("Theo dõi đơn hàng");
                    fragment = TheoDoiDonHangFragment.newInstance();
                } else if (item.getItemId() == R.id.nav_revenue) {
                    toolbar.setTitle("Tổng doanh thu");
                    fragment = TongDoanhThuFragment.newInstance();
                } else if (item.getItemId() == R.id.nav_statistics) {
                    toolbar.setTitle("Thống kê sản phẩm bán chạy");
                    fragment = ThongKeSanPhamFragment.newInstance();
                }

                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, fragment)
                            .commit();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }

        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
