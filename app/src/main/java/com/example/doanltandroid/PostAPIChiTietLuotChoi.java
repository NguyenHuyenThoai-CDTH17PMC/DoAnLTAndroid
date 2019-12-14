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

public class PostAPIChiTietLuotChoi extends AsyncTask<Void,Void,String> {
    Context context;
    String duongdan;
    String luot_choi_id;
    String cau_hoi_id;
    String phuong_an;
    String diem;

    ChiTietLuotChoi chiTietLuotChoi;
    public PostAPIChiTietLuotChoi(Context context, String duongdan, String luot_choi_id, String cau_hoi_id, String phuong_an, String diem) {
        this.context = context;
        this.duongdan = duongdan;
        this.luot_choi_id = luot_choi_id;
        this.cau_hoi_id = cau_hoi_id;
        this.phuong_an = phuong_an;
        this.diem = diem;
        chiTietLuotChoi=new ChiTietLuotChoi();
        chiTietLuotChoi.setLuot_choi_id(luot_choi_id);
        chiTietLuotChoi.setCau_hoi_id(cau_hoi_id);
        chiTietLuotChoi.setPhuong_an(phuong_an);
        chiTietLuotChoi.setDiem(diem);

    }

    @Override
    protected String doInBackground(Void... voids) {
        return this.send();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(s==null){
            Toast.makeText(context, "Kết nối lũng", Toast.LENGTH_SHORT).show();;
        }
        else {
            if(s=="error"){
                Toast.makeText(context, "Không thành công", Toast.LENGTH_SHORT).show();
            }

        }
    }
    private String send(){
        HttpURLConnection connection=ReadAPI.connectionPOST(duongdan);
        if(connection==null){
            return null;
        }
        try {
            OutputStream outputStream=connection.getOutputStream();
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(outputStream,"UTF-8");
            BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(new ChiTietLuotChoiPackage(chiTietLuotChoi).getPackage());
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
