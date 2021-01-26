package entity;

public class SubmitCommonEntity {
    private String androidClientVersionCode;
    private String city;
    private String clientVersion;
    private String deviceModel;
    private String deviceType;
    private String deviceVersion;

    //手机序列号
    private String imei;
    private String latitude;
    private String longitude;
    private String province;
    private String serverApiVersion;

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String str) {
        this.longitude = str;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String str) {
        this.latitude = str;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String str) {
        this.province = str;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public String getImei() {
        return this.imei;
    }

    public void setImei(String str) {
        this.imei = str;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public void setDeviceModel(String str) {
        this.deviceModel = str;
    }

    public String getDeviceVersion() {
        return this.deviceVersion;
    }

    public void setDeviceVersion(String str) {
        this.deviceVersion = str;
    }

    public String getClientVersion() {
        return this.clientVersion;
    }

    public void setClientVersion(String str) {
        this.clientVersion = str;
    }

    public String getAndroidClientVersionCode() {
        return this.androidClientVersionCode;
    }

    public void setAndroidClientVersionCode(String str) {
        this.androidClientVersionCode = str;
    }

    public String getServerApiVersion() {
        return this.serverApiVersion;
    }

    public void setServerApiVersion(String str) {
        this.serverApiVersion = str;
    }

    @Override
    public String toString() {
        return "SubmitCommonEntity{" +
                "androidClientVersionCode='" + androidClientVersionCode + '\'' +
                ", city='" + city + '\'' +
                ", clientVersion='" + clientVersion + '\'' +
                ", deviceModel='" + deviceModel + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", deviceVersion='" + deviceVersion + '\'' +
                ", imei='" + imei + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", province='" + province + '\'' +
                ", serverApiVersion='" + serverApiVersion + '\'' +
                '}';
    }
}