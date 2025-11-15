package com.example.lab9_cau2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    EditText editId, editName, editAge;
    Button btnDelete, btnClose;
    DBHelper dbHelper;
    String empId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        editId = findViewById(R.id.editId);
        editName = findViewById(R.id.editName);
        editAge = findViewById(R.id.editAge);
        btnDelete = findViewById(R.id.btnDelete);
        btnClose = findViewById(R.id.btnClose);

        dbHelper = new DBHelper(this);

        empId = getIntent().getStringExtra("id");
        if (empId != null) {
            Employee emp = dbHelper.getEmployee(empId);
            if (emp != null) {
                editId.setText(emp.getId());
                editName.setText(emp.getName());
                editAge.setText(String.valueOf(emp.getAge()));
            }
        }

        btnDelete.setOnClickListener(v -> {
            if (empId != null) {
                boolean ok = dbHelper.deleteEmployee(empId);
                if (ok) {
                    Toast.makeText(DetailActivity.this, "Đã xóa nhân viên", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(DetailActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnClose.setOnClickListener(v -> finish());
    }
}

