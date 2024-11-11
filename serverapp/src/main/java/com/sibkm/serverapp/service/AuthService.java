package com.sibkm.serverapp.service;

import com.sibkm.serverapp.entity.Employee;
import com.sibkm.serverapp.entity.Role;
import com.sibkm.serverapp.entity.User;
import com.sibkm.serverapp.model.request.LoginRequest;
import com.sibkm.serverapp.model.request.RegistrationRequest;
import com.sibkm.serverapp.model.response.LoginResponse;
import com.sibkm.serverapp.repository.EmployeeRepository;
import com.sibkm.serverapp.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

  private EmployeeRepository employeeRepository;
  private EmployeeService employeeService;
  private RoleService roleService;
  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;
  private AuthenticationManager authManager;
  private AppUserDetailService appUserDetailService;

  public Employee registration(RegistrationRequest registrationRequest) {
    Employee employee = new Employee();
    User user = new User();

    // tarik data Pakai Bean utils
    BeanUtils.copyProperties(registrationRequest, employee);
    BeanUtils.copyProperties(registrationRequest, user);

    // Set Hash Password
    user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

    // Isi roles otomatis ketika input pada registration
    List<Role> roles = Collections.singletonList(roleService.getById(1));
    user.setRoles(roles);

    user.setEmployee(employee);
    employee.setUser(user);

    return employeeRepository.save(employee);
  }

  public Employee addRole(Integer idEmployee, Role role) {
    // 1. cek employee dahulu
    Employee employee = employeeService.getById(idEmployee);
    User user = employee.getUser();

    // 2. cek role
    List<Role> roles = user.getRoles();
    roles.add(roleService.getById(role.getId()));

    // 3. masukin role
    user.setRoles(roles);
    userRepository.save(user);
    return employee;
  }

  public LoginResponse login(LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
    final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
    // Session
    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
        loginRequest.getUsername(), loginRequest.getPassword());

    Authentication authentication = authManager.authenticate(authToken);
    SecurityContext context = securityContextHolderStrategy.createEmptyContext();

    context.setAuthentication(authentication);
    securityContextHolderStrategy.setContext(context);

    HttpSession session = request.getSession(true);
    session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);

    // // authentication
    // UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(
    //     loginRequest.getUsername(),
    //     loginRequest.getPassword());

    // // set principle
    // Authentication auth = authManager.authenticate(authReq);
    // SecurityContext sc = SecurityContextHolder.getContext();
    // sc.setAuthentication(auth);

    // get username for login response
    UserDetails userDetails = appUserDetailService.loadUserByUsername(loginRequest.getUsername());

    // get email for login response
    User user = userRepository
        .findByUsernameOrEmployee_Email(
            loginRequest.getUsername(),
            loginRequest.getUsername())
        .get();

    // get authorities for login response
    List<String> authorities = userDetails
        .getAuthorities()
        .stream()
        .map(authority -> authority.getAuthority())
        .collect(Collectors.toList());

    // set response
    LoginResponse loginResponse = new LoginResponse();
    loginResponse.setUsername(userDetails.getUsername());
    loginResponse.setEmail(user.getEmployee().getEmail());
    loginResponse.setAuthorities(authorities);

    return loginResponse;
  }
}
