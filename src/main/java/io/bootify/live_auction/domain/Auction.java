package io.bootify.live_auction.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import java.time.OffsetDateTime;


@Entity
public class Auction {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Integer id;

    @Column(unique = true)
    private Integer sellerId;

    @Column
    private String productName;

    @Column
    private String pricePolicy;

    @Column
    private Boolean isShowStock;

    @Column
    private Double variationDuration;

    @Column
    private Integer currentPrice;

    @Column
    private Integer currentStock;

    @Column
    private OffsetDateTime startedAt;

    @Column
    private OffsetDateTime finishedAt;

    @Column
    private Integer maximumPurchaseLimitCount;

    @Column
    private Integer originPrice;

    @Column
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
