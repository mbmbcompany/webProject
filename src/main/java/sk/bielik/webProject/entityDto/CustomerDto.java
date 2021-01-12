package sk.bielik.webProject.entityDto;

import org.springframework.lang.Nullable;
import sk.bielik.webProject.entity.Credentials;

public class CustomerDto {
    @Nullable
    private long id;

    private Credentials credentials;

    private String password;

    private String nickName;

    private TrolleyDto trolley;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public TrolleyDto getTrolley() {
        return trolley;
    }

    public void setTrolley(TrolleyDto trolley) {
        this.trolley = trolley;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

}
