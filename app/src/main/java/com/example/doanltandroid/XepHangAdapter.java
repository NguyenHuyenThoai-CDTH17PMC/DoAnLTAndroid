package com.example.doanltandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class XepHangAdapter extends  RecyclerView.Adapter<XepHangAdapter.ViewHoler> {

    private final ArrayList<XepHang> xepHangs;
    private Context context;
    private String url;

    public XepHangAdapter(ArrayList<XepHang> xepHangs, Context context) {
        this.xepHangs = xepHangs;
        this.context = context;
    }

    @Override
    public XepHangAdapter.ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new XepHangAdapter.ViewHoler(LayoutInflater.from(context).inflate(R.layout.list_item_xephang, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull XepHangAdapter.ViewHoler holder, int position) {
        XepHang xepHang = xepHangs.get(position);
        holder.BindTo(xepHang);
    }

    @Override
    public int getItemCount() {
        return xepHangs.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{

        private TextView ten_dang_nhap;
        private TextView diem_cao_nhat;
        private ImageView hinh_dai_dien;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            this.ten_dang_nhap = itemView.findViewById(R.id.txttendangnhapxh);
            this.diem_cao_nhat = itemView.findViewById(R.id.txtdiemxh);
            this.hinh_dai_dien=itemView.findViewById(R.id.imgxephang);
            Animation animation = AnimationUtils.loadAnimation(context,R.anim.anim_scale);
            itemView.startAnimation(animation);
        }
        void BindTo(XepHang currentLinhvuc){
            ten_dang_nhap.setText(currentLinhvuc.getTen_dang_nhap());
            diem_cao_nhat.setText(currentLinhvuc.getDiem_cao_nhat());
            url = "http://192.168.56.1:8080/Do_An_PHP/public/img/"+currentLinhvuc.getHinh_dai_dien();
            Picasso.with(context).load(url).into(hinh_dai_dien);
        }
    }
}
