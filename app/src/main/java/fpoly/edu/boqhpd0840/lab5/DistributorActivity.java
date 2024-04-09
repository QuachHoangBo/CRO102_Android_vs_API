package fpoly.edu.boqhpd0840.lab5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import fpoly.edu.boqhpd0840.R;
import fpoly.edu.boqhpd0840.lab5.adapter.DistributorsAdapter;
import fpoly.edu.boqhpd0840.lab5.models.Distributor;
import fpoly.edu.boqhpd0840.lab5.models.Response;
import fpoly.edu.boqhpd0840.lab5.services.HttpRequest;
import retrofit2.Call;
import retrofit2.Callback;

public class DistributorActivity extends AppCompatActivity {
    EditText edSearch;
    List<Distributor> list;
    HttpRequest httpRequest;
    Dialog dialog;
    EditText edNameDistributor;
    Button btnSaveDialog, btnCancelDialog;
    FloatingActionButton floatingActionButton;
    String TAG = "//===DistributorActivity";
    private RecyclerView recyclerView;
    private DistributorsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        httpRequest = new HttpRequest();
        setContentView(R.layout.activity_distributor);
        edSearch = findViewById(R.id.edtSearch);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        recyclerView = findViewById(R.id.rcvDistributor);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        // Khởi tạo adapter ở đây
        adapter = new DistributorsAdapter();
        adapter.setOnItemClickListener(new DistributorsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String id) {
                showDialogDelete(id);
            }

            @Override
            public void updateItem(String id, String name) {
                openDialog(id,name);
            }
        });
        onResume();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog("","");
            }
        });

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionID, KeyEvent keyEvent) {
                if (actionID == EditorInfo.IME_ACTION_SEARCH){
                    String key = edSearch.getText().toString().trim();
                    httpRequest.callAPI().searchDistributor(key).enqueue(searchDistributor);
                }
                return false;
            }
        });

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
                    adapter.setData(list);
                    recyclerView.setAdapter(adapter);
                    for (Distributor item: list){
                        Log.i(TAG,"//==="+item.toString());
                    }
                }
            }
        }

        @Override
        public void onFailure(Call<Response<ArrayList<Distributor>>> call, Throwable throwable) {
                Log.i(TAG,"//===Error getList="+throwable.getMessage());
        }
    };

    Callback<Response<Distributor>> addDistributor = new Callback<Response<Distributor>>() {
        @Override
        public void onResponse(Call<Response<Distributor>> call, retrofit2.Response<Response<Distributor>> response) {
            if (response.isSuccessful()){
                if (response.body().getStatus()== 200){
                    Toast.makeText(getApplicationContext(),"Add successfull",Toast.LENGTH_LONG).show();
                    onResume();
                    dialog.dismiss();
                }
            }
        }

        @Override
        public void onFailure(Call<Response<Distributor>> call, Throwable throwable) {
            Log.i(TAG,"//===Error Add="+throwable.getMessage());
        }
    };
    Callback<Response<Distributor>> updateDistributor = new Callback<Response<Distributor>>() {
        @Override
        public void onResponse(Call<Response<Distributor>> call, retrofit2.Response<Response<Distributor>> response) {
            if (response.isSuccessful()){
                if (response.body().getStatus()== 200){
                    Toast.makeText(getApplicationContext(),"Update successful",Toast.LENGTH_LONG).show();
                    onResume();
                    dialog.dismiss();
                }
            }
        }

        @Override
        public void onFailure(Call<Response<Distributor>> call, Throwable throwable) {
            Log.i(TAG,"//===Error update="+throwable.getMessage());
        }
    };
    public void openDialog(String id,String name){
        dialog = new Dialog(DistributorActivity.this);
        dialog.setContentView(R.layout.dialog_distributor);
        edNameDistributor = dialog.findViewById(R.id.edtName);
        edNameDistributor.setText(name);
        btnSaveDialog = dialog.findViewById(R.id.btnUpdate);
        btnCancelDialog = dialog.findViewById(R.id.btnCancel);
        btnCancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSaveDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strName = edNameDistributor.getText().toString().trim();
                if (strName.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please input Distributor",Toast.LENGTH_LONG).show();
                }else {
                    Distributor distributor = new Distributor();
                    distributor.setName(strName);
                    if (id.isEmpty()){
                        httpRequest.callAPI().addDistributor(distributor).enqueue(addDistributor);
                        Toast.makeText(getApplicationContext(),"trường hợp 1",Toast.LENGTH_LONG).show();
                    }else{
                        httpRequest.callAPI().updateDistributor(id,distributor).enqueue(updateDistributor);
                        Toast.makeText(getApplicationContext(),"trường hợp 2",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        dialog.show();
    }
    Callback<Response<Distributor>> DeleteDistributor = new Callback<Response<Distributor>>() {
        @Override
        public void onResponse(Call<Response<Distributor>> call, retrofit2.Response<Response<Distributor>> response) {
            if (response.isSuccessful()){
                if (response.body().getStatus()== 200){
                    Toast.makeText(getApplicationContext(),"Delete successfull",Toast.LENGTH_LONG).show();
                    onResume();
                    if (dialog != null) {
                        dialog.dismiss();
                    }

                }
            }
        }

        @Override
        public void onFailure(Call<Response<Distributor>> call, Throwable throwable) {
            Log.i(TAG,"//===Error Delete="+throwable.getMessage());
        }
    };
    public void showDialogDelete(String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(DistributorActivity.this);
        builder.setTitle("Delete Distributor");
        builder.setMessage("Are you sure");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                httpRequest.callAPI().deleteDistributor(id).enqueue(DeleteDistributor);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    Callback<Response<ArrayList<Distributor>>> searchDistributor = new Callback<Response<ArrayList<Distributor>>>() {
        @Override
        public void onResponse(Call<Response<ArrayList<Distributor>>> call, retrofit2.Response<Response<ArrayList<Distributor>>> response) {
            if(response.isSuccessful()){
                if (response.body().getStatus() == 200){
                    list = new ArrayList<>();
                    list = response.body().getData();
                    if (list.isEmpty()) {
                        // Hiển thị thông báo không có kết quả tìm thấy
                        Toast.makeText(getApplicationContext(), "Không tìm thấy kết quả", Toast.LENGTH_SHORT).show();
                    } else {
                        // Hiển thị kết quả tìm thấy
                        adapter.setData(list);
                        recyclerView.setAdapter(adapter);
                    }
                    for (Distributor item: list){
                        Log.i(TAG,"//==="+item.toString());
                    }
                }
            }
        }

        @Override
        public void onFailure(Call<Response<ArrayList<Distributor>>> call, Throwable throwable) {
            Log.i(TAG,"//===Error search="+throwable.getMessage());
        }
    };
}