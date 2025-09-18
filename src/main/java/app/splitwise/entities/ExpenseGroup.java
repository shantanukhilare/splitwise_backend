package app.splitwise.entities;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "expense_groups")
public class ExpenseGroup extends BaseEntity{

	@Column(name = "group_name" , nullable = false, unique = true)
    private String groupName;

    @Column(name = "group_type")
    @Enumerated(EnumType.STRING)
    private GroupType groupType;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;



}
