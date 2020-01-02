package com.example.doanltandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LuotChoiAdapter extends  RecyclerView.Adapter<LuotChoiAdapter.ViewHoler> {

    private final ArrayList<LuotChoi> luotChois;

    private Context context;

    public LuotChoiAdapter(ArrayList<LuotChoi> luotChois, Context context) {
        this.luotChois = luotChois;
        this.context = context;
    }

    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHoler(LayoutInflater.from(context).inflate(R.layout.list_items_lichsuluotchoi,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        LuotChoi currentLuotChoi=luotChois.get(position);
        holder.BindTo(currentLuotChoi);
    }

    @Override
    public int getItemCount() {
        return luotChois.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txt_ngay;
        private TextView txt_socau;
        private TextView txt_diem;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            this.txt_ngay=itemView.findViewById(R.id.txtngay);
            this.txt_socau=itemView.findViewById(R.id.txtsocau);
            this.txt_diem=itemView.findViewById(R.id.txtDiem);
            itemView.setOnClickListener(this);
            Animation animation = AnimationUtils.loadAnimation(context,R.anim.anim_scale);
            itemView.startAnimation(animation);
        }
        void BindTo(LuotChoi currentLuotChoi){
            txt_ngay.setText(currentLuotChoi.getNgay_gio());
            txt_socau.setText(currentLuotChoi.getSo_cau());
            txt_diem.setText(currentLuotChoi.getDiem());
        }
        @Override
        public void onClick(View v) {
            /*
            LuotChoi currentLuotChoi=luotChois.get(getAdapterPosition());
            for (int i=0;i<luotChois.size();i++){
                if(currentLuotChoi.getId().equals(luotChois.get(i).getId())){

                    // new GetAPICauHoi(context).execute("http://10.0.2.2:8080/Do_An_PHP/public/api/cau-hoi?linh_vuc="+i);
                    // http://localhost:8080/Do_An_PHP/public/api/chi-tiet-luot-choi/lay_chi_tiet_luot_choi?luotchoi_id=10
                    Intent intent=new Intent(context,XemChiTiet.class);
                    intent.putExtra("luot_choi_id",currentLuotChoi.getId());
                    Activity activity= (Activity) context;
                    activity.startActivity(intent);
                    break;
                }
            }*/

        }
    }

}
