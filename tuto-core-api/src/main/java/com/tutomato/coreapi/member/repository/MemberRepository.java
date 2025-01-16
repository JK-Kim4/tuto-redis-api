package com.tutomato.coreapi.member.repository;

import com.tutomato.climbinggymapi.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query(value = "select * From member as m left join gym as g on m.gym_name = g.name", nativeQuery = true)
    List<Member> findAllMemberWithGym();
}
