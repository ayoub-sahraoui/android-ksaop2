package com.example.ksaop2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.ksaop2.service.SoapClient;

public class DeleteAccountActivity extends AppCompatActivity {
    private EditText accountIdInput;
    private TextView resultTextView;
    private SoapClient soapClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Delete Account");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        soapClient = new SoapClient();
        accountIdInput = findViewById(R.id.accountIdInput);
        resultTextView = findViewById(R.id.resultTextView);
        Button deleteAccountButton = findViewById(R.id.deleteAccountButton);

        deleteAccountButton.setOnClickListener(v -> {
            try {
                long id = Long.parseLong(accountIdInput.getText().toString());
                showConfirmationDialog(id);
            } catch (NumberFormatException e) {
                resultTextView.setText("Please enter a valid account ID");
            }
        });
    }

    private void showConfirmationDialog(long id) {
        new AlertDialog.Builder(this)
            .setTitle("Confirm Deletion")
            .setMessage("Are you sure you want to delete account " + id + "?")
            .setPositiveButton("Delete", (dialog, which) -> deleteAccount(id))
            .setNegativeButton("Cancel", null)
            .show();
    }

    private void deleteAccount(long id) {
        soapClient.deleteCompte(id, new SoapClient.SoapResponse<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                runOnUiThread(() -> {
                    if (result) {
                        resultTextView.setText("Account " + id + " deleted successfully");
                        accountIdInput.setText("");
                    } else {
                        resultTextView.setText("Failed to delete account " + id);
                    }
                });
            }

            @Override
            public void onError(Exception exception) {
                runOnUiThread(() -> resultTextView.setText("Error: " + exception.getMessage()));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
