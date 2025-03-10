package com.assign.api.transaction_service.service;

import com.assign.api.transaction_service.entity.TransactionEntity;
import com.assign.api.transaction_service.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    public TransactionEntity addTransaction(String accountId, BigDecimal amount) {
        TransactionEntity transaction = TransactionEntity.builder()
                .accountId(accountId)
                .amount(amount)
                .transactionDate(LocalDateTime.now())
                .build();
       return transactionRepository.save(transaction);
    }
    public Set<TransactionEntity> getTransactions(String accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
}
