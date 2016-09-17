package chawla.fireapp;

/**
 * Created by Tushar on 9/16/16.
 */

public class user_class {

    private String first_name_;
    private String last_name_;
    private int userID_;
    private int phone_no;

    public user_class(String first_name_, String last_name_, int userID_, int phone_no) {
        this.first_name_ = first_name_;
        this.last_name_ = last_name_;
        this.userID_ = userID_;
        this.phone_no = phone_no;
    }

    public String getFirst_name_() {
        return first_name_;
    }

    public void setFirst_name_(String first_name_) {
        this.first_name_ = first_name_;
    }

    public String getLast_name_() {
        return last_name_;
    }

    public void setLast_name_(String last_name_) {
        this.last_name_ = last_name_;
    }

    public int getUserID_() {
        return userID_;
    }

    public void setUserID_(int userID_) {
        this.userID_ = userID_;
    }

    public int getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(int phone_no) {
        this.phone_no = phone_no;
    }
}
