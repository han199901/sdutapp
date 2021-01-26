package entity;

public class HeadParamsEntity {
    private String clientVersion;
    private String sessionKey;
    private String userId;
    private String userType;
    private String xxdm;

    public String getClientVersion() {
        return this.clientVersion;
    }

    public void setClientVersion(String str) {
        this.clientVersion = str;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String str) {
        this.userType = str;
    }

    public String getXxdm() {
        return this.xxdm;
    }

    public void setXxdm(String str) {
        this.xxdm = str;
    }

    public String getSessionKey() {
        return this.sessionKey;
    }

    public void setSessionKey(String str) {
        this.sessionKey = str;
    }
}