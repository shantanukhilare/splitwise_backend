package app.splitwise.services;

import app.splitwise.dtos.*;

import java.util.List;

public interface FriendService {
    ApiResponse addFriend(AddFriendRequestDto payload);
    List<FinalFriendsResponseDto> getFriendsList(Long userId);
    List<AddMemberFriendsResponseDto> getFriendListToAddMembers(Long userId);

}
