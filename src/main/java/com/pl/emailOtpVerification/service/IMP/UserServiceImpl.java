package com.pl.emailOtpVerification.service.IMP;

import com.pl.emailOtpVerification.model.Users;
import com.pl.emailOtpVerification.repository.UserRepository;
import com.pl.emailOtpVerification.requests.RegisterRequest;
import com.pl.emailOtpVerification.responses.RegisterResponse;
import com.pl.emailOtpVerification.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    // Constructor for dependency injection
    public UserServiceImpl(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }
    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        Users existingUser = userRepository.findByEmail(registerRequest.getEmail());
        if (existingUser != null && existingUser.isVerified()){
            throw new RuntimeException("User Already Registered");
        }
//        Optional<Users> existingUser = Optional.ofNullable(userRepository.findByEmail(registerRequest.getEmail()));
//        if (existingUser.isPresent() && existingUser.get().isVerified()) {
//            throw new RuntimeException("User Already Registered");
//        }

        Users users = new Users.Builder()
                .setUserName(registerRequest.getUserName())
                .setEmail(registerRequest.getEmail())
                .setPassword(registerRequest.getPassword())
                .build();

        String otp = generateOTP();
        users.setOtp(otp);

        Users savedUser = userRepository.save(users);
        sendVerificationEmail(savedUser.getEmail(),otp);

        RegisterResponse response = new RegisterResponse.Builder()
                .setUserName(users.getUserName())
                .setEmail(users.getEmail())
                .build();
        return response;
    }

    private String generateOTP(){
        Random random = new Random();
        int otpValue = 100000 + random.nextInt(900000);
        return String.valueOf(otpValue);
    }

    private void sendVerificationEmail(String email,String otp){
        String subject = "Email verification";
        String body ="your verification otp is: "+otp;
        emailService.sendEmail(email,subject,body);
    }


    @Override
    public void verify(String email, String otp) {
        Users users = userRepository.findByEmail(email);
        if (users == null){
            throw new RuntimeException("User not found");
        } else if (users.isVerified()) {
            throw new RuntimeException("User is already verified");
        } else if (otp.equals(users.getOtp())) {
            users.setVerified(true);
            userRepository.save(users);
        }else {
            throw new RuntimeException("Internal Server error");
        }
    }

    @Override
    public Users login(String email, String password) {
        Users byEmail = userRepository.findByEmail(email);
        if(byEmail != null && byEmail.isVerified() && byEmail.getPassword().equals(password)){
            return byEmail;
        }
        else{
            throw new RuntimeException("Error occured");
        }
    }
}
