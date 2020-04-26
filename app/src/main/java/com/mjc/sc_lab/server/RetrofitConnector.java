package com.mjc.sc_lab.server;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnector {
    final static String URL = "https://studylog.shop:5001/"; // 서버 API

    public static Retrofit createRetrofit(Context context) {
        return new Retrofit.Builder()
                .baseUrl(URL)
                .client(new OkHttpClient.Builder()
                        .sslSocketFactory(SSLConnector.getPinnedCertSslSocketFactory(context))
                        .build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}

/*private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public ServiceApi server = retrofit.create(ServiceApi.class);
}*/