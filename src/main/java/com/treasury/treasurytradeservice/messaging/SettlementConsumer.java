package com.treasury.treasurytradeservice.messaging;

import com.treasury.treasurytradeservice.config.RabbitMQConfig;
import com.treasury.treasurytradeservice.model.event.TradeEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SettlementConsumer {

  @RabbitListener(queues = RabbitMQConfig.SETTLEMENT_QUEUE)
  public void handleTradeCreated(TradeEvent event) {
    log.info("Settlement received: {} for trade {}",
        event.getEventType(), event.getTradeId());
    log.info("Processing settlement for {} {} {} at {}",
        event.getTradeSide(), event.getAmount(),
        event.getCurrencyPair(), event.getPrice());
    // In a real system: create settlement record,
    // initiate payment, update trade status
  }
}
