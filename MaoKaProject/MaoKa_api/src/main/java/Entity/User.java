package Entity;

public class User {
    private int id;
    private String account;
    private String password;
    private String username;
    private String email;
    private int phonenumber;
    private String image;
    private int loginType;
    public User() {
    }

    //获取的时候使用
    public User(int id, String account, String password, String username, String email, int phonenumber, String image, int loginType) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.username = username;
        this.email = email;
        this.phonenumber = phonenumber;
        this.image = image;
        this.loginType = loginType;
    }


    //添加的时候使用，因为当时没有id
    public User(String account, String password, String username, String email, int phonenumber, String image, int loginType) {
        this.account = account;
        this.password = password;
        this.username = username;
        this.email = email;
        this.phonenumber = phonenumber;
        this.image = image;
        this.loginType = loginType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phonenumber=" + phonenumber +
                ", image='" + image + '\'' +
                ", loginType=" + loginType +
                '}';
    }
}
