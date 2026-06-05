package com.treasury.treasurytradeservice.model.entities;

import com.treasury.treasurytradeservice.model.enums.TradeSide;
import com.treasury.treasurytradeservice.model.enums.TradeStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "trades")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trade {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String currencyPair;

  @Column(nullable = false, precision = 19, scale = 6)
  private BigDecimal amount;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TradeSide tradeSide;

  @Column(nullable = false, precision = 19, scale = 6)
  private BigDecimal price;

  @Column(nullable = false)
  private String counterParty;

  @Column(nullable = false)
  private String traderName;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TradeStatus tradeStatus;

  @Column(nullable = false)
  private LocalDateTime tradeDate;

  private LocalDateTime settlementDate;

  @CreationTimestamp
  @Column(nullable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;
}
