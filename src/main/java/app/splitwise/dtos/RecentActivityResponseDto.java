package app.splitwise.dtos;

import lombok.Data;

@Data
public class RecentActivityResponseDto {
    private String groupName;
    private String activity;

    public RecentActivityResponseDto(String groupName, String activity) {
        this.groupName = groupName;
        this.activity = activity;
    }
}
