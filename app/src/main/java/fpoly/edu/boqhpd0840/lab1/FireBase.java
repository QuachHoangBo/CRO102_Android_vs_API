package fpoly.edu.boqhpd0840.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fpoly.edu.boqhpd0840.R;

public class FireBase extends AppCompatActivity {

    Button googleAuth,phoneAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base);

        googleAuth = findViewById(R.id.btnloginWithEmail);
        phoneAuth = findViewById(R.id.btnloginWithPhoneNumer);

        googleAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FireBase.this, Login.class);
                startActivity(intent);
            }
        });

        phoneAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FireBase.this, OTPPhoneNumber.class);
                startActivity(intent);
            }
        });
    }
}