package com.tutomato.api.membership.repository;

import com.tutomato.api.membership.domain.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
}
