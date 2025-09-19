package app.splitwise.dtos;

import lombok.Data;

import java.util.List;

@Data
public class FriendsResponseDto {
    private Long id;
    private String name;
    private List<String> groups;
    private Double owesYou;
    private Double youOwe;

}
