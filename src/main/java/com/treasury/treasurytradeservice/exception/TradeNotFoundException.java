package com.treasury.treasurytradeservice.exception;

public class TradeNotFoundException extends RuntimeException {
  public TradeNotFoundException(String message) {
    super(message);
  }
}
