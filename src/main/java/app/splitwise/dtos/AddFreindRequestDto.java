package app.splitwise.dtos;

import lombok.Data;

import java.util.List;

@Data
public class AddFriendRequestDto {
    private Long userId;
    private List<Long> friendIds;
}
