package com.treasury.treasurytradeservice.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  public static final String TRADE_EXCHANGE = "trade-exchange";
  public static final String SETTLEMENT_QUEUE = "settlement-queue";
  public static final String TRADE_ROUTING_KEY = "trade.created";

  @Bean
  public DirectExchange tradeExchange() {
    return new DirectExchange(TRADE_EXCHANGE);
  }

  @Bean
  public Queue settlementQueue() {
    return new Queue(SETTLEMENT_QUEUE, true);  // durable
  }

  @Bean
  public Binding binding(Queue settlementQueue, DirectExchange tradeExchange) {
    return BindingBuilder.bind(settlementQueue)
        .to(tradeExchange)
        .with(TRADE_ROUTING_KEY);
  }

  @Bean
  public JacksonJsonMessageConverter messageConverter() {
    return new JacksonJsonMessageConverter();
  }

  @Bean
  public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                       JacksonJsonMessageConverter converter) {
    RabbitTemplate template = new RabbitTemplate(connectionFactory);
    template.setMessageConverter(converter);
    return template;
  }
}