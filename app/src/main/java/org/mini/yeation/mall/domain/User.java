package org.mini.yeation.mall.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 *  模型 - 用户
 */
@Entity(tableName = "user")
public class User {
    /**
     * 用户ID
     */
    @PrimaryKey
    @ColumnInfo(name = "userid")
    private int userid;
    /**
     * 用户账号
     */
    @ColumnInfo(name = "username")
    private String username;
    /**
     * 用户密码
     */
    @ColumnInfo(name = "password")
    private String password;

    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
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
}
