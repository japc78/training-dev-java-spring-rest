package com.japcdev.userapp.models;

public class User {
  private String userName;
  private String password;
  private String nickName;
  /**
   *
   */
  public User() {
  }


  /**
   * @param userName
   * @param password
   * @param nickName
   */
  public User(String nickName, String password, String userName ) {
    this.userName = userName;
    this.password = password;
    this.nickName = nickName;
  }


  /**
   * @return the userName
   */
  public String getUserName() {
    return userName;
  }


  /**
   * @param userName the userName to set
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }


  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }


  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }


  /**
   * @return the nickName
   */
  public String getNickName() {
    return nickName;
  }


  /**
   * @param nickName the nickName to set
   */
  public void setNickName(String nickName) {
    this.nickName = nickName;
  }



}
