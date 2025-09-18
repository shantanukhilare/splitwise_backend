package app.splitwise.dtos;

import app.splitwise.entities.GroupType;
import app.splitwise.entities.User;
import lombok.Data;

import java.util.List;

@Data
public class CreateGroupRequestBody {
    private String name;
    private GroupType groupType;
    private Long createdBy;
    private List<Long> userIds;
}
