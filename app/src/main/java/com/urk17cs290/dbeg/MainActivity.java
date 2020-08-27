package com.urk17cs290.dbeg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db= new DbHelper(MainActivity.this,"college",null,1);

        final EditText name = findViewById(R.id.name);
        final EditText num = findViewById(R.id.num);
        Button save = findViewById(R.id.save);
        Button viewAll = findViewById(R.id.view_all);
        final TextView outView = findViewById(R.id.outView);

    save.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            String userName = name.getText().toString();
            int mobile = Integer.parseInt(num.getText().toString());

            long status = db.saveData(userName, mobile);
            if (status > 0) {
              Toast.makeText(getApplicationContext(), "Added to db", Toast.LENGTH_SHORT).show();
              Log.d("TAG", "no of users : "+status);
            }
          }
        });
    viewAll.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
            String output = db.viewAll();
            outView.setText(output);
            Log.d("TAG", "output string = "+output);
         }
        });
    }
}