package com.emarkova.session16;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    DBManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = new DBManager(MainActivity.this);
    }

    public void onCompute(View view) {
        Calculator calculator = new Calculator();
        EditText editText = (EditText)findViewById(R.id.editText);
        String result = calculator.compute(editText.getText().toString());
        manager.addRow(result);
        editText.setText(result);
    }
}
