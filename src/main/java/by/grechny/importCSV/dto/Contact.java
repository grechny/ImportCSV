package by.grechny.importCSV.dto;

import java.io.Serializable;

/**
 * Реализация класса Contact, содержащего поля
 * login, name, surname, email, phoneNumber
 */
public class Contact implements Serializable{

    private String login;
    private String name;
    private String surname;
    private String email;
    private Long phoneNumber;

    public void setLogin(String login){
        this.login = login;
    }

    public String getLogin(){
        return this.login;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setSurname (String surname){
        this.surname = surname;
    }

    public String getSurname (){
        return this.surname;
    }

    public void setEmail (String email){
        this.email = email;
    }

    public String getEmail (){
        return this.email;
    }

    public void setPhoneNumber (Long phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public Long getPhoneNumber (){
        return this.phoneNumber;
    }

}
