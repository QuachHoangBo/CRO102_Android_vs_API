package fpoly.edu.boqhpd0840.lab5.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fpoly.edu.boqhpd0840.R;
import fpoly.edu.boqhpd0840.lab5.models.Fruit;

public class FruitAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static final int TYPE_ITEM = 1;
    public static final int TYPE_LOADING = 2;
    private List<Fruit> mListFruit;
    private boolean isLoadingAdd;
    public void setData(List<Fruit> list){
        this.mListFruit = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (mListFruit != null && position == mListFruit.size() - 1 && isLoadingAdd){
            return TYPE_LOADING;
        }
        return TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      if (TYPE_ITEM == viewType){
          View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fruits,parent,false);
          return new FruitViewHolder(view);
      }else{
          View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading,parent,false);
          return new LoadingViewHolder(view);
      }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == TYPE_ITEM){
            Fruit fruit = mListFruit.get(position);
            FruitViewHolder fruitviewHolder = (FruitViewHolder) holder;
            fruitviewHolder.tvName.setText(fruit.getName() + " " + (position + 1));

        }
    }

    @Override
    public int getItemCount() {
        if (mListFruit != null){
            return  mListFruit.size();
        }
        return 0;
    }

    public class FruitViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName;

        public FruitViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
    public class LoadingViewHolder extends RecyclerView.ViewHolder{
        private ProgressBar progressBar;
        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progree_bar);
        }
    }
    public void addFooterLoading(){
        isLoadingAdd = true;
        mListFruit.add(new Fruit(""));
    }
    public void removeFooterLoading(){
        isLoadingAdd = false;

        int postition = mListFruit.size() - 1;
        Fruit fruit = mListFruit.get(postition);
        if (fruit != null){
            mListFruit.remove(postition);
            notifyItemRemoved(postition);
        }

    }
}
