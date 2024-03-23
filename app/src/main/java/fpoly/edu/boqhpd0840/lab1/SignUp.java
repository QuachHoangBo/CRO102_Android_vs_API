package fpoly.edu.boqhpd0840.lab1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import fpoly.edu.boqhpd0840.R;

public class SignUp extends AppCompatActivity {
    Button btn_signup,btn_goback;
    EditText ed_User,ed_Pass    ;
    private FirebaseAuth auth;
    String user , pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btn_signup = findViewById(R.id.btnSignUp);
        btn_goback = findViewById(R.id.btnGoback);
        ed_User = findViewById(R.id.edtEmailSighUp);
        ed_Pass= findViewById(R.id.edtPassSignUp);
        auth = FirebaseAuth.getInstance();

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = ed_User.getText().toString();
                pass = ed_Pass.getText().toString();
                auth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener( SignUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Đăng ký thành công",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SignUp.this, Login.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(getApplicationContext(), "Đăng ký không than công",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        btn_goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });
    }
}