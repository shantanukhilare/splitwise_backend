package app.splitwise.dtos;

import lombok.Data;

import java.util.List;

@Data
public class FinalFriendsResponseDto {
    private Long id;
    private String name;
    private List<String> groupsList;
    private Double owesYou;
    private Double youOwe;
}
