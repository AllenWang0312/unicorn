package edu.tjrac.swant.unicorn.stale;

import com.google.gson.Gson;

import java.io.File;

import edu.tjrac.swant.unicorn.UnicornApi;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by wpc on 2018/3/5 0005.
 */

public class NetRequire {


    public static void requireJson(String trade, Object postInfo, Callback callback){
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("trade", trade)
                .add("json", new Gson().toJson(
                        postInfo
                ))
                .build();
        Request request = new Request.Builder()
                .post(body)
                .url(UnicornApi.URL).
                        build();
        client.newCall(request).enqueue(callback);
    }

    public static void uploadFile(String trade, Object postInfo ,String tag, File file, Callback callback){
        OkHttpClient mOkHttpClent = new OkHttpClient();

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("trade",trade)
                .addFormDataPart("json",new Gson().toJson(postInfo))
                .addFormDataPart(tag, file.getName(),
                        RequestBody.create(MediaType.parse("image/png"), file));
        RequestBody requestBody = builder.build();

        Request request = new Request.Builder()
                .url(UnicornApi.URL)
                .post(requestBody)
                .build();
        Call call = mOkHttpClent.newCall(request);
        call.enqueue(callback);
    }


}
