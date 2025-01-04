package com.pl.emailOtpVerification.responses;

import lombok.*;

public class RegisterResponse {
    private String userName;
    private String email;

    // Default constructor
    public RegisterResponse() {
    }

    // All-arguments constructor
    public RegisterResponse(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    // Getters and Setters
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

    // Builder pattern
    public static class Builder {
        private String userName;
        private String email;

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public RegisterResponse build() {
            return new RegisterResponse(userName, email);
        }
    }
}

