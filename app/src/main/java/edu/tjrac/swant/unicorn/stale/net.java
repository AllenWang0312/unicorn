package edu.tjrac.swant.unicorn.stale;

///**
// * Created by wpc on 2018/1/16 0016.
// */
//
//public class net {



//    public static void netWork(Context context, String tradeCode, JSONObject json, AESdecodeCallBack callBack){
////        DialogUtils.show(context);
//        String token="";
//        try {
////            token= UserDao.queryFirstData().getToken();
//        }catch (Exception e){
//            token="";
//        }
//        PackageManager pm =context.getPackageManager();
//        PackageInfo pi=null;
//        try {
//            pi = pm.getPackageInfo(context.getPackageName(),
//                    PackageManager.GET_CONFIGURATIONS);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//
////        AESUtils aesUtils = new AESUtils();
//
//        JSONObject js = new JSONObject();
//
////        String time= DateUtils.longToString(System.currentTimeMillis());
//
//        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//
//        try {
////            js.put("tradeTime", time);
//            if(json!=null){
//                js.put("data",json);
//            }
//            String deviceId = tm.getDeviceId();
//            if(TextUtils.isEmpty(deviceId)){
//                deviceId="868753022692227";
//            }
//            js.put("deviceImsi", deviceId);
////            js.put("deviceImsi", "868753022692227");
//            js.put("token",token);
//            js.put("tradeCode",tradeCode);
//            js.put("deviceType",2);
//            js.put("version", pi.versionName);
////            js.put("version", "1.0");
////            LogUtils.e("MyHttpUtils",js.toString());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        //            String encrypt = aesUtils.encrypt(js.toString().trim().getBytes("UTF-8"));
//        OkHttpUtils
//                .postString()
//                .url(Config.URL)
////                    .content(encrypt)
//                .content(js.toString().trim())
//                .mediaType(MediaType.parse("application/json;charset=utf-8"))
//                .build()
//                .execute(callBack);
//
//    }
//
//    public static void netWorkWithOutDialog(Context context, String tradeCode,String json, AESdecodeCallBack callBack){
//
//        String token="";
//        try {
////            token= UserDao.queryFirstData().getToken();
//        }catch (Exception e){
//            token="";
//        }
//        PackageManager pm =context.getPackageManager();
//        PackageInfo pi=null;
//        try {
//            pi = pm.getPackageInfo(context.getPackageName(),
//                    PackageManager.GET_CONFIGURATIONS);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//
////        AESUtils aesUtils = new AESUtils();
//
//        JSONObject js = new JSONObject();
//
////        String time= DateUtils.longToString(System.currentTimeMillis());
//
//        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//
//        try {
////            js.put("tradeTime", time);
//            if(!TextUtils.isEmpty(json)){
//                js.put("data",new JSONObject(json));
//            }
//            String deviceId = tm.getDeviceId();
//            if(TextUtils.isEmpty(deviceId)){
//                deviceId="868753022692227";
//            }
//            js.put("deviceImsi", deviceId);
////            js.put("deviceImsi", "868753022692227");
//            js.put("token",token);
//            js.put("tradeCode",tradeCode);
//            js.put("deviceType",2);
//            js.put("version", pi.versionName);
////            js.put("version", "1.0");
////            LogUtils.e("MyHttpUtils",js.toString());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        OkHttpUtils
//                .postString()
//                .url(Config.URL)
//                .content(js.toString().trim())
//                .mediaType(MediaType.parse("application/json;charset=utf-8"))
//                .build()
//                .execute(callBack);
//
//    }
//
//    /**
//     * 下载文件
//     * @param url
//     */
//    public static void downLoad(String url, FileCallBack callBack){
//        OkHttpUtils
//                .get()
//                .url(url)
//                .build()
//                .execute(callBack);
//    }
//
//
//    /**
//     * 上传图片
//     * @param callback
//     */
//    public static void upLoad(File file, StringCallback callback){
////        String token = UserDao.queryFirstData().getToken();
//        HashMap<String,String> map = new HashMap<>();
//        String[] split = file.getName().split("\\.");
//        String name= SystemClock.currentThreadTimeMillis()+"."+split[split.length-1];
//        map.put("Content-Disposition", "form-data; name=\"mFile\";filename=\""+ name +"\"");
//        HashMap<String, String> stringStringHashMap = new HashMap<>();
////        stringStringHashMap.put("type", String.valueOf(type));
////        stringStringHashMap.put("source", String.valueOf(source));
////        stringStringHashMap.put("token", token);
//        OkHttpUtils
//                .post()
////                .url(UrlConstant.PICURL_UPLOAD)
//                .addFile("mFile", name,file)
//                .params(stringStringHashMap)
//                .headers(map)
//                .build()
//                .execute(callback);
//    }
//}
