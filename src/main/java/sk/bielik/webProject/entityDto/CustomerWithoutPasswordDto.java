package sk.bielik.webProject.entityDto;

import org.springframework.lang.Nullable;
import sk.bielik.webProject.entity.Trolley;

public class CustomerWithoutPasswordDto {
    @Nullable
    private long id;

    private String name;

    private String surename;

    private String email;

    private String phone_number;

    private String adress;

    private String nickName;

    private Trolley trolley;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Trolley getTrolley() {
        return trolley;
    }

    public void setTrolley(Trolley trolley) {
        this.trolley = trolley;
    }

    @Override
    public String toString() {
        return "CustomerWithoutPasswordDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surename='" + surename + '\'' +
                ", email='" + email + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", adress='" + adress + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
