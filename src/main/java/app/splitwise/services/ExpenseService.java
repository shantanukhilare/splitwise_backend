package app.splitwise.services;


import app.splitwise.dtos.*;

import java.util.List;


public interface ExpenseService {
    ApiResponse addExpense(CreateEvenExpenseRequestBody payload);

    ApiResponse addUnevenExpense(CreateUnevenExpenseRequestBody payload);

    Double myContribution(Long paidBy, Long groupId);

    Object getExpensesByGroupId(Long groupId);

    List<RecentActivityResponseDto> recentActivity(Long userId);
}
