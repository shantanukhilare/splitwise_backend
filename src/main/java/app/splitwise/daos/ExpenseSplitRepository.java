package app.splitwise.daos;

import app.splitwise.dtos.RecentActivityResponseDto;
import app.splitwise.dtos.UserBalanceDto;
import app.splitwise.entities.ExpenseSplit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpenseSplitRepository extends JpaRepository<ExpenseSplit, Long> {

    // How much the user owes to others (group-wise)
    @Query("""
        SELECT new app.splitwise.dtos.UserBalanceDto(
            s.owedTo.id,
            s.owedTo.name,
            SUM(s.amountOwed)
        )
        FROM ExpenseSplit s
        JOIN s.expense e
        JOIN e.expenseGroup g
        WHERE s.user.id = :userId
          AND s.owedTo.id <> :userId
          AND g.id = :groupId
        GROUP BY s.owedTo.id, s.owedTo.name
    """)
    List<UserBalanceDto> getAmountsOwedByUser(
            @Param("userId") Long userId,
            @Param("groupId") Long groupId
    );

    // Who owes the user (group-wise)
    @Query("""
        SELECT new app.splitwise.dtos.UserBalanceDto(
            s.user.id,
            s.user.name,
            SUM(s.amountOwed)
        )
        FROM ExpenseSplit s
        JOIN s.expense e
        JOIN e.expenseGroup g
        WHERE s.owedTo.id = :userId
          AND s.user.id <> :userId
          AND g.id = :groupId
        GROUP BY s.user.id, s.user.name
    """)
    List<UserBalanceDto> getAmountsOwedToUser(
            @Param("userId") Long userId,
            @Param("groupId") Long groupId
    );

    // Aliases with explicit "group-wise" names (same queries as above)

    @Query("""
        SELECT new app.splitwise.dtos.UserBalanceDto(
            s.owedTo.id,
            s.owedTo.name,
            SUM(s.amountOwed)
        )
        FROM ExpenseSplit s
        JOIN s.expense e
        JOIN e.expenseGroup g
        WHERE s.user.id = :userId
          AND s.owedTo.id <> :userId
          AND g.id = :groupId
        GROUP BY s.owedTo.id, s.owedTo.name
    """)
    List<UserBalanceDto> getGroupWiseAmountsIOwe(
            @Param("userId") Long userId,
            @Param("groupId") Long groupId
    );

    @Query("""
        SELECT new app.splitwise.dtos.UserBalanceDto(
            s.user.id,
            s.user.name,
            SUM(s.amountOwed)
        )
        FROM ExpenseSplit s
        JOIN s.expense e
        JOIN e.expenseGroup g
        WHERE s.owedTo.id = :userId
          AND s.user.id <> :userId
          AND g.id = :groupId
        GROUP BY s.user.id, s.user.name
    """)
    List<UserBalanceDto> getGroupWiseAmountsOwedToMe(
            @Param("userId") Long userId,
            @Param("groupId") Long groupId
    );

    @Query("""
        SELECT new app.splitwise.dtos.RecentActivityResponseDto(
          es.expense.expenseGroup.groupName,
          CONCAT(es.expense.paidBy.name, ' paid ', es.expense.amount, ' in group ',
                 es.expense.expenseGroup.groupName, ' for ', es.expense.description)
        )
        FROM ExpenseSplit es
        WHERE es.user.id = :userId
    """)
    List<RecentActivityResponseDto> recentActivity(@Param("userId") Long userId);


    @Query("""
        SELECT new app.splitwise.dtos.RecentActivityResponseDto(
          es.expense.expenseGroup.groupName,
          CONCAT(es.expense.paidBy.name, ' paid ', es.expense.amount, ' in group ',
                 es.expense.expenseGroup.groupName, ' for ', es.expense.description)
        )
        FROM ExpenseSplit es
        WHERE es.user.id = :userId
        AND es.expense.expenseGroup.id= :groupId
    """)
    List<RecentActivityResponseDto> recentActivityByGroupId(@Param("userId") Long userId, @Param("groupId") Long groupId);

}
