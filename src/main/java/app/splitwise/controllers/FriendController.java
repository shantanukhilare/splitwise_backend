package app.splitwise.controllers;

import app.splitwise.dtos.AddFriendRequestDto;
import app.splitwise.services.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/friends")
@CrossOrigin(origins = "*")
public class FriendController {
    @Autowired
    private FriendService friendService;

    @PostMapping()
    public ResponseEntity<?> addFriends(@RequestBody AddFriendRequestDto payload){
        return ResponseEntity.status(HttpStatus.CREATED).body(friendService.addFriend(payload));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getFriendsWithDetails(@PathVariable Long userId){
        return ResponseEntity.ok(friendService.getFriendsList(userId));
    }
}
