package com.example.doanltandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

public class GetAPICauHoi extends AsyncTask<String,String,String> {
    private Context context;

    public GetAPICauHoi(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        return ReadAPI.GetJSON(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Intent intent=new Intent(context,CauHoiLayTheoIDLinhVuc.class);
        intent.putExtra("JSON",s);
        Activity activity= (Activity) context;
        activity.startActivity(intent);
    }
}
