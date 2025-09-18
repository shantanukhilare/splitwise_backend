package app.splitwise.daos;

import app.splitwise.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByNameOrPhoneNumberOrEmailAndIsActiveTrue(String name, String phoneNumber, String email);

    User findByNameOrPhoneNumberOrEmailAndIsActiveTrue(String name, String phoneNumber, String email);

}
