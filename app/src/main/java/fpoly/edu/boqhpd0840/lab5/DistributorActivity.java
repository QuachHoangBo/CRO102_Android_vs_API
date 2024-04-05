package fpoly.edu.boqhpd0840.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fpoly.edu.boqhpd0840.R;
import fpoly.edu.boqhpd0840.lab5.models.Distributor;
import fpoly.edu.boqhpd0840.lab5.models.Response;
import fpoly.edu.boqhpd0840.lab5.services.HttpRequest;
import retrofit2.Call;
import retrofit2.Callback;

public class DistributorActivity extends AppCompatActivity {

    List<Distributor> list;
    HttpRequest httpRequest;
    String TAG = "//===DistributorActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        httpRequest = new HttpRequest();
        setContentView(R.layout.activity_distributor);
        onResume();
    }

    @Override
    protected void onResume() {
        super.onResume();
        httpRequest.callAPI().getListDistributor().enqueue(getListDistributor);
    }

    Callback<Response<ArrayList<Distributor>>> getListDistributor = new Callback<Response<ArrayList<Distributor>>>() {
        @Override
        public void onResponse(Call<Response<ArrayList<Distributor>>> call, retrofit2.Response<Response<ArrayList<Distributor>>> response) {
            if(response.isSuccessful()){
                if (response.body().getStatus() == 200){
                    list = new ArrayList<>();
                    list = response.body().getData();
                    for (Distributor item: list){
                        Log.i(TAG,"//==="+item.toString());
                    }
                }
            }
        }

        @Override
        public void onFailure(Call<Response<ArrayList<Distributor>>> call, Throwable throwable) {
                Log.i(TAG,"//===Error="+throwable.getMessage());
        }
    };
}