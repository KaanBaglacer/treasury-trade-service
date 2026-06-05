package com.treasury.treasurytradeservice.repository;

import com.treasury.treasurytradeservice.model.entities.Trade;
import com.treasury.treasurytradeservice.model.enums.TradeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {
  List<Trade> findByTradeStatus(TradeStatus status);
  List<Trade> findByCurrencyPair(String currencyPair);
  Optional<Trade> findById(Long id);
}
