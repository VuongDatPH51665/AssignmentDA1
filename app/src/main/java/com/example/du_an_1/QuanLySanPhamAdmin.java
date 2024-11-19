package com.example.du_an_1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.du_an_1.Database.SanPhamDAO;
import com.example.du_an_1.Model.SanPham;

import java.util.ArrayList;
import java.util.List;
public class QuanLySanPhamAdmin extends AppCompatActivity {

    private EditText etProductName, etProductPrice, etProductSize, etProductQuantity, etProductDescription;
    private ListView lvProducts;
    private SanPhamDAO sanPhamDAO;
    private List<SanPham> sanPhamList = new ArrayList<>();
    private ArrayAdapter<SanPham> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_san_pham_admin);

        etProductName = findViewById(R.id.etProductName);
        etProductPrice = findViewById(R.id.etProductPrice);
        etProductSize = findViewById(R.id.etProductSize);
        etProductQuantity = findViewById(R.id.etProductQuantity);
        etProductDescription = findViewById(R.id.etProductDescription);
        lvProducts = findViewById(R.id.lvProducts);
        Button btnAddProduct = findViewById(R.id.btnAddProduct);

        sanPhamDAO = new SanPhamDAO(this);
        loadProducts();

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });

        lvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Xử lý khi người dùng chọn sản phẩm trong danh sách
            }
        });
    }

    private void loadProducts() {
        sanPhamList.clear();
        sanPhamList.addAll(sanPhamDAO.getAll());
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sanPhamList);
        lvProducts.setAdapter(adapter);
    }

    private void addProduct() {
        SanPham sanPham = new SanPham();
        sanPham.setTen(etProductName.getText().toString());
        sanPham.setGia(Double.parseDouble(etProductPrice.getText().toString()));
        sanPham.setSize(etProductSize.getText().toString());
        sanPham.setSoLuong(Integer.parseInt(etProductQuantity.getText().toString()));
        sanPham.setMoTa(etProductDescription.getText().toString());

        sanPhamDAO.insert(sanPham);
        loadProducts(); // Cập nhật danh sách sản phẩm sau khi thêm
    }
}