package app.splitwise.services;

import app.splitwise.dtos.AddFriendRequestDto;
import app.splitwise.dtos.ApiResponse;
import app.splitwise.dtos.FriendsResponseDto;

import java.util.List;

public interface FriendService {
    ApiResponse addFriend(AddFriendRequestDto payload);
    List<FriendsResponseDto> getFriendsList(Long userId);

}
