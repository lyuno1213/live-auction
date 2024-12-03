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

    public AuctionService(final AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
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
                .map(auction -> mapToDTO(auction, new AuctionDTO()))
                .toList(),
                pageable, page.getTotalElements());
    }

    public AuctionDTO get(final Integer id) {
        return auctionRepository.findById(id)
                .map(auction -> mapToDTO(auction, new AuctionDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final AuctionDTO auctionDTO) {
        final Auction auction = new Auction();
        mapToEntity(auctionDTO, auction);
        return auctionRepository.save(auction).getId();
    }

    public void update(final Integer id, final AuctionDTO auctionDTO) {
        final Auction auction = auctionRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(auctionDTO, auction);
        auctionRepository.save(auction);
    }

    public void delete(final Integer id) {
        auctionRepository.deleteById(id);
    }

    private AuctionDTO mapToDTO(final Auction auction, final AuctionDTO auctionDTO) {
        auctionDTO.setId(auction.getId());
        auctionDTO.setSellerId(auction.getSellerId());
        auctionDTO.setProductName(auction.getProductName());
        auctionDTO.setPricePolicy(auction.getPricePolicy());
        auctionDTO.setIsShowStock(auction.getIsShowStock());
        auctionDTO.setVariationDuration(auction.getVariationDuration());
        auctionDTO.setCurrentPrice(auction.getCurrentPrice());
        auctionDTO.setCurrentStock(auction.getCurrentStock());
        auctionDTO.setStartedAt(auction.getStartedAt());
        auctionDTO.setFinishedAt(auction.getFinishedAt());
        auctionDTO.setMaximumPurchaseLimitCount(auction.getMaximumPurchaseLimitCount());
        auctionDTO.setOriginPrice(auction.getOriginPrice());
        auctionDTO.setOriginStock(auction.getOriginStock());
        return auctionDTO;
    }

    private Auction mapToEntity(final AuctionDTO auctionDTO, final Auction auction) {
        auction.setSellerId(auctionDTO.getSellerId());
        auction.setProductName(auctionDTO.getProductName());
        auction.setPricePolicy(auctionDTO.getPricePolicy());
        auction.setIsShowStock(auctionDTO.getIsShowStock());
        auction.setVariationDuration(auctionDTO.getVariationDuration());
        auction.setCurrentPrice(auctionDTO.getCurrentPrice());
        auction.setCurrentStock(auctionDTO.getCurrentStock());
        auction.setStartedAt(auctionDTO.getStartedAt());
        auction.setFinishedAt(auctionDTO.getFinishedAt());
        auction.setMaximumPurchaseLimitCount(auctionDTO.getMaximumPurchaseLimitCount());
        auction.setOriginPrice(auctionDTO.getOriginPrice());
        auction.setOriginStock(auctionDTO.getOriginStock());
        return auction;
    }

    public boolean sellerIdExists(final Integer sellerId) {
        return auctionRepository.existsBySellerId(sellerId);
    }

}
