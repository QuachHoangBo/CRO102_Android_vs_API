package fpoly.edu.boqhpd0840.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import fpoly.edu.boqhpd0840.R;

public class OTPPhoneNumber extends AppCompatActivity {
    EditText PhoneNumber,OTP;
    Button GetOTP,LoginOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpphone_number);

        PhoneNumber phoneNumber = findViewById(R.id.edtOTPPhoneNumber);
        OTP otp = findViewById(R.id.edtOTP);
    }
}