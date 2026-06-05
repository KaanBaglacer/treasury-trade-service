package com.treasury.treasurytradeservice.model.dto;

import com.treasury.treasurytradeservice.model.enums.TradeSide;
import com.treasury.treasurytradeservice.model.enums.TradeStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TradeResponse {
  private Long id;
  private String currencyPair;
  private BigDecimal amount;
  private TradeSide tradeSide;
  private BigDecimal price;
  private String counterParty;
  private String traderName;
  private TradeStatus tradeStatus;
  private LocalDateTime tradeDate;
  private LocalDateTime settlementDate;
  private LocalDateTime createdAt;
}
