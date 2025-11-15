package com.example.lab9_cau1;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Button btnDraw = findViewById(R.id.btnDraw);
        EditText etCount = findViewById(R.id.etCount);
        TableLayout table = findViewById(R.id.table);

        Random rnd = new Random();

        btnDraw.setOnClickListener(v -> {
            String s = etCount.getText().toString().trim();
            if (TextUtils.isEmpty(s)) {
                etCount.setError("Enter a number");
                return;
            }
            int count;
            try {
                count = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                etCount.setError("Invalid number");
                return;
            }
            if (count <= 0) {
                etCount.setError("Number must be > 0");
                return;
            }


            table.removeAllViews();

            for (int i = 1; i <= count; i++) {
                TableRow row = new TableRow(this);
                TableLayout.LayoutParams rowParams = new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(rowParams);
                row.setPadding(8, 8, 8, 8);


                Button left = new Button(this);
                TableRow.LayoutParams lp = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                lp.setMargins(8, 8, 8, 8);
                left.setLayoutParams(lp);
                left.setPadding(16, 16, 16, 16);
                left.setGravity(Gravity.CENTER);


                Button right = new Button(this);
                TableRow.LayoutParams rp = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                rp.setMargins(8, 8, 8, 8);
                right.setLayoutParams(rp);
                right.setPadding(16, 16, 16, 16);
                right.setGravity(Gravity.CENTER);

                int value = rnd.nextInt(101);
                if (i % 2 == 1) {

                    left.setText(String.format(Locale.getDefault(), "%d", value));
                    right.setVisibility(View.INVISIBLE);
                } else {

                    right.setText(String.format(Locale.getDefault(), "%d", value));
                    left.setVisibility(View.INVISIBLE);
                }

                row.addView(left);
                row.addView(right);

                table.addView(row);
            }
        });
    }
}