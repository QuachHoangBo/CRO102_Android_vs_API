package fpoly.edu.boqhpd0840.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import fpoly.edu.boqhpd0840.R;

public class FireBase extends AppCompatActivity {

    Button googleAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base);

        googleAuth = findViewById(R.id.btnloginEmail);
    }
}