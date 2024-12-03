package io.bootify.live_auction.repos;

import io.bootify.live_auction.domain.Auction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuctionRepository extends JpaRepository<Auction, Integer> {

    Page<Auction> findAllById(Integer id, Pageable pageable);

    boolean existsBySellerId(Integer sellerId);

}
