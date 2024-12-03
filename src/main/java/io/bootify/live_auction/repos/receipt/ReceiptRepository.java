package io.bootify.live_auction.repos.receipt;

import io.bootify.live_auction.domain.member.Member;
import io.bootify.live_auction.domain.receipt.Receipt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
    Page<Receipt> findAllById(Long id, Pageable pageable);
}
