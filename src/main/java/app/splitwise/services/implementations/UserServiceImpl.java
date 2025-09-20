package app.splitwise.services.implementations;

import app.splitwise.daos.UserRepository;
import app.splitwise.dtos.ApiResponse;
import app.splitwise.dtos.LoginRequestDto;
import app.splitwise.dtos.LoginResponseDto;
import app.splitwise.dtos.UserCreateRequestBody;
import app.splitwise.entities.User;
import app.splitwise.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public ApiResponse registerUser(UserCreateRequestBody payload) {
        if(!userRepository.existsByNameOrPhoneNumberOrEmailAndIsActiveTrue(payload.getName(),payload.getPhoneNumber(),payload.getEmail())){
        User user= modelMapper.map(payload,User.class);
        user.setPassword(payload.getPassword());
        user.setActive(true);
        User user1=userRepository.save(user);
        return new ApiResponse("User created successfully");
        }
        else
            return new ApiResponse("User already Exists bro...");
    }

    @Override
    public LoginResponseDto login(LoginRequestDto payload) {
        var credentials = userRepository.findByNameOrPhoneNumberOrEmailAndIsActiveTrue(
                payload.getCredentials(),
                payload.getCredentials(),
                payload.getCredentials()
        );
        LoginResponseDto response = new LoginResponseDto();
        if (credentials == null) {
            return null;
        }
        response.setName(credentials.getName());
        response.setEmail(credentials.getEmail());
        response.setPhoneNumber(credentials.getPhoneNumber());
        response.setId(credentials.getId());

        if(payload.getPassword().equals(credentials.getPassword())){
            return response;
        }
        return null;
    }

    @Override
    public ApiResponse deactivateUser(Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setActive(false);
        userRepository.save(user);

        return new ApiResponse("User " + user.getName() + " Deactivated Successfully");
    }

    @Override
    public User findFriend(String name) {
        return userRepository.findByNameOrPhoneNumberOrEmailAndIsActiveTrue(name,name,name);
    }


}
