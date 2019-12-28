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

public class PostAPIFacebook extends AsyncTask<Void,Void,String> {
    Context context;
    String duongdan;
    String edtTendangnhap;
    String edtEmail;
    String edtMatkhau;
    String hinhanh;

    NguoiChoi nguoiChoi;

    ProgressDialog progressDialog;


    public PostAPIFacebook(Context context, String duongdan, String edtTendangnhap, String edtEmail, String edtMatkhau, String hinhanh) throws NoSuchAlgorithmException {
        this.context = context;
        this.duongdan = duongdan;
        this.edtTendangnhap = edtTendangnhap;
        this.edtEmail = edtEmail;
        this.edtMatkhau = edtMatkhau;
        this.hinhanh=hinhanh;
        nguoiChoi=new NguoiChoi();
        nguoiChoi.setTen_dang_nhap(edtTendangnhap);
        nguoiChoi.setMat_khau(StringMD5.convertHashToString(edtMatkhau));
        nguoiChoi.setEmail(edtEmail);
        nguoiChoi.setHinh_dai_dien(hinhanh);
        nguoiChoi.setDiem_cao_nhat("0");
        nguoiChoi.setCredit("0");
        nguoiChoi.setMxh_id("1");
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog=new ProgressDialog(context);
        progressDialog.setTitle("Phản hồi");
        progressDialog.setMessage("Vui lòng chờ trong giây lát");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return this.send();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressDialog.dismiss();
        if(s==null){
            Toast.makeText(context, "Kết nối lũng", Toast.LENGTH_SHORT).show();;
        }
        else {
            if(s=="error"){
                Toast.makeText(context, "Không thành công", Toast.LENGTH_SHORT).show();
            }
            else {

                Toast.makeText(context, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private  String send(){
        HttpURLConnection connection=ReadAPI.connectionPOST(duongdan);
        if(connection==null){
            return null;
        }
        try {
            OutputStream outputStream=connection.getOutputStream();
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(outputStream,"UTF-8");
            BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(new NguoiChoiPackage(nguoiChoi).getPackage());
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            int response_thanhcong=connection.getResponseCode();
            if(response_thanhcong==connection.HTTP_OK){
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
                return "error";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
