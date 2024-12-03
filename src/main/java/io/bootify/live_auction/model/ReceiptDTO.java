package io.bootify.live_auction.model;

import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;


public class ReceiptDTO {

    private Integer id;

    private Integer auctionId;

    private Integer buyerId;

    private Integer sellerId;

    @Size(max = 255)
    private String productName;

    private Integer price;

    private Integer quantity;

    private ReceiptStatus receiptStatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Integer getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(final Integer auctionId) {
        this.auctionId = auctionId;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(final Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(final Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(final Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    public ReceiptStatus getReceiptStatus() {
        return receiptStatus;
    }

    public void setReceiptStatus(final ReceiptStatus receiptStatus) {
        this.receiptStatus = receiptStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(final LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
