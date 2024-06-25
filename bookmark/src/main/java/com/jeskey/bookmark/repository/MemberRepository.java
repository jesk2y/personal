package com.jeskey.bookmark.repository;


import com.jeskey.bookmark.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

/*    @EntityGraph(attributePaths = "roleSet")
    Optional<Member> getMemberByEmail(String email);*/
}
