package com.example.renthub;

public class Users {

    private String email, name, username,id,address,image,contactNo,number,password;

    public Users(String email, String name, String username, String id, String address, String image, String contactNo, String number) {
        this.email = email;
        this.name = name;
        this.username = username;
        this.id = id;
        this.address= address;
        this.image= image;
        this.contactNo=contactNo;
        this.number = number;
    }
    
    public Users(String email, String name, String username, String password) {
        this.email = email;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public Users() {

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String imageUri) {
        image = imageUri;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    /* public String getEmail() {
        return Email;
    }

    public String getName() {
        return Name;
    }

    public String getusername() {
        return username;
    }

    public String getId() {
        return id;
    }

    public String getAddress(){
        return Address;
    }
    public String getImageUri(){return ImageUri;}
    */

}
