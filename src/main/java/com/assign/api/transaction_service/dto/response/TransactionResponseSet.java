package com.assign.api.transaction_service.dto.response;

import lombok.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponseSet {
    private Set<TransactionResponse> transactionResponseSet;
}
