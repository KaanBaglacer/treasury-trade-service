package com.treasury.treasurytradeservice.messaging;

import com.treasury.treasurytradeservice.config.RabbitMQConfig;
import com.treasury.treasurytradeservice.model.entities.Trade;
import com.treasury.treasurytradeservice.model.event.TradeEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TradeEventProducer {

  private final RabbitTemplate rabbitTemplate;

  public void publishTradeCreated(Trade trade) {
    TradeEvent event = TradeEvent.builder()
        .tradeId(trade.getId())
        .eventType("TRADE_CREATED")
        .currencyPair(trade.getCurrencyPair())
        .amount(trade.getAmount())
        .tradeSide(trade.getTradeSide())
        .price(trade.getPrice())
        .counterParty(trade.getCounterParty())
        .tradeStatus(trade.getTradeStatus())
        .tradeDate(trade.getTradeDate())
        .settlementDate(trade.getSettlementDate())
        .build();

    rabbitTemplate.convertAndSend(
        RabbitMQConfig.TRADE_EXCHANGE,
        RabbitMQConfig.TRADE_ROUTING_KEY,
        event
    );
    log.info("Published TRADE_CREATED event for trade {}", trade.getId());
  }
}