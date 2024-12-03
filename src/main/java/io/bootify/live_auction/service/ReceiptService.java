package io.bootify.live_auction.service;

import io.bootify.live_auction.domain.Receipt;
import io.bootify.live_auction.model.ReceiptDTO;
import io.bootify.live_auction.repos.ReceiptRepository;
import io.bootify.live_auction.util.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final ReceiptMapper receiptMapper;

    public ReceiptService(final ReceiptRepository receiptRepository,
            final ReceiptMapper receiptMapper) {
        this.receiptRepository = receiptRepository;
        this.receiptMapper = receiptMapper;
    }

    public Page<ReceiptDTO> findAll(final String filter, final Pageable pageable) {
        Page<Receipt> page;
        if (filter != null) {
            Integer integerFilter = null;
            try {
                integerFilter = Integer.parseInt(filter);
            } catch (final NumberFormatException numberFormatException) {
                // keep null - no parseable input
            }
            page = receiptRepository.findAllById(integerFilter, pageable);
        } else {
            page = receiptRepository.findAll(pageable);
        }
        return new PageImpl<>(page.getContent()
                .stream()
                .map(receipt -> receiptMapper.updateReceiptDTO(receipt, new ReceiptDTO()))
                .toList(),
                pageable, page.getTotalElements());
    }

    public ReceiptDTO get(final Integer id) {
        return receiptRepository.findById(id)
                .map(receipt -> receiptMapper.updateReceiptDTO(receipt, new ReceiptDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final ReceiptDTO receiptDTO) {
        final Receipt receipt = new Receipt();
        receiptMapper.updateReceipt(receiptDTO, receipt);
        return receiptRepository.save(receipt).getId();
    }

    public void update(final Integer id, final ReceiptDTO receiptDTO) {
        final Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        receiptMapper.updateReceipt(receiptDTO, receipt);
        receiptRepository.save(receipt);
    }

    public void delete(final Integer id) {
        receiptRepository.deleteById(id);
    }

}
