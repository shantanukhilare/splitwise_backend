package app.splitwise.dtos;

import lombok.Data;

@Data
public class GroupMembersResponseBody {
    private Long id;
    private String name;

    public GroupMembersResponseBody(Long id,String name) {
        this.id=id;
        this.name = name;
    }
}
