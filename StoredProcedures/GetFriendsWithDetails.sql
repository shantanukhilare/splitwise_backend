CREATE PROCEDURE GetFriendsWithDetails(IN input_user_id BIGINT)
BEGIN
    SELECT 
        u.id AS id,
        u.name AS name,

        -- Comma-separated list of shared groups
        (
            SELECT GROUP_CONCAT(DISTINCT eg.group_name SEPARATOR ', ')
            FROM expense_groups eg
            JOIN expenses e ON e.expense_group = eg.id
            JOIN expense_splits es1 ON es1.expense_id = e.id
            JOIN expense_splits es2 ON es2.expense_id = e.id
            WHERE es1.user_id = f.friend_id
              AND es2.user_id = f.user_id
              AND f.friend_id IN (es1.user_id, es2.user_id)
        ) AS `groups`,

        -- Friend owes to user
        (
            SELECT IFNULL(SUM(e.amount), 0)
            FROM expenses e
            JOIN expense_splits es ON es.expense_id = e.id
            WHERE es.user_id = f.friend_id
              AND e.paid_by = f.user_id
        ) AS owesYou,

        -- User owes to friend
        (
            SELECT IFNULL(SUM(es.amount), 0)
            FROM expenses e
            JOIN expense_splits es ON es.expense_id = e.id
            WHERE es.user_id = f.user_id
              AND e.paid_by = f.friend_id
        ) AS youOwe

    FROM friends f
    JOIN users u ON u.id = f.friend_id
    WHERE f.user_id = input_user_id;
END;
