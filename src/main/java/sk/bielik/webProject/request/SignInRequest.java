package sk.bielik.webProject.request;


public class SignInRequest  {

    private String nickName;

    private String password;

    public SignInRequest() {
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
