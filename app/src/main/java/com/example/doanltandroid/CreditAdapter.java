package com.example.doanltandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreditAdapter extends RecyclerView.Adapter<CreditAdapter.ViewHolder> {
    @NonNull
    private final ArrayList<Credit> credits;
    private Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public CreditAdapter(@NonNull ArrayList<Credit> credits, Context context) {
        this.credits = credits;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_credit,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       Credit currentCredit=credits.get(position);
        holder.BindTo(currentCredit);
    }

    @Override
    public int getItemCount() {
        return credits.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView sotien;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.sotien=itemView.findViewById(R.id.txtso_tien);
            itemView.setOnClickListener(this);
        }
        void BindTo(Credit currentCredit){
            sotien.setText(currentCredit.getSo_tien());
        }
        @Override
        public void onClick(View v) {
            sharedPreferences=context.getSharedPreferences("nguoichoi",context.MODE_PRIVATE);
            String id_nguoichoi = sharedPreferences.getString("id_nguoichoi","");
            String duongdanSuaCreditNguoiChoi="http://10.0.2.2:8080/Do_An_PHP/public/api/nguoi-choi/cap-nhat-credit";
            String duongdanLichSuMuaCredit="http://10.0.2.2:8080/Do_An_PHP/public/api/lich-su-mua-credit/them-moi";

            Credit currentCredit=credits.get(getAdapterPosition());
            for (int i=0;i<credits.size();i++){
                if(currentCredit.getSo_tien().equals(credits.get(i).getSo_tien())){

                    Map<String,String>mapPostMuaCredit=new HashMap<>();
                    mapPostMuaCredit.put("id",id_nguoichoi);
                    int credit=Integer.parseInt(credits.get(i).getSo_tien())+ Integer.parseInt(sharedPreferences.getString("credit",""));
                    mapPostMuaCredit.put("credit",String.valueOf(credit));
                    ReadAPI.PostAPI(context,mapPostMuaCredit,duongdanSuaCreditNguoiChoi);

                    //cap nhat credit trong share
                    editor=sharedPreferences.edit();
                    editor.putString("credit",String.valueOf(credit));
                    editor.commit();

                    //lưu vào lịch sử mua
                    Map<String,String>mapPostLichSuMua=new HashMap<>();
                    mapPostLichSuMua.put("nguoi_choi_id",id_nguoichoi);
                    mapPostLichSuMua.put("goi_credit_id",credits.get(i).getId());
                    mapPostLichSuMua.put("credit",credits.get(i).getCredit());
                    mapPostLichSuMua.put("so_tien",credits.get(i).getSo_tien());
                    ReadAPI.PostAPI(context,mapPostLichSuMua,duongdanLichSuMuaCredit);

                    Toast.makeText(context, "Credit hiện có: "+credit, Toast.LENGTH_SHORT).show();

                }
            }
        }
    }
}
