package com.example.doanltandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LinhVucAdapter extends  RecyclerView.Adapter<LinhVucAdapter.ViewHoler> {

   private final ArrayList<LinhVuc>linhVucs;
   LayoutInflater layoutInflater;

    public LinhVucAdapter(ArrayList<LinhVuc> linhVucs, Context context) {
        this.linhVucs = linhVucs;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=layoutInflater.inflate(R.layout.list_items_linhvuc,parent,false);
        return new ViewHoler(itemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
      holder.btnTenLinhVuc.setText(linhVucs.get(position).getTenlinhvuc());
    }

    @Override
    public int getItemCount() {
        return linhVucs.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        final LinhVucAdapter linhVucAdapter;
        private Button btnTenLinhVuc;

    public ViewHoler(@NonNull View itemView, LinhVucAdapter linhVucAdapter) {
        super(itemView);
        this.linhVucAdapter = linhVucAdapter;
        this.btnTenLinhVuc = itemView.findViewById(R.id.btntenlinhvuc);
    }
}

}
