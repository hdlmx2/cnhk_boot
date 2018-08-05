package store.cnhk.pojo;

import java.sql.Timestamp;
import java.util.Objects;


public class User {
    private int userId;
    private String name;
    private String userName;
    private String userPassword;
    private String wechatId;
    private String wecharName;
    private Timestamp timestamp;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userid) {
        this.userId = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }


    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userpassward) {
        this.userPassword = userpassward;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatid) {
        this.wechatId = wechatid;
    }


    public String getWecharName() {
        return wecharName;
    }

    public void setWecharName(String wecharname) {
        this.wecharName = wecharname;
    }


    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(userPassword, user.userPassword) &&
                Objects.equals(wechatId, user.wechatId) &&
                Objects.equals(wecharName, user.wecharName) &&
                Objects.equals(timestamp, user.timestamp);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, userName, userPassword, wechatId, wecharName, timestamp);
    }
}
