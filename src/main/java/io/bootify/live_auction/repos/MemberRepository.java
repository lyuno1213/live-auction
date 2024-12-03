package io.bootify.live_auction.repos;

import io.bootify.live_auction.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Integer> {

    Page<Member> findAllById(Integer id, Pageable pageable);

    boolean existsBySignInIdIgnoreCase(String signInId);

    boolean existsByPasswordIgnoreCase(String password);

}
