package vn.khanhduc.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.khanhduc.elearning.entity.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("Select u from User u where u.email= :email")
    Optional<User> findByEmail(String email);
}

