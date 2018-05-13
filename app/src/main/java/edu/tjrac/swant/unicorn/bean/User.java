package edu.tjrac.swant.unicorn.bean;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import com.yckj.baselib.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wpc on 2018/2/8 0008.
 */


public class User  implements Parcelable {


    long id;
    int age;

    String account;
    String name;
    String password;
    String avarat;

    String tel;
    String email;
    String address;
    String qq;
    String wechat;

    float JD;
    float WD;

    public User(String account, String psw) {
        this.account = account;
        if (StringUtils.isMobileNO(account)) {
            this.tel = account;
        } else if (StringUtils.isEmail(account)) {
            this.email = account;
        }
        this.password = psw;
    }

    public User(String account) {
        this.account = account;
    }

    public User(Parcel in) {
        id = in.readLong();
        age = in.readInt();
        account = in.readString();
        name = in.readString();
        password = in.readString();
        avarat = in.readString();
        tel = in.readString();
        email = in.readString();
        address = in.readString();
        qq = in.readString();
        wechat = in.readString();
        JD = in.readFloat();
        WD = in.readFloat();
    }

    public User(long id, float jd, float wd) {
        this.id = id;
        this.JD = jd;
        this.WD = wd;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeInt(age);
        dest.writeString(account);
        dest.writeString(name);
        dest.writeString(password);
        dest.writeString(avarat);
        dest.writeString(tel);
        dest.writeString(email);
        dest.writeString(address);
        dest.writeString(qq);
        dest.writeString(wechat);
        dest.writeDouble(JD);
        dest.writeDouble(WD);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvarat() {
        return avarat;
    }

    public void setAvarat(String avarat) {
        this.avarat = avarat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getJD() {
        return JD;
    }

    public void setJD(float JD) {
        this.JD = JD;
    }

    public double getWD() {
        return WD;
    }

    public void setWD(float WD) {
        this.WD = WD;
    }

    public long getId() {
        return id;
    }

    public void setId(long _id) {
        this.id = _id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }


    public Map<String, String> getMap() {
        Map<String, String> map = new HashMap<>();

        if (id != 0) map.put("id", id + "");
        if (age != 0) map.put("age", age + "");
        if (!StringUtils.isEmpty(account)) map.put("account", account);

        if (!StringUtils.isEmpty(name)) map.put("name", name);

        if (!StringUtils.isEmpty(password)) map.put("password", password);

        if (!StringUtils.isEmpty(avarat)) map.put("avarat", avarat);


        if (!StringUtils.isEmpty(tel)) map.put("tel", tel);

        if (!StringUtils.isEmpty(email)) map.put("email", email);

        if (!StringUtils.isEmpty(address)) map.put("address", address);

        if (!StringUtils.isEmpty(qq)) map.put("qq", qq);

        if (!StringUtils.isEmpty(wechat)) map.put("wechat", wechat);
        if (JD != 0f) map.put("JD", JD + "");
        if (WD != 0f) map.put("WD", JD + "");
        return map;
    }

    public void setLocation(Location location) {
        this.JD = (float) location.getLongitude();
        this.WD = (float) location.getLatitude();
    }
}
