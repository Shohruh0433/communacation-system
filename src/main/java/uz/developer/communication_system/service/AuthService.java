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

import uz.developer.communication_system.entity.Employee;
import uz.developer.communication_system.entity.Role;
import uz.developer.communication_system.entity.SimCard;
import uz.developer.communication_system.entity.User;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.payload.EmployeeLoginDto;
import uz.developer.communication_system.payload.EmployeeRegisterDto;
import uz.developer.communication_system.payload.SimCardDto;
import uz.developer.communication_system.payload.model.Passport;
import uz.developer.communication_system.repository.EmployeeRepository;
import uz.developer.communication_system.repository.RoleRepository;
import uz.developer.communication_system.repository.SimCardRepository;
import uz.developer.communication_system.repository.UserRepository;
import uz.developer.communication_system.security.JwtProvider;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    SimCardRepository simCardRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    SimCardService simCardService;
    @Autowired
    JavaMailSender javaMailSender;



    public ApiResponse loginSimCard(SimCardDto simCardDto){
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    simCardDto.getSimCardNumber(),
                    simCardDto.getPinCode()

            ));

            SimCard simCard=(SimCard) authenticate.getPrincipal();
            String token = jwtProvider.generateToken(simCard.getUsername(), simCard.getRoles());
            return new ApiResponse(token,true);

        }catch (BadCredentialsException e){
            return new ApiResponse("parol yoki login xato",false);
        }
    }


    public Object loginEmployee(EmployeeLoginDto employeeDto) {

        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    employeeDto.getUsername(),
                    employeeDto.getPassword()

            ));

            Employee employee=(Employee) authenticate.getPrincipal();
            String token = jwtProvider.generateToken(employee.getUsername(), employee.getRoles());
            return new ApiResponse(token,true);

        }catch (BadCredentialsException e){
            return new ApiResponse("parol yoki login xato",false);
        }

    }

    public ApiResponse registerEmployee(EmployeeRegisterDto employeeRegisterDto) {

        ApiResponse apiResponse = simCardService.getPassportBySeriesAndNumber(
                employeeRegisterDto.getPassportSeries(), employeeRegisterDto.getPassportNumber());

        if (!apiResponse.isSuccess())
            return apiResponse;

        Set<Role> roles = (Set<Role>) roleRepository.findAllById(employeeRegisterDto.getRoles());
        if (roles.isEmpty())
            return new ApiResponse("Roles not found",false);

        if (employeeRepository.existsByEmail(employeeRegisterDto.getEmail()))
            return new ApiResponse("already exist email",false);

        User user = (User) apiResponse.getObject();
        userRepository.save(user);
        Employee employee = new Employee();
        employee.setUser(user);
        employee.setUsername(employeeRegisterDto.getUsername());
        employee.setRoles(roles);
        employee.setEmail(employeeRegisterDto.getEmail());


        Boolean sendEmail = sendEmail(employee.getUsername(), employee.getPassword(), employee.getEmail());
       if (!sendEmail)
           return new ApiResponse(" not send email",false);

        employeeRepository.save(employee);
        return new ApiResponse("Employee registered",true);

    }




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SimCard> optionalSimCard = simCardRepository.findBySimCardNumber(username);
        if (optionalSimCard.isPresent())
            return optionalSimCard.get();
        throw new UsernameNotFoundException(username+" topilmadi");
    }

    public UserDetails loadClientByUsernameFromSimCard(String simCardNumber)  {

        return simCardRepository.findBySimCardNumber(simCardNumber).orElseThrow(() ->
                new UsernameNotFoundException(simCardNumber + "topilmadi"));

    }

    public Boolean sendEmail(String username, String password,String sendingEmail) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("smartofficial777@gmail.com");
            mailMessage.setTo(sendingEmail);
            mailMessage.setSubject("Login and Password");
            mailMessage.setText("Your Login: "+username+"\nYour Password: "+password );
            javaMailSender.send(mailMessage);
            return true;
        } catch (Exception e) {
            return false;
        }

    }


    //    public ApiResponse registerSimCard(SimCardDto simCardDto){
//
//        boolean existByFullNumber = simCardRepository.existsByCodeAndNumber(simCardDto.getCode(), simCardDto.getNumber());
//        if (existByFullNumber) return new ApiResponse("Bu nomer avval ro'yxatdan o'ttgan",false);
//
//        Optional<User> optionalUser = userRepository.findById(simCardDto.getUserId());
//        if (optionalUser.isEmpty())
//         return new ApiResponse("User not found",false);
//
//        SimCard simCard = new SimCard();
//        simCard.setRoles(Collections.singleton(roleRepository.findAllByName(RoleEnum.ROLL_CLIENT)));
//      simCard.setNumber(simCardDto.getNumber());
//      simCard.setCode(simCardDto.getCode());
//      simCard.setPassword(simCardDto.getPassword());
//
//       simCardRepository.save(simCard);
//       return new ApiResponse("Number royhatdan otdi",true);
//
//    }



}
