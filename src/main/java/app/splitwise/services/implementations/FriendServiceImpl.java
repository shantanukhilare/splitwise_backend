package app.splitwise.services.implementations;

import app.splitwise.daos.FriendRepository;
import app.splitwise.daos.UserRepository;
import app.splitwise.dtos.AddFriendRequestDto;
import app.splitwise.dtos.ApiResponse;
import app.splitwise.dtos.FriendsResponseDto;
import app.splitwise.entities.Friend;
import app.splitwise.entities.User;
import app.splitwise.services.FriendService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FriendServiceImpl implements FriendService {
    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ApiResponse addFriend(AddFriendRequestDto payload) {
        User user=userRepository.findById(payload.getUserId()).orElseThrow(()-> new RuntimeException("User not found..."));
        List<Friend> friendList=new ArrayList<>();
        for (Long id:payload.getFriendIds()){
            Friend friend=new Friend();
            User friendById=userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found..."));
            friend.setFriend(friendById);
            friend.setUser(user);
            friendList.add(friend);
        }
        friendRepository.saveAll(friendList);
        return new ApiResponse("Friends added successfully");
    }

    @Override
    public List<FriendsResponseDto> getFriendsList(Long userId) {
        return friendRepository.getFriendsWithDetails(userId);
    }
}
