package com.example.ksaop2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.ksaop2.service.SoapClient;

import org.ksoap2.serialization.SoapObject;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private EditText accountIdInput, soldeInput, typeInput;
    private TextView resultTextView;
    private SoapClient soapClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        soapClient = new SoapClient();
        accountIdInput = findViewById(R.id.accountIdInput);
        soldeInput = findViewById(R.id.soldeInput);
        typeInput = findViewById(R.id.typeInput);
        resultTextView = findViewById(R.id.resultTextView);

        Button getAccountButton = findViewById(R.id.getAccountButton);
        Button getAllAccountsButton = findViewById(R.id.getAllAccountsButton);
        Button deleteAccountButton = findViewById(R.id.deleteAccountButton);
        Button addAccountButton = findViewById(R.id.addAccountButton);

        getAccountButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GetAccountActivity.class);
            startActivity(intent);
        });

        getAllAccountsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AllAccountsActivity.class);
            startActivity(intent);
        });

        deleteAccountButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DeleteAccountActivity.class);
            startActivity(intent);
        });

        addAccountButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddAccountActivity.class);
            startActivity(intent);
        });
    }
}
