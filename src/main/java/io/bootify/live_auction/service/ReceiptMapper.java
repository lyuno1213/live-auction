package io.bootify.live_auction.service;

import io.bootify.live_auction.domain.Receipt;
import io.bootify.live_auction.model.ReceiptDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;


@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ReceiptMapper {

    ReceiptDTO updateReceiptDTO(Receipt receipt, @MappingTarget ReceiptDTO receiptDTO);

    @Mapping(target = "id", ignore = true)
    Receipt updateReceipt(ReceiptDTO receiptDTO, @MappingTarget Receipt receipt);

}
