package sk.bielik.webProject.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Credentials {

    private String firstName;

    private String surename;

    private String email;

    private String phone_number;

    private String adress;

    private String bankAccount;

    public Credentials() {
    }

    public Credentials(String firstName, String surename, String email, String phone_number, String adress, String bankAccount) {
        this.firstName = firstName;
        this.surename = surename;
        this.email = email;
        this.phone_number = phone_number;
        this.adress = adress;
        this.bankAccount = bankAccount;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
}
