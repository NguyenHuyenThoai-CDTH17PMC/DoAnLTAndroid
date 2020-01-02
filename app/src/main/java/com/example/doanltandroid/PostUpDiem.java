package com.example.doanltandroid;

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

public class PostUpDiem extends AsyncTask<Void,Void,String> {
    Context context;
    String duongdan;
    String diem_cao_nhat;
    NguoiChoi nguoiChoi;
    public PostUpDiem(Context context ,String duongdan, String diem_cao_nhat) {
        this.context = context;
        this.duongdan=duongdan;
        this.diem_cao_nhat = diem_cao_nhat;
        nguoiChoi = new NguoiChoi();
        nguoiChoi.setDiem_cao_nhat(diem_cao_nhat);
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
            bufferedWriter.write(new UpdateDiemPackage(nguoiChoi).getPackage());
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
