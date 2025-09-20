package app.splitwise.controllers;

import app.splitwise.dtos.ApiResponse;
import app.splitwise.dtos.LoginRequestDto;
import app.splitwise.dtos.UserCreateRequestBody;
import app.splitwise.entities.User;
import app.splitwise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> findUser(@PathVariable String name) {
        return ResponseEntity.ok(userService.findFriend(name));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserCreateRequestBody payload) {
        ApiResponse msg = userService.registerUser(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto payload) {
        return ResponseEntity.ok(userService.login(payload));
    }

    @PostMapping("/deactivate/{userId}")
    public ResponseEntity<?> deactivateUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.deactivateUser(userId));
    }

}
