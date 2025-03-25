package com.example.slip9q2;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class ShowCompaniesActivity extends AppCompatActivity {
    private TextView companiesList;
    private DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_companies);
        companiesList = findViewById(R.id.companiesList);
        dbHelper = new DatabaseHelper(this);
        displayCompanies();
    }
    private void displayCompanies() {
        Cursor cursor = dbHelper.getAllCompanies();
        StringBuilder data = new StringBuilder();
        if (cursor.getCount() == 0) {
            data.append("No companies found");
        } else {
            while (cursor.moveToNext()) {
                data.append("ID: ").append(cursor.getInt(0)).append("\n");
                data.append("Name: ").append(cursor.getString(1)).append("\n");
                data.append("Address: ").append(cursor.getString(2)).append("\n");
                data.append("Phone: ").append(cursor.getString(3)).append("\n\n");
            }
        }
        companiesList.setText(data.toString());
    }
}
