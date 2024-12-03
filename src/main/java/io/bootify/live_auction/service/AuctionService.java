package io.bootify.live_auction.service;

import io.bootify.live_auction.domain.Auction;
import io.bootify.live_auction.model.AuctionDTO;
import io.bootify.live_auction.repos.AuctionRepository;
import io.bootify.live_auction.util.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final AuctionMapper auctionMapper;

    public AuctionService(final AuctionRepository auctionRepository,
            final AuctionMapper auctionMapper) {
        this.auctionRepository = auctionRepository;
        this.auctionMapper = auctionMapper;
    }

    public Page<AuctionDTO> findAll(final String filter, final Pageable pageable) {
        Page<Auction> page;
        if (filter != null) {
            Integer integerFilter = null;
            try {
                integerFilter = Integer.parseInt(filter);
            } catch (final NumberFormatException numberFormatException) {
                // keep null - no parseable input
            }
            page = auctionRepository.findAllById(integerFilter, pageable);
        } else {
            page = auctionRepository.findAll(pageable);
        }
        return new PageImpl<>(page.getContent()
                .stream()
                .map(auction -> auctionMapper.updateAuctionDTO(auction, new AuctionDTO()))
                .toList(),
                pageable, page.getTotalElements());
    }

    public AuctionDTO get(final Integer id) {
        return auctionRepository.findById(id)
                .map(auction -> auctionMapper.updateAuctionDTO(auction, new AuctionDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final AuctionDTO auctionDTO) {
        final Auction auction = new Auction();
        auctionMapper.updateAuction(auctionDTO, auction);
        return auctionRepository.save(auction).getId();
    }

    public void update(final Integer id, final AuctionDTO auctionDTO) {
        final Auction auction = auctionRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        auctionMapper.updateAuction(auctionDTO, auction);
        auctionRepository.save(auction);
    }

    public void delete(final Integer id) {
        auctionRepository.deleteById(id);
    }

    public boolean sellerIdExists(final Integer sellerId) {
        return auctionRepository.existsBySellerId(sellerId);
    }

}
