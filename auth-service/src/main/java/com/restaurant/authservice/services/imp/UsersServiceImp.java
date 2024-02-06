package com.restaurant.authservice.services.imp;

import com.restaurant.authservice.aggregates.constant.Constants;
import com.restaurant.authservice.aggregates.constant.HttpStatus;
import com.restaurant.authservice.aggregates.response.ResponseReniec;
import com.restaurant.authservice.config.security.CustomerDetailService;
import com.restaurant.authservice.config.security.jwt.JwtUtil;
import com.restaurant.authservice.feignClient.ReniecClient;
import com.restaurant.authservice.repository.EmployeesRepository;
import com.restaurant.authservice.util.mappers.UsersMapper;
import com.restaurant.authservice.aggregates.request.RequestUserLogin;
import com.restaurant.authservice.aggregates.request.RequestUsers;
import com.restaurant.authservice.aggregates.response.ResponseBase;
import com.restaurant.authservice.repository.UsersRepository;
import com.restaurant.authservice.entity.UsersEntity;

import com.restaurant.authservice.services.UsersService;
import com.restaurant.authservice.util.validations.UsersValidations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UsersServiceImp implements UsersService{
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private EmployeesRepository employeesRepository;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private UsersValidations usersValidations;
    @Autowired
    private CustomerDetailService customerDetailService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ReniecClient reniecClient;

    @Value("${token.api.net}")
    private String tokenApi;
    @Override
    public ResponseBase getAllUsers() {
        List<UsersEntity> users = usersRepository.findAll();
        if(users != null)
            return new ResponseBase(HttpStatus.OK,"Usuarios obtenidos", Optional.of(users));
        return new ResponseBase(HttpStatus.BAD_REQUEST,"No existen", Optional.empty());
    }

    @Override
    public ResponseBase login(RequestUserLogin requestUsers) {
        try{
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(requestUsers.getUsername(),requestUsers.getPassword()));
            if(authentication.isAuthenticated()){
                if(customerDetailService.getUser().getStatus() == Constants.ACTIVO){
                    UsersEntity user = customerDetailService.getUser();
                    String token = jwtUtil.generateToken(user.getUsername(),user.getRole().getName());
                    return new ResponseBase(HttpStatus.OK,"Login successful", Optional.of(token));
                }
                return new ResponseBase(HttpStatus.BAD_REQUEST,"Login error", Optional.empty());

            }
            return new ResponseBase(HttpStatus.BAD_REQUEST,"Login error", Optional.empty());

        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseBase(HttpStatus.BAD_REQUEST,"Login error", Optional.empty());
    }

    @Override
    public ResponseBase signin(RequestUsers requestUsers) {
        boolean validate = usersValidations.validateInput(requestUsers);
        if(validate){
            UsersEntity user = usersMapper.getUserEntityFromRequestUser(requestUsers);
            ResponseReniec responseReniec = getExecutionReniec(requestUsers.getDni());
            if(user != null &&  responseReniec !=null){
                user.getEmployee().setDni(responseReniec.getNumeroDocumento());
                user.getEmployee().setName(responseReniec.getNombres());
                user.getEmployee().setLastname(responseReniec.getApellidoPaterno()+" "+responseReniec.getApellidoMaterno());
                employeesRepository.save(user.getEmployee());
                usersRepository.save(user);
                return new ResponseBase(HttpStatus.BAD_REQUEST,Constants.MESS_SUCCESS, Optional.of(user));
            }
            return new ResponseBase(HttpStatus.BAD_REQUEST,Constants.MESS_ERROR_DATA_NOT_FOUND, Optional.empty());
        }
        return new ResponseBase(HttpStatus.BAD_REQUEST, Constants.MESS_ERROR_DATA_NOT_VALID , Optional.empty());
    }

    private ResponseReniec getExecutionReniec (String numero) {
        String authorization = "Bearer "+tokenApi ;
        ResponseReniec responseReniec = reniecClient.getInfoReniec(numero, authorization);
        return responseReniec;
    }
}
