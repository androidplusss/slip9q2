package com.example.slip9q2;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    private EditText name, address, phone;
    private Button insertButton, showButton;
    private DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        insertButton = findViewById(R.id.insertButton);
        showButton = findViewById(R.id.showButton);
        dbHelper = new DatabaseHelper(this);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String companyName = name.getText().toString();
                String companyAddress = address.getText().toString();
                String companyPhone = phone.getText().toString();
                if (companyName.isEmpty() || companyAddress.isEmpty() || companyPhone.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isInserted = dbHelper.insertCompany(companyName, companyAddress, companyPhone);
                    if (isInserted) {
                        Toast.makeText(MainActivity.this, "Company inserted successfully", Toast.LENGTH_SHORT).show();
                        name.setText("");
                        address.setText("");
                        phone.setText("");
                    } else {
                        Toast.makeText(MainActivity.this, "Failed to insert company", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowCompaniesActivity.class);
                startActivity(intent);
            }
        });
    }
}
