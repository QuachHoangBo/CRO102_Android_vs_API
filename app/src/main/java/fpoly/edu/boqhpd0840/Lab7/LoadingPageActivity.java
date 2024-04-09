package fpoly.edu.boqhpd0840.Lab7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fpoly.edu.boqhpd0840.R;
import fpoly.edu.boqhpd0840.lab5.adapter.FruitAdapter;
import fpoly.edu.boqhpd0840.lab5.models.Fruit;

public class LoadingPageActivity extends AppCompatActivity {

    private RecyclerView rcvFruit;
    private FruitAdapter fruitAdapter;
    private List<Fruit> mListFruit;

    private boolean isLoading;
    private  boolean isLastPage;
    private int totalPage = 5;
    private int currentPage = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_page);

        rcvFruit = findViewById(R.id.rcv_fruit);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvFruit.setLayoutManager(linearLayoutManager);

        fruitAdapter = new FruitAdapter();
        rcvFruit.setAdapter(fruitAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rcvFruit.addItemDecoration(itemDecoration);

        setFirstData();

        rcvFruit.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            public void loadMoreItem() {
                isLoading = true;

                currentPage +=1;
                loadNextPage();
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }
        });
    }

    private void setFirstData(){
        mListFruit = getListFruit();
        fruitAdapter.setData(mListFruit);
        if (currentPage < totalPage){
            fruitAdapter.addFooterLoading();
        }else{
            isLastPage = true;
        }
    }

    public void loadNextPage(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<Fruit> list = getListFruit();

                fruitAdapter.removeFooterLoading();
                mListFruit.addAll(list);
                fruitAdapter.notifyDataSetChanged();

                isLoading = false;

                if (currentPage < totalPage){
                    fruitAdapter.addFooterLoading();
                }else{
                    isLastPage = true;
                }
            }
        },2000);
    }
    private List<Fruit> getListFruit(){
        Toast.makeText(this, "Load data page "+ currentPage, Toast.LENGTH_SHORT).show();

        List<Fruit> list = new ArrayList<>();
        for (int i = 1; i<= 10;i++){
            list.add(new Fruit("Fruit name"));
        }
        return list;
    }

}