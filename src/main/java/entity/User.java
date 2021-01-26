package entity;

public class User extends SubmitCommonEntity {
    private String passw;
    private String schoolId;
    private String userId;

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "passw='" + passw + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
