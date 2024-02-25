package com.example.renthub;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity {
    private EditText newPassword, confirmPassword;
    private ImageButton cng;
    ProgressDialog pd;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        newPassword= findViewById(R.id.newPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
        cng= findViewById(R.id.confirm);
        pd= new ProgressDialog(this);
        auth= FirebaseAuth.getInstance();


        cng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser cuser= FirebaseAuth.getInstance().getCurrentUser();

                if((newPassword.getText().toString()).equals(confirmPassword.getText().toString())) {
                    if (cuser != null) {
                        pd.setMessage("Changing Password");
                        pd.show();
                        cuser.updatePassword(newPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    pd.dismiss();
                                    Toast.makeText(ChangePassword.this, "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                                    auth.signOut();
                                    finish();
                                    startActivity(new Intent(ChangePassword.this, Login.class));

                                }
                            }
                        });
                    }
                }
                else {
                    Toast.makeText(ChangePassword.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}