package com.example.care;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class detail extends AppCompatActivity {

    TextView tvCases,tvRecovered,tvActive,tvTotalDeaths,tvTodaysDeath,tvAffectedCountry,tvCritical,tvTodaysCases;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvCases=findViewById(R.id.tvCases);
        tvRecovered=findViewById(R.id.tvRecovered);
        tvActive=findViewById(R.id.tvActive);
        tvTotalDeaths=findViewById(R.id.tvtotalDeaths);
        tvTodaysDeath=findViewById(R.id.tvTodaysDeath);
        tvAffectedCountry=findViewById(R.id.tvaffectedCountry);
        tvCritical=findViewById(R.id.tvCritical);
        tvTodaysCases=findViewById(R.id.tvTodaysCases);
        fetchData();
    }

    private void fetchData() {
        String url="https://corona.lmao.ninja/v2/countries";
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String countryName=jsonObject.getString("country");
                        if(countryName.equals("India"))
                        {
                            Log.d("yes", "yes");
                            tvCases.setText(jsonObject.getString("cases"));
                            tvRecovered.setText(jsonObject.getString("recovered"));
                            tvActive.setText(jsonObject.getString("active"));
                            tvTotalDeaths.setText(jsonObject.getString("deaths"));
                            tvTodaysDeath.setText(jsonObject.getString("todayDeaths"));
                            tvAffectedCountry.setText(jsonObject.getString("affectedCountries"));
                            tvCritical.setText(jsonObject.getString("critical"));
                            tvTodaysCases.setText(jsonObject.getString("todayCases"));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(detail.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("error","yes");
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}