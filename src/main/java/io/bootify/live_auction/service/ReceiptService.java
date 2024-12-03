package io.bootify.live_auction.service;

import io.bootify.live_auction.domain.receipt.Receipt;
import io.bootify.live_auction.model.ReceiptDTO;
import io.bootify.live_auction.repos.receipt.ReceiptRepository;
import io.bootify.live_auction.util.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;

    public ReceiptService(final ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    public Page<ReceiptDTO> findAll(final String filter, final Pageable pageable) {
        Page<Receipt> page;
        if (filter != null) {
            Long integerFilter = null;
            try {
                integerFilter = Long.parseLong(filter);
            } catch (final NumberFormatException numberFormatException) {
                // keep null - no parseable input
            }
            page = receiptRepository.findAllById(integerFilter, pageable);
        } else {
            page = receiptRepository.findAll(pageable);
        }
        return new PageImpl<>(page.getContent()
                .stream()
                .map(receipt -> mapToDTO(receipt, new ReceiptDTO()))
                .toList(),
                pageable, page.getTotalElements());
    }

    public ReceiptDTO get(final Integer id) {
        return receiptRepository.findById(id)
                .map(receipt -> mapToDTO(receipt, new ReceiptDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final ReceiptDTO receiptDTO) {
        final Receipt receipt = new Receipt();
        mapToEntity(receiptDTO, receipt);
        return receiptRepository.save(receipt).getId();
    }

    public void update(final Integer id, final ReceiptDTO receiptDTO) {
        final Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(receiptDTO, receipt);
        receiptRepository.save(receipt);
    }

    public void delete(final Integer id) {
        receiptRepository.deleteById(id);
    }

    private ReceiptDTO mapToDTO(final Receipt receipt, final ReceiptDTO receiptDTO) {
        receiptDTO.setId(receipt.getId());
        receiptDTO.setAuctionId(receipt.getAuctionId());
        receiptDTO.setBuyerId(receipt.getBuyerId());
        receiptDTO.setSellerId(receipt.getSellerId());
        receiptDTO.setProductName(receipt.getProductName());
        receiptDTO.setPrice(receipt.getPrice());
        receiptDTO.setQuantity(receipt.getQuantity());
        receiptDTO.setReceiptStatus(receipt.getReceiptStatus());
        receiptDTO.setCreatedAt(receipt.getCreatedAt());
        receiptDTO.setUpdatedAt(receipt.getUpdatedAt());
        return receiptDTO;
    }

    private Receipt mapToEntity(final ReceiptDTO receiptDTO, final Receipt receipt) {
        receipt.setAuctionId(receiptDTO.getAuctionId());
        receipt.setBuyerId(receiptDTO.getBuyerId());
        receipt.setSellerId(receiptDTO.getSellerId());
        receipt.setProductName(receiptDTO.getProductName());
        receipt.setPrice(receiptDTO.getPrice());
        receipt.setQuantity(receiptDTO.getQuantity());
        receipt.setReceiptStatus(receiptDTO.getReceiptStatus());
        receipt.setCreatedAt(receiptDTO.getCreatedAt());
        receipt.setUpdatedAt(receiptDTO.getUpdatedAt());
        return receipt;
    }

}
