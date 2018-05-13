package edu.tjrac.swant.wifi;

import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.koushikdutta.async.AsyncServer;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.http.body.MultipartFormDataBody;
import com.koushikdutta.async.http.body.Part;
import com.koushikdutta.async.http.server.AsyncHttpServer;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.koushikdutta.async.http.server.HttpServerRequestCallback;
import com.yckj.baselib.common.base.BaseServer;
import com.yckj.baselib.util.FileUtils;
import com.yckj.baselib.util.T;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.filesystem.MediaUtil;

/**
 * Created by wpc on 2018/1/24 0024.
 */

public class FileWifiShareServer extends BaseServer {

    FileUploadHolder fileUploadHolder = new FileUploadHolder();

    private AsyncHttpServer server = new AsyncHttpServer();
    private AsyncServer mAsyncServer = new AsyncServer();
    List<String> medias = new ArrayList<>();

    String[] support_video_type;
    String[] support_music_type;
    String[] support_image_type;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        support_music_type = getResources().getStringArray(R.array.support_music_type);
        support_video_type = getResources().getStringArray(R.array.support_vedio_type);
        support_image_type = getResources().getStringArray(R.array.support_image_type);
        startServer();
        medias = MediaUtil.getMediaUris(FileWifiShareServer.this, MediaUtil.MediaType.all);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

//        final String add = intent.getStringExtra("add");
//        if (!StringUtils.isEmpty(add)) {
//            medias.add(add);
//        }
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        if (server != null) {
            server.stop();
        }
        if (mAsyncServer != null) {
            mAsyncServer.stop();
        }
        super.onDestroy();
    }

    HashMap<String, String> typeMapping = new HashMap<>();

    private void startServer() {
        String sdcard = FileUtils.getExtSDCardPath();
        Log.i("startServer", sdcard == null ? "null" : sdcard);

        server.get("/images/.*", this::sendResources);
        server.get("/css/.*", this::sendResources);
        server.get("/scripts/.*", this::sendResources);

        server.get("/", new HttpServerRequestCallback() {
            @Override
            public void onRequest(AsyncHttpServerRequest request, AsyncHttpServerResponse response) {
                Log.i("onRequest", "get Request");
                try {
                    response.send(getIndexContent());
                } catch (IOException e) {
                    e.printStackTrace();
                    response.code(500).end();
                }
            }
        });
//        server.get("/send", new HttpServerRequestCallback() {
//            @Override
//            public void onRequest(AsyncHttpServerRequest request, AsyncHttpServerResponse response) {
//                Log.i("/send/onRequest",new Gson().toJson(request));
//            }
//        });
        //upload
        server.post("/files", (AsyncHttpServerRequest request, AsyncHttpServerResponse response) -> {
                    final MultipartFormDataBody body = (MultipartFormDataBody) request.getBody();
                    body.setMultipartCallback((Part part) -> {
                        if (part.isFile()) {
                            body.setDataCallback((DataEmitter emitter, ByteBufferList bb) -> {
                                fileUploadHolder.write(bb.getAllByteArray());
                                bb.recycle();
                            });
                        } else {
                            if (body.getDataCallback() == null) {
                                body.setDataCallback((DataEmitter emitter, ByteBufferList bb) -> {
                                    try {
                                        String fileName = URLDecoder.decode(new String(bb.getAllByteArray()), "UTF-8");
                                        fileUploadHolder.setFileName(fileName);
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                    bb.recycle();
                                });
                            }
                        }
                    });
                    request.setEndCallback((Exception e) -> {
                        T.show(FileWifiShareServer.this,"receiver wifi shared file"+fileUploadHolder.fileName);
                        fileUploadHolder.reset();
                        response.end();
//                        RxBus.get().post(Constants.RxBusEventType.LOAD_BOOK_LIST, 0);
                    });
                }
        );
        server.get("/files", new HttpServerRequestCallback() {
            @Override
            public void onRequest(AsyncHttpServerRequest request, AsyncHttpServerResponse response) {
                JSONArray array = new JSONArray();
                for (int i = 0; i < medias.size(); i++) {
                    File file = new File(medias.get(i));
                    String name = file.getName();
                    String type = "unsupport";
                    String end = name.substring(name.lastIndexOf("."), name.length()).toLowerCase();
                    if (typeMapping.keySet().contains(end)) {
                        type = typeMapping.get(end);
                    } else {
                        if (FileUtils.endWith(end, support_video_type)) {
                            type = "video";
                            typeMapping.put(end, type);
                        } else if (FileUtils.endWith(end, support_music_type)) {
                            type = "music";
                            typeMapping.put(end, type);
                        } else if (FileUtils.endWith(end, support_image_type)) {
                            type = "image";
                            typeMapping.put(end, type);
                        }
                    }
                    if (file.exists()) {
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("type", type);
                            jsonObject.put("name", file.getName());
                            jsonObject.put("path", file.getAbsolutePath());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        array.put(jsonObject);
                    }
                }
                response.send(array.toString());
            }
        });
        server.get("/progress/.*",  new HttpServerRequestCallback() {
            @Override
            public void onRequest(AsyncHttpServerRequest request, AsyncHttpServerResponse response) {
                JSONObject res = new JSONObject();

                String path = request.getPath().replace("/progress/", "");

                if (path.equals(fileUploadHolder.fileName)) {
                    try {
                        res.put("fileName", fileUploadHolder.fileName);
                        res.put("size", fileUploadHolder.totalSize);
                        res.put("progress", fileUploadHolder.fileOutPutStream == null ? 1 : 0.1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                response.send(res);
            }
        });




        server.get("/files/.*", new HttpServerRequestCallback() {
            @Override
            public void onRequest(AsyncHttpServerRequest request, AsyncHttpServerResponse response) {
                String path = request.getPath().replace("/files/", "");
                try {
                    path = URLDecoder.decode(path, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                File file = new File(path);
                if (file.exists() && file.isFile()) {
                    try {
                        FileInputStream fis = new FileInputStream(file);
                        response.sendStream(fis, fis.available());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return;
                }
                response.code(404).send("Not found!");
            }
        });
        server.listen(mAsyncServer, 54321);
    }
    private void sendResources(final AsyncHttpServerRequest request, final AsyncHttpServerResponse response) {
        try {
            String fullPath = request.getPath();
            fullPath = fullPath.replace("%20", " ");
            String resourceName = fullPath;
            if (resourceName.startsWith("/")) {
                resourceName = resourceName.substring(1);
            }
            if (resourceName.indexOf("?") > 0) {
                resourceName = resourceName.substring(0, resourceName.indexOf("?"));
            }
            if (!TextUtils.isEmpty(getContentTypeByResourceName(resourceName))) {
                response.setContentType(getContentTypeByResourceName(resourceName));
            }
            BufferedInputStream bInputStream = new BufferedInputStream(getAssets().open(resourceName));
            response.sendStream(bInputStream, bInputStream.available());
        } catch (IOException e) {
            e.printStackTrace();
            response.code(404).end();
            return;
        }
    }


    private static final String TEXT_CONTENT_TYPE = "text/html;charset=utf-8";
    private static final String CSS_CONTENT_TYPE = "text/css;charset=utf-8";
    private static final String BINARY_CONTENT_TYPE = "application/octet-stream";

    private static final String JS_CONTENT_TYPE = "application/javascript";
    private static final String PNG_CONTENT_TYPE = "application/x-png";
    private static final String JPG_CONTENT_TYPE = "application/jpeg";
    private static final String SWF_CONTENT_TYPE = "application/x-shockwave-flash";
    private static final String WOFF_CONTENT_TYPE = "application/x-font-woff";
    private static final String TTF_CONTENT_TYPE = "application/x-font-truetype";
    private static final String SVG_CONTENT_TYPE = "image/svg+xml";
    private static final String EOT_CONTENT_TYPE = "image/vnd.ms-fontobject";
    private static final String MP3_CONTENT_TYPE = "audio/mp3";
    private static final String MP4_CONTENT_TYPE = "video/mpeg4";

    private String getContentTypeByResourceName(String resourceName) {
        if (resourceName.endsWith(".css")) {
            return CSS_CONTENT_TYPE;
        } else if (resourceName.endsWith(".js")) {
            return JS_CONTENT_TYPE;
        } else if (resourceName.endsWith(".swf")) {
            return SWF_CONTENT_TYPE;
        } else if (resourceName.endsWith(".png")) {
            return PNG_CONTENT_TYPE;
        } else if (resourceName.endsWith(".jpg") || resourceName.endsWith(".jpeg")) {
            return JPG_CONTENT_TYPE;
        } else if (resourceName.endsWith(".woff")) {
            return WOFF_CONTENT_TYPE;
        } else if (resourceName.endsWith(".ttf")) {
            return TTF_CONTENT_TYPE;
        } else if (resourceName.endsWith(".svg")) {
            return SVG_CONTENT_TYPE;
        } else if (resourceName.endsWith(".eot")) {
            return EOT_CONTENT_TYPE;
        } else if (resourceName.endsWith(".mp3")) {
            return MP3_CONTENT_TYPE;
        } else if (resourceName.endsWith(".mp4")) {
            return MP4_CONTENT_TYPE;
        }
        return "";
    }

    private String getIndexContent() throws IOException {
        BufferedInputStream bInputStream = null;
        try {
            bInputStream = new BufferedInputStream(getAssets().open("index2.html"));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = 0;
            byte[] tmp = new byte[10240];
            while ((len = bInputStream.read(tmp)) > 0) {
                baos.write(tmp, 0, len);
            }
            return new String(baos.toByteArray(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (bInputStream != null) {
                try {
                    bInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    public static   void  start(Context context){
//
//    }

}
