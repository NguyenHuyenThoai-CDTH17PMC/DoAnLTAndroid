package com.example.doanltandroid;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class PostAPILuotChoi extends AsyncTask<Void,Void,String> {
     Context context;
     String duongdan;
     String nguoi_choi_id;
     String so_cau;
     String diem;
     String ngay_gio;

    LuotChoi luotChoi;
    ProgressDialog progressDialog;

    public PostAPILuotChoi(Context context, String duongdan, String nguoi_choi_id, String so_cau, String diem, String ngay_gio) {
        this.context = context;
        this.duongdan = duongdan;
        this.nguoi_choi_id = nguoi_choi_id;
        this.so_cau = so_cau;
        this.diem = diem;
        this.ngay_gio = ngay_gio;
        luotChoi=new LuotChoi();
        luotChoi.setNguoi_choi_id(nguoi_choi_id);
        luotChoi.setSo_cau(so_cau);
        luotChoi.setDiem(diem);
        luotChoi.setNgay_gio(ngay_gio);
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
                Toast.makeText(context, "Đã lưu lượt chơi thành công", Toast.LENGTH_SHORT).show();
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
            bufferedWriter.write(new LuotChoiPackage(luotChoi).getPackage());
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
