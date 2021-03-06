package com.example.doanltandroid;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.security.NoSuchAlgorithmException;

public class PostAPIChinhSuaNguoiChoi extends AsyncTask<Void,Void,String> {

    Context context;
    String duongdan;
    String Tendangnhap;
    String Email;
    String Matkhau;
    String hinh_dai_dien;
    String diem_cao_nhat;
    String credit;

    NguoiChoi nguoiChoi;

    public PostAPIChinhSuaNguoiChoi(Context context ,String duongdan,String edtTendangnhap, String edtEmail, String edtMatkhau,String hinh_dai_dien,String diem_cao_nhat,String credit) throws NoSuchAlgorithmException {
        this.context = context;
        this.duongdan=duongdan;
        this.Tendangnhap = edtTendangnhap;
        this.Email = edtEmail;
        this.Matkhau = edtMatkhau;
        this.hinh_dai_dien=hinh_dai_dien;
        this.diem_cao_nhat=diem_cao_nhat;
        this.credit=credit;

        nguoiChoi = new NguoiChoi();

        nguoiChoi.setTen_dang_nhap(edtTendangnhap);
        nguoiChoi.setMat_khau(StringMD5.convertHashToString(edtMatkhau));
        nguoiChoi.setEmail(edtEmail);
        nguoiChoi.setHinh_dai_dien(hinh_dai_dien);
        nguoiChoi.setDiem_cao_nhat(diem_cao_nhat);
        nguoiChoi.setCredit(credit);
    }
    @Override
    protected String doInBackground(Void... voids) {
        return this.send();
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(s==null){
            Toast.makeText(context, "Kết nối lũng", Toast.LENGTH_SHORT).show();
        }
        else {
            if(s=="error"){
                Toast.makeText(context, "Toang", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context, "Thành công!!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private String send(){
        final HttpURLConnection connection = ReadAPI.connectionPOST(duongdan);
        if(connection==null){
            return null;
        }
        try {
            OutputStream outputStream=connection.getOutputStream();
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(outputStream,"UTF-8");
            BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(new SuaNguoiChoiPackage(nguoiChoi).getPackage());
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            int response_thanhcong=connection.getResponseCode();
            if(response_thanhcong == connection.HTTP_OK){
                BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder stringBuilder=new StringBuilder();
                String line;
                while ((line=bufferedReader.readLine())!=null){
                    stringBuilder.append(line);
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            else {
                return String.valueOf(response_thanhcong);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
}

