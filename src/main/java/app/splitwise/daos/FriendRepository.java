package app.splitwise.daos;

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
}
