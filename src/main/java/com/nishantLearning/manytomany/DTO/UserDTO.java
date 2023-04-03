package com.nishantLearning.manytomany.DTO;

public class UserDTO {
    private Long id;
    private String userName;
    private String mobileNo;
    

    public UserDTO() {
    }

    public UserDTO(Long id, String userName, String mobileNo) {
        this.id = id;
        this.userName = userName;
        this.mobileNo = mobileNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                '}';
    }
}
