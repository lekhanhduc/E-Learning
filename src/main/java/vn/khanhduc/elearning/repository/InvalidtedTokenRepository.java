package vn.khanhduc.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.khanhduc.elearning.entity.InvalidtedToken;

@Repository
public interface InvalidtedTokenRepository extends JpaRepository<InvalidtedToken, String> {
}
