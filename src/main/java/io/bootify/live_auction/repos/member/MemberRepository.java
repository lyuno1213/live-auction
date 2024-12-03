package io.bootify.live_auction.repos.member;

import io.bootify.live_auction.domain.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Integer> {
    Page<Member> findAllById(Long id, Pageable pageable);
}
