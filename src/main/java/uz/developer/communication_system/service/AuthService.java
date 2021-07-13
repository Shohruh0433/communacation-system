package uz.developer.communication_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import uz.developer.communication_system.entity.User;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.payload.LoginDto;
import uz.developer.communication_system.payload.RegisterDto;
import uz.developer.communication_system.repository.RoleRepository;
import uz.developer.communication_system.repository.UserRepository;
import uz.developer.communication_system.security.JwtProvider;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
     JavaMailSender javaMailSender;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;




    public ApiResponse register(RegisterDto registerDto){
        User user=new User();
        boolean existsByEmail = userRepository.existsByEmail(registerDto.getEmail());
        if (existsByEmail) return new ApiResponse("Bu email avval ro'yxatdan o'ttgan",false);

        user.setEmail(registerDto.getEmail());
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRoles(Collections.singleton(roleRepository.findAllByName(RoleEnum.ROLL_USER)));
        user.setEmailCode(UUID.randomUUID().toString());
        userRepository.save(user);
        boolean sendEmail = sendEmail(registerDto.getEmail(), user.getEmailCode());
        if (sendEmail)
        return new ApiResponse("Muvaffaqiyatli ro'yxatdan o'tdingiz. Akkountingizni aktivlashtirish uchun emailingizni tasdiqlang",true);
        else
            return new ApiResponse("Emailga xabar yuborishda xatolik",false);

    }

    public boolean sendEmail(String sendingEmail,String emailCode){
        try {
            SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
            simpleMailMessage.setFrom("Askarov3Shohruh@gmail.com");
            simpleMailMessage.setTo(sendingEmail);
            simpleMailMessage.setSubject("Akkountni tasdiqlash");
            simpleMailMessage.setText("<a href='http://10.100.201.234:8080/api/auth/verifyEmail" +
                    "?emailCode="+emailCode+"&email="+sendingEmail+"'>Tasdiqlang</a>");
            javaMailSender.send(simpleMailMessage);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public ApiResponse verifyEmail(String emailCode,String email){
        Optional<User> optionalUser = userRepository.findByEmailAndEmailCode(email, emailCode);
        if (optionalUser.isPresent()){
            User user= optionalUser.get();
            user.setEnabled(true);
            user.setEmailCode(null);
            userRepository.save(user);
            return new ApiResponse("Aktivatsiya tasdiqlandi",true);


        }
        return new ApiResponse("Tasdiqlanmadi",false);

    }

    public ApiResponse login(LoginDto loginDto){
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getEmail(),
                    loginDto.getPassword()

            ));

            User user=(User) authenticate.getPrincipal();
            String token = jwtProvider.generateToken(user.getEmail(), user.getRoles());
            return new ApiResponse(token,true);

        }catch (BadCredentialsException e){
            return new ApiResponse("parol yoki login xato",false);
        }
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        if (optionalUser.isPresent())
            return optionalUser.get();
        throw new UsernameNotFoundException(username+" topilmadi");
    }
}
