package lk.ijse.fx.entity;

public class Signup {
    private String userName;
    private String password;

    public Signup(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Signup() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
