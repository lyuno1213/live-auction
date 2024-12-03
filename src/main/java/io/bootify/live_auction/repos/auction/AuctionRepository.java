package io.bootify.live_auction.repos.auction;

import io.bootify.live_auction.domain.auction.Auction;
import io.bootify.live_auction.domain.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuctionRepository extends JpaRepository<Auction, Integer> {
    Page<Auction> findAllById(Long id, Pageable pageable);
}
