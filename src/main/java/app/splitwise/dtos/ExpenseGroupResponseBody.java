package app.splitwise.dtos;

import app.splitwise.entities.GroupType;
import lombok.Data;

@Data
public class ExpenseGroupResponseBody {
    private Long groupId;
    private String groupName;
    private Long memberCount;
    private GroupType groupType;

    public ExpenseGroupResponseBody(Long groupId, String groupName, Long memberCount,GroupType groupType) {
        this.groupId=groupId;
        this.groupName = groupName;
        this.memberCount = memberCount;
        this.groupType = groupType;
    }
}
