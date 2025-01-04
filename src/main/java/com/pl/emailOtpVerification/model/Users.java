package com.pl.emailOtpVerification.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name = "USERS")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String email;
    private String password;
    private String otp;
    private boolean verified;

    // Default constructor
    public Users() {
    }

    // All-arguments constructor
    public Users(Long userId, String userName, String email, String password, String otp, boolean verified) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.otp = otp;
        this.verified = verified;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    // Builder pattern
    public static class Builder {
        private Long userId;
        private String userName;
        private String email;
        private String password;
        private String otp;
        private boolean verified;

        public Builder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setOtp(String otp) {
            this.otp = otp;
            return this;
        }

        public Builder setVerified(boolean verified) {
            this.verified = verified;
            return this;
        }

        public Users build() {
            return new Users(userId, userName, email, password, otp, verified);
        }
    }
}
