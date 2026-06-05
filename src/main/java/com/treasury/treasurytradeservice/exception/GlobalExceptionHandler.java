package com.treasury.treasurytradeservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(TradeCreationException.class)
  public ResponseEntity<String> handleTradeCreationException(TradeCreationException ex) {
    log.error("Trade creation failed: {}", ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

  @ExceptionHandler(GetTradesException.class)
  public ResponseEntity<String> handleGetTradeException(GetTradesException ex) {
    log.error("Get trade failed: {}", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  @ExceptionHandler(TradeNotFoundException.class)
  public ResponseEntity<String> handleTradeNotFoundException(TradeNotFoundException ex) {
    log.error("Trade not found: {}", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  @ExceptionHandler(CancelTradeException.class)
  public ResponseEntity<String> handleCancelTradeException(CancelTradeException ex) {
    log.error("Cancel trade failed: {}", ex.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
  }
}
