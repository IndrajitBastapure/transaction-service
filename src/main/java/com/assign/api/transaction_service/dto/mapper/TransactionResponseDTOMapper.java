package com.assign.api.transaction_service.dto.mapper;

import com.assign.api.transaction_service.dto.response.TransactionResponse;
import com.assign.api.transaction_service.entity.TransactionEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionResponseDTOMapper {
    public TransactionResponse mapToTransactionResponseDTO(TransactionEntity transactionEntity){
        return TransactionResponse.builder()
                .id(transactionEntity.getId())
                .accountId(transactionEntity.getAccountId())
                .amount(transactionEntity.getAmount())
                .transactionDate(transactionEntity.getTransactionDate())
                .build();
    }
}
