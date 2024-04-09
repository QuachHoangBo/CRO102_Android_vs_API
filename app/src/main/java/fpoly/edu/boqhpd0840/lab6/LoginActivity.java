package fpoly.edu.boqhpd0840.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fpoly.edu.boqhpd0840.R;
import fpoly.edu.boqhpd0840.lab5.models.Response;
import fpoly.edu.boqhpd0840.lab5.models.User;
import fpoly.edu.boqhpd0840.lab5.services.HttpRequest;
import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {
    String TAG ="//======LoginActivity=====";
    HttpRequest httpRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        httpRequest = new HttpRequest();
        Button btnLogin = findViewById(R.id.btnLoginLab6);
        EditText edUser = findViewById(R.id.edUserNameLoginLab6);
        EditText edPassword = findViewById(R.id.edPasswordLoginLab6);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUser = edUser.getText().toString();
                String strPassword = edPassword.getText().toString();
                if (strUser.isEmpty() || strPassword.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Username or Password was Wrong!", Toast.LENGTH_SHORT).show();
                }else {
                    User user = new User();
                    user.setUsername("namnv");
                    user.setPassword("123");
                    httpRequest.callAPI().checkLogin(user).enqueue(responseCheckLogin);
//                    finish();
                }
            }
        });
    }
    Callback<Response<User>> responseCheckLogin = new Callback<Response<User>>() {
        @Override
        public void onResponse(Call<Response<User>> call, retrofit2.Response<Response<User>> response) {
            if (response.isSuccessful()){
                Log.i(TAG,"//=responseCheckLogin=="+response.body().toString());
                if (response.body().getStatus()==200){
                    Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }

        @Override
        public void onFailure(Call<Response<User>> call, Throwable throwable) {
            Toast.makeText(getApplicationContext(), "Login Fail", Toast.LENGTH_SHORT).show();

            Log.i(TAG,"//=responseCheckLogin ERROR:" +throwable.getMessage());
        }
    };
}