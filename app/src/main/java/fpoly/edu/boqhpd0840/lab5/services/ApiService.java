package fpoly.edu.boqhpd0840.lab5.services;

import java.util.ArrayList;

import fpoly.edu.boqhpd0840.lab5.models.Distributor;
import fpoly.edu.boqhpd0840.lab5.models.Response;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    String BASE_URL = "http://192.168.0.111:3000/";
    @GET("distributors/list")
    Call<Response<ArrayList<Distributor>>> getListDistributor();
}
