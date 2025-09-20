package app.splitwise.daos;

import app.splitwise.dtos.AddMemberFriendsResponseDto;
import app.splitwise.dtos.FriendsResponseDto;
import app.splitwise.entities.Friend;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query(value = "CALL GetFriendsWithDetails(:userId)", nativeQuery = true)
    List<FriendsResponseDto> getFriendsWithDetails(@Param("userId") Long userId);

    @Query("""
        SELECT new app.splitwise.dtos.AddMemberFriendsResponseDto(
            f.friend.id,
            f.friend.name
        )
        FROM Friend f
        JOIN f.friend
        WHERE f.user.id = :userId
        AND f.user.isActive = true
    """)
    List<AddMemberFriendsResponseDto> friendList(@Param("userId") Long userId);
}
