package app.splitwise.services.implementations;

import app.splitwise.daos.FriendRepository;
import app.splitwise.daos.UserRepository;
import app.splitwise.dtos.*;
import app.splitwise.entities.Friend;
import app.splitwise.entities.User;
import app.splitwise.services.FriendService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FriendServiceImpl implements FriendService {
    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public ApiResponse addFriend(AddFriendRequestDto payload) {
        User user=userRepository.findById(payload.getUserId()).orElseThrow(()-> new RuntimeException("User not found..."));
        User friend=userRepository.findByNameOrPhoneNumberOrEmailAndIsActiveTrue(payload.getName(),payload.getName(),payload.getName());
        if(friend!=null){
            Friend obj=new Friend();
            obj.setUser(user);
            obj.setFriend(friend);
            friendRepository.save(obj);
            return new ApiResponse("Friends added successfully");
        }
        return new ApiResponse("Something went wrong");
    }

    @Override
    public List<FinalFriendsResponseDto> getFriendsList(Long userId) {
        var result = friendRepository.getFriendsWithDetails(userId);

        return result.stream()
                .map(dto -> {
                    FinalFriendsResponseDto response = mapper.map(dto, FinalFriendsResponseDto.class);
                    if (dto.getGroups() != null && !dto.getGroups().isBlank()) {
                        List<String> groupList = Arrays.asList(dto.getGroups().split(",\\s*"));
                        response.setGroupsList(groupList);
                    } else {
                        response.setGroupsList(Collections.emptyList());
                    }
                    return response;
                })
                .collect(Collectors.toList());
    }


    @Override
    public List<AddMemberFriendsResponseDto> getFriendListToAddMembers(Long userId) {
        return friendRepository.friendList(userId);
    }

}
