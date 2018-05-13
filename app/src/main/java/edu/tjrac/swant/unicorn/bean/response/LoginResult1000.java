package edu.tjrac.swant.unicorn.bean.response;

import edu.tjrac.swant.unicorn.bean.User;

/**
 * Created by wpc on 2018/3/5 0005.
 */

public class LoginResult1000 {
    int code ;
    String message;
    User data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
