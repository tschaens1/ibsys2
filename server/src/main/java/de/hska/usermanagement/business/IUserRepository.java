package de.hska.usermanagement.business;

import de.hska.usermanagement.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
