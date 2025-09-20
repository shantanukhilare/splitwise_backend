package app.splitwise.dtos;

import lombok.Data;

@Data
public class AddMemberFriendsResponseDto {
    private Long userId;
    private String name;

    public AddMemberFriendsResponseDto(Long userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}
