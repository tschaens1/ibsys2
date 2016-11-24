package de.hska.inputmanagement.business;

import de.hska.inputmanagement.domain.Input;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInputRepository extends JpaRepository<Input, Long> {
}
