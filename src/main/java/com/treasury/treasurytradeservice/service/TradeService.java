package com.treasury.treasurytradeservice.service;

import com.treasury.treasurytradeservice.exception.CancelTradeException;
import com.treasury.treasurytradeservice.exception.TradeNotFoundException;
import com.treasury.treasurytradeservice.messaging.TradeEventProducer;
import com.treasury.treasurytradeservice.model.dto.CreateTradeRequest;
import com.treasury.treasurytradeservice.model.dto.TradeResponse;
import com.treasury.treasurytradeservice.model.entities.Trade;
import com.treasury.treasurytradeservice.model.enums.TradeStatus;
import com.treasury.treasurytradeservice.repository.TradeRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TradeService {

  private final TradeRepository tradeRepository;
  private final TradeEventProducer tradeEventProducer;

  public TradeResponse createTrade(@Valid CreateTradeRequest createTradeRequest) {
    Trade trade = Trade.builder()
        .currencyPair(createTradeRequest.getCurrencyPair())
        .amount(createTradeRequest.getAmount())
        .tradeSide(createTradeRequest.getTradeSide())
        .price(createTradeRequest.getPrice())
        .counterParty(createTradeRequest.getCounterParty())
        .traderName(createTradeRequest.getTraderName())
        .tradeDate(LocalDateTime.now())
        .settlementDate(LocalDateTime.now().plusDays(2)) // T+2 settlement
        .tradeStatus(TradeStatus.CREATED)
        .build();

    Trade createdTrade = tradeRepository.save(trade);

    tradeEventProducer.publishTradeCreated(createdTrade);  // add this

    return mapToResponse(createdTrade);
  }

  public List<TradeResponse> getAllTrades() {
    List<Trade> tradeList = tradeRepository.findAll();

    return tradeList.stream()
        .map(this::mapToResponse)
        .toList();
  }

  public TradeResponse getTradeById(Long id) {
    Trade trade = tradeRepository.findById(id)
        .orElseThrow(() -> new TradeNotFoundException("Trade not found: " + id));
    return mapToResponse(trade);
  }

  public TradeResponse cancelTrade(Long id) {
    Trade trade = tradeRepository.findById(id)
        .orElseThrow(() -> new TradeNotFoundException("Trade not found: " + id));

    if (trade.getTradeStatus() != TradeStatus.CREATED) {
      throw new CancelTradeException(
          "Can only cancel trades with CREATED status. Current: " + trade.getTradeStatus());
    }

    trade.setTradeStatus(TradeStatus.CANCELLED);
    return mapToResponse(tradeRepository.save(trade));
  }

  private TradeResponse mapToResponse(Trade trade) {
    return TradeResponse.builder()
        .id(trade.getId())
        .currencyPair(trade.getCurrencyPair())
        .amount(trade.getAmount())
        .tradeSide(trade.getTradeSide())
        .price(trade.getPrice())
        .counterParty(trade.getCounterParty())
        .traderName(trade.getTraderName())
        .tradeStatus(trade.getTradeStatus())
        .tradeDate(trade.getTradeDate())
        .settlementDate(trade.getSettlementDate())
        .createdAt(trade.getCreatedAt())
        .build();
  }
}
