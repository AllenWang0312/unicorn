package edu.tjrac.swant.unicorn.stale;


/**
 * Created by 22320 on 2017/5/3.
 */

//public abstract class AESdecodeCallBack extends Callback<String>
//{
//    @Override
//    public void onError(Call call, Exception e, int id) {
////        Toast.makeText(YanChuanApplication.getApplication(), "网络连接失败！", Toast.LENGTH_SHORT).show();
////        DialogUtils.dismiss();
//    }
//
//    @Override
//    public String parseNetworkResponse(Response response, int id) throws IOException
//    {
////        AESUtils aesUtils = new AESUtils();
////        String decrypt = aesUtils.decrypt(response.body().string());
//        String decrypt = response.body().string();
//
//        return decrypt;
//    }
//
//    @Override
//    public void onResponse(String response, int id) {
////        DialogUtils.dismiss();
////        LogUtils.e("yangjun",response);
////        try {
////            JSONObject jsonObject = new JSONObject(response);
////            String errCode = jsonObject.getString("errCode");
////            String status = jsonObject.getString("status");
////            if(status.equals("error")){
////                Toast.makeText(YanChuanApplication.getApplication(), jsonObject.getString("errMessage"), Toast.LENGTH_SHORT).show();
//
////                //单点登录
////                if(errCode.equals("1002")){
////                    List<BaseActivity> activities = MyActivityManager.getAllActivitys();
////                    //如果包含LoginActivity，除了LoginActivity的全部销毁
////                    if(activities.contains(LoginActivity.class)){
////                        for (int i = 0; i < activities.size(); i++) {
////                            if(!(activities.get(i) instanceof LoginActivity)){
////                                activities.get(i).finish();
////                            }
////                        }
////                    }else {
////                        //如果不包含LoginActivity，启动LoginActivity，销毁其他的
////                        for (int i = 0; i < activities.size(); i++) {
////                            BaseActivity baseActivity = activities.get(activities.size() - 1);
////                            baseActivity.startActivity(new Intent(baseActivity,LoginActivity.class));
////                            activities.get(i).finish();
////                        }
////                    }
////                    UserDao.clearUser();
////                }
////                return;
////            }else {
//                setData(response);
////            }
////        } catch (JSONException e) {
////            e.printStackTrace();
////        }
//
//    }
//
//    public void setData(String response){
//
//    }
//}