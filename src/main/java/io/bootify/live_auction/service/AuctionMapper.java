package io.bootify.live_auction.service;

import io.bootify.live_auction.domain.Auction;
import io.bootify.live_auction.model.AuctionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;


@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AuctionMapper {

    AuctionDTO updateAuctionDTO(Auction auction, @MappingTarget AuctionDTO auctionDTO);

    @Mapping(target = "id", ignore = true)
    Auction updateAuction(AuctionDTO auctionDTO, @MappingTarget Auction auction);

}
