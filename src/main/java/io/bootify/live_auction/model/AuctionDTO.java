package io.bootify.live_auction.model;

import jakarta.validation.constraints.Size;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;


public class AuctionDTO {

    private Integer id;

    @AuctionSellerIdUnique
    private Integer sellerId;

    @Size(max = 255)
    private String productName;

    @Size(max = 255)
    private String pricePolicy;

    private Boolean isShowStock;

    private Double variationDuration;

    private Integer currentPrice;

    private Integer currentStock;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime startedAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime finishedAt;

    private Integer maximumPurchaseLimitCount;

    private Integer originPrice;

    private Integer originStock;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
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

    public String getPricePolicy() {
        return pricePolicy;
    }

    public void setPricePolicy(final String pricePolicy) {
        this.pricePolicy = pricePolicy;
    }

    public Boolean getIsShowStock() {
        return isShowStock;
    }

    public void setIsShowStock(final Boolean isShowStock) {
        this.isShowStock = isShowStock;
    }

    public Double getVariationDuration() {
        return variationDuration;
    }

    public void setVariationDuration(final Double variationDuration) {
        this.variationDuration = variationDuration;
    }

    public Integer getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(final Integer currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Integer getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(final Integer currentStock) {
        this.currentStock = currentStock;
    }

    public OffsetDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(final OffsetDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public OffsetDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(final OffsetDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Integer getMaximumPurchaseLimitCount() {
        return maximumPurchaseLimitCount;
    }

    public void setMaximumPurchaseLimitCount(final Integer maximumPurchaseLimitCount) {
        this.maximumPurchaseLimitCount = maximumPurchaseLimitCount;
    }

    public Integer getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(final Integer originPrice) {
        this.originPrice = originPrice;
    }

    public Integer getOriginStock() {
        return originStock;
    }

    public void setOriginStock(final Integer originStock) {
        this.originStock = originStock;
    }

}
