package com.treasury.treasurytradeservice.model.dto;

import com.treasury.treasurytradeservice.model.enums.TradeSide;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateTradeRequest {
  @NotBlank
  private String currencyPair;
  @NotNull
  private BigDecimal amount;
  @NotNull
  private TradeSide tradeSide;
  @NotNull
  private BigDecimal price;
  @NotBlank
  private String counterParty;
  @NotBlank
  private String traderName;
}
