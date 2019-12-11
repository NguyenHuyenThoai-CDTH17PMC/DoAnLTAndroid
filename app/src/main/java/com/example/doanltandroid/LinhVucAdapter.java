package com.example.doanltandroid;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LinhVucAdapter extends  RecyclerView.Adapter<LinhVucAdapter.ViewHoler> {

   private final ArrayList<LinhVuc>linhVucs;
   private Context context;

    public LinhVucAdapter(ArrayList<LinhVuc> linhVucs, Context context) {
        this.linhVucs = linhVucs;
        this.context = context;
    }

    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHoler(LayoutInflater.from(context).inflate(R.layout.list_items_linhvuc,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        LinhVuc currentLinhvuc=linhVucs.get(position);
        holder.BindTo(currentLinhvuc);
    }

    @Override
    public int getItemCount() {
        return linhVucs.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tenlinhvuc;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);


            this.tenlinhvuc=itemView.findViewById(R.id.txttenlinhvuc);
            itemView.setOnClickListener(this);

         }
         void BindTo(LinhVuc currentLinhvuc){
               tenlinhvuc.setText(currentLinhvuc.getTenlinhvuc());

         }

        @Override
        public void onClick(View v) {
            LinhVuc currentLinhvuc=linhVucs.get(getAdapterPosition());

            for (int i=0;i<linhVucs.size();i++){
                if(currentLinhvuc.getTenlinhvuc().equals(linhVucs.get(i).getTenlinhvuc())){
                    i=i+1;
                    new GetAPICauHoi(context).execute("http://192.168.56.1/Do_An_PHP/public/api/cau-hoi?linh_vuc="+i);
                    break;
                }
            }

        }
    }

}
