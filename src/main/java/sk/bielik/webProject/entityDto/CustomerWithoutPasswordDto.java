package sk.bielik.webProject.entityDto;

import org.springframework.lang.Nullable;
import sk.bielik.webProject.entity.Credentials;

import java.util.ArrayList;
import java.util.List;

public class CustomerWithoutPasswordDto {
    @Nullable
    private long id;

    private Credentials credentials;

    private String nickName;

    private TrolleyDto trolley;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public TrolleyDto  getTrolley() {
        return trolley;
    }

    public void setTrolley(TrolleyDto  trolley) {
        this.trolley = trolley;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

}
