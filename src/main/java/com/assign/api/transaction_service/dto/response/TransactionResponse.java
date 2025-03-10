package com.assign.api.transaction_service.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponse {
    private String  id;
    private String  accountId;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
}
