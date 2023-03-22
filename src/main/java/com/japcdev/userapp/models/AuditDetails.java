package com.japcdev.userapp.models;

public class AuditDetails {
  private String createdBy;
  private String roleName;

  public AuditDetails() {

  }

  /**
   * @param createdBy
   * @param roleName
   */
  public AuditDetails(String createdBy, String roleName) {
    this.createdBy = createdBy;
    this.roleName = roleName;
  }

  /**
   * @return the createdBy
   */
  public String getCreatedBy() {
    return createdBy;
  }

  /**
   * @param createdBy the createdBy to set
   */
  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  /**
   * @return the roleName
   */
  public String getRoleName() {
    return roleName;
  }

  /**
   * @param roleName the roleName to set
   */
  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }





}
