package ra.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    Users findAllByUserName (String userName);
    boolean existsByUserName (String userName);
    boolean existsByEmail (String email);
}
