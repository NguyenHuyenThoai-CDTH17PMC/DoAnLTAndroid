package com.example.doanltandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class ReadAPI {
    public static String GetJSON(String duongdan){
        HttpURLConnection urlConnection=null;
        StringBuilder stringBuilder=new StringBuilder();
        BufferedReader bufferedReader=null;
        try {
            URL url=new URL(duongdan);
            urlConnection= (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            InputStream inputStream=urlConnection.getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            bufferedReader=new BufferedReader(inputStreamReader);
            String line;
            while ((line=bufferedReader.readLine())!=null) {
                stringBuilder.append(line);
            }
            if (stringBuilder.length()==0){
                return null;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(urlConnection!=null){
                urlConnection.disconnect();
            }
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }
    public static HttpURLConnection connectionPOST(String duongdan){
        try {
            URL url=new URL(duongdan);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(20000);

            connection.setDoOutput(true);
            connection.setDoInput(true);
            return connection;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
    // Encode Bitmap --> Base64 string
    public static String encodeBitmapToString(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    public static void PostAPI(final Context context, final Map<String,String> mMap, String duongdan)
    {
        //Khởi tạo stringrequest với method = post, đường dẫn
        StringRequest stringRequest=new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override//Kết quả trả về
            public void onResponse(String response) {
//                try {
//                    JSONArray jr = new JSONArray(response);
//                    JSONObject jb = jr.getJSONObject(5);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        }, new Response.ErrorListener() { //Kết quả trả về nếu lỗi
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            public String getBodyContentType() { //Định dạng chuỗi trả về UTF-8
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
            @Override
            //Giá trị truyên vồ là 1 map <String,String>
            // String 1: là tên request.
            // String 2 là giá trị....
            // ví dụ localhost:8000/api?concunhonho=8.35cm
            //String 1: concunhonho - String 2= 8.35cm
            protected Map<String,String> getParams() throws AuthFailureError {
//                Map<String,String> params=mMap;
//                for(String key:mMap.keySet()){
//                    params.put( key,mMap.get(key));
//                }
                return mMap;

            }
        };
        //Khởi tạo 1 request chuẩn bị thực thi
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        //Thực thi request
        requestQueue.add(stringRequest);// Rồi còn thắc mắc nào ko
        //Map<String,String> map = new Hashmap<>();
        //String 1 là key, String 2 là value (có thể thay = int,float,double,...)



    }
}
//Ví dụ thêm đăng ký:
/* Khai báo 1 map
    Map<String,String> map = new Hashmap<>();
    map.put("ten_dang_nhap","concu"); t ko biết nói sao nên xóa :))
    map.put("mat_khau","8,35cm");
    -- khai  báo map xong thì bỏ vô ở dưới
    String url = "http://10.0.2.2:8000/api/dang-ky;
 * ReadAPI.PostAPI() oke noi chu hieu r chau
 * có gì chauw làm ko ?
   v thì t xong rồi đó m chỉ cần khai báo MAP + URL r bỏ vô là xong ,de vl ma tao chua test, m test thu xem

    jsonNguoiChoi.put("ten_dang_nhap",nguoiChoi.getTen_dang_nhap());
            jsonNguoiChoi.put("mat_khau",nguoiChoi.getMat_khau());
            jsonNguoiChoi.put("email",nguoiChoi.getEmail());
            jsonNguoiChoi.put("hinh_dai_dien", nguoiChoi.getHinh_dai_dien()); // hình đại diện thì nó truyền sao? don gian, may nen chuoi base 64 thoi
            jsonNguoiChoi.put("diem_cao_nhat",nguoiChoi.getDiem_cao_nhat());
            jsonNguoiChoi.put("credit",nguoiChoi.getCredit());

            jsonNguoiChoi.put("mxh_id",nguoiChoi.getMxh_id());
 * */