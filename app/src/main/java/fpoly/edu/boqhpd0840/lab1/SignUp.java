package fpoly.edu.boqhpd0840.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fpoly.edu.boqhpd0840.R;

public class SignUp extends AppCompatActivity {

    Button btnGoLogin,goBack;
    EditText edtEmail,edtPassword,edtPassword2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        goBack = findViewById(R.id.btnGoback);
        edtEmail = findViewById(R.id.edtEmailSighUp);
        edtPassword = findViewById(R.id.edtPassSignUp);
        edtPassword2 = findViewById(R.id.edtPassSignUp2);

        btnGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });
    }
}