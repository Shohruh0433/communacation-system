package uz.developer.communication_system.service;

import org.springframework.beans.factory.annotation.Autowired;
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
import uz.developer.communication_system.entity.SimCard;
import uz.developer.communication_system.entity.User;
import uz.developer.communication_system.entity.enums.RoleEnum;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.payload.EmployeeDto;
import uz.developer.communication_system.payload.SimCardDto;
import uz.developer.communication_system.repository.EmployeeRepository;
import uz.developer.communication_system.repository.RoleRepository;
import uz.developer.communication_system.repository.SimCardRepository;
import uz.developer.communication_system.repository.UserRepository;
import uz.developer.communication_system.security.JwtProvider;

import java.util.Collections;
import java.util.Optional;

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




    public ApiResponse registerSimCard(SimCardDto simCardDto){

        boolean existByFullNumber = simCardRepository.existsByCodeAndNumber(simCardDto.getCode(), simCardDto.getNumber());
        if (existByFullNumber) return new ApiResponse("Bu nomer avval ro'yxatdan o'ttgan",false);

        Optional<User> optionalUser = userRepository.findById(simCardDto.getUserId());
        if (optionalUser.isEmpty())
         return new ApiResponse("User not found",false);

        SimCard simCard = new SimCard();
        simCard.setRoles(Collections.singleton(roleRepository.findAllByName(RoleEnum.ROLL_CLIENT)));
      simCard.setNumber(simCardDto.getNumber());
      simCard.setCode(simCardDto.getCode());
      simCard.setPassword(simCardDto.getPassword());

       simCardRepository.save(simCard);
       return new ApiResponse("Number royhatdan otdi",true);

    }


    public ApiResponse loginSimCard(SimCardDto simCardDto){
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    simCardDto.getFullNumber(),
                    simCardDto.getPassword()

            ));

            SimCard simCard=(SimCard) authenticate.getPrincipal();
            String token = jwtProvider.generateToken(simCard.getUsername(), simCard.getRoles());
            return new ApiResponse(token,true);

        }catch (BadCredentialsException e){
            return new ApiResponse("parol yoki login xato",false);
        }
    }


    public Object loginEmployee(EmployeeDto employeeDto) {

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



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
     String code = username.substring(0,6);
     String number = username.substring(6);
        Optional<SimCard> optionalSimCard = Optional.ofNullable(simCardRepository.findByCodeAndNumber(code, number));
        if (optionalSimCard.isPresent())
            return optionalSimCard.get();
        throw new UsernameNotFoundException(username+" topilmadi");
    }



}
