package com.course.dao.po;


import com.course.util.ByteConstantVar;

public class Admins extends BasePo {
    public final static String KEY_OF_ONLINE_ADMIN_IN_HTTP_SESSION = "onlineAdmin";
    private String username;
    private String password;
    private String description;

    /***
     * 性别枚举
     */
    public enum Sex {
        M(ByteConstantVar.ONE, "男"),
        W(ByteConstantVar.TWO, "女"),
        U(ByteConstantVar.THREE, "未知");

        Sex(Byte code, String name) {
            this.code = code;
            this.name = name;
        }

        Byte code;
        String name;

        public Byte getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }

    public static String getKeyOfOnlineAdminInHttpSession() {
        return KEY_OF_ONLINE_ADMIN_IN_HTTP_SESSION;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
