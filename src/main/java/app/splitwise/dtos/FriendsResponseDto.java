package app.splitwise.dtos;

import lombok.Data;

import java.util.List;

@Data
public class FriendsResponseDto {
    private Long id;
    private String name;
    private String groups;
    private Double owesYou;
    private Double youOwe;

    public FriendsResponseDto(Long id, String name, String groups, Double owesYou, Double youOwe) {
        this.id = id;
        this.name = name;
        this.groups = groups;
        this.owesYou = owesYou;
        this.youOwe = youOwe;
    }
}
