package com.treasury.treasurytradeservice.model.event;

import com.treasury.treasurytradeservice.model.enums.TradeSide;
import com.treasury.treasurytradeservice.model.enums.TradeStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeEvent {
  private Long tradeId;
  private String eventType;
  private String currencyPair;
  private BigDecimal amount;
  private TradeSide tradeSide;
  private BigDecimal price;
  private String counterParty;
  private TradeStatus tradeStatus;
  private LocalDateTime tradeDate;
  private LocalDateTime settlementDate;
}
