package com.treasury.treasurytradeservice.controller;

import com.treasury.treasurytradeservice.model.dto.CreateTradeRequest;
import com.treasury.treasurytradeservice.model.dto.TradeResponse;
import com.treasury.treasurytradeservice.model.entities.Trade;
import com.treasury.treasurytradeservice.service.TradeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trades")
@RequiredArgsConstructor
public class TradeController {

  private final TradeService tradeService;

  @PostMapping
  public TradeResponse createTrade(@RequestBody @Valid CreateTradeRequest trade) {
    return tradeService.createTrade(trade);
  }

  @GetMapping
  public List<TradeResponse> getAllTrades() {
    return tradeService.getAllTrades();
  }

  @GetMapping("/{id}")
  public TradeResponse getTradeById(@PathVariable Long id) {
    return tradeService.getTradeById(id);
  }

  @PutMapping("/{id}/cancel")
  public TradeResponse cancelTrade(@PathVariable Long id) {
    return tradeService.cancelTrade(id);
  }
}
