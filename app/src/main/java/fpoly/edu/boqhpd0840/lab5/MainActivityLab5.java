package fpoly.edu.boqhpd0840.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fpoly.edu.boqhpd0840.Lab7.LoadingPageActivity;
import fpoly.edu.boqhpd0840.R;
import fpoly.edu.boqhpd0840.lab6.RegisterActivity;

public class MainActivityLab5 extends AppCompatActivity {

    Button btnDistributor,btnRegister,btnLoadingPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activitylab5);

        btnDistributor = findViewById(R.id.btn_distributor);
        btnRegister = findViewById(R.id.btn_Register);
        btnLoadingPage = findViewById(R.id.btnLoadingPage);

        btnDistributor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityLab5.this,DistributorActivity.class);
                startActivity(intent);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityLab5.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnLoadingPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityLab5.this, LoadingPageActivity.class);
                startActivity(intent);
            }
        });
    }

}