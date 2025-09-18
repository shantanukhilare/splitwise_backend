package app.splitwise.daos;

import app.splitwise.dtos.RecentActivityResponseDto;
import app.splitwise.entities.Expense;
import app.splitwise.entities.ExpenseSplit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    @Query(value = "SELECT SUM(e.amount) from Expense e where e.paidBy.id = :paidBy and e.expenseGroup.id = :groupId")
    Double myContributionInGroup(@Param("paidBy") Long paidBy, @Param("groupId") Long groupId);

    List<Expense> findByExpenseGroupIdOrderByIdDesc(Long groupId);

    @Query("select e from Expense e where e.expenseGroup.id = :groupId order by e.id desc")
    List<Expense> findByGroup(Long groupId);

//    SELECT
//    eg.group_name,
//    CONCAT(u.name, ' paid ', e.amount, ' in group ', eg.group_name, ' for ', e.description) AS statement
//    FROM expenses e
//    INNER JOIN expense_splits es ON es.expense_id = e.id
//    INNER JOIN expense_groups eg ON eg.id = e.expense_group
//    INNER JOIN users u ON u.id = e.paid_by;

    @Query("""
        SELECT new app.splitwise.dtos.RecentActivityResponseDto(
          e.expenseGroup.groupName,
          CONCAT(u.name, ' paid ', e.amount, ' in group ', e.expenseGroup.groupName, ' for ', e.description)
        )
        FROM Expense e
        JOIN e.expenseGroup eg
        JOIN e.paidBy u
        """)
    List<RecentActivityResponseDto> recentActivity(@Param("userId") Long userId);
}
