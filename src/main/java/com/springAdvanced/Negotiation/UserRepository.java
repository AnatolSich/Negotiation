package com.springAdvanced.Negotiation;

import com.springAdvanced.Negotiation.model.db.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
