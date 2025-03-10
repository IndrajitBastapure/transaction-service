package com.assign.api.transaction_service.service;

import com.assign.api.transaction_service.entity.TransactionEntity;
import com.assign.api.transaction_service.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
public class TransactionServiceTest {
    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTransaction() {
        String accountId = "test-account-id";
        BigDecimal amount = BigDecimal.valueOf(100.00);

        TransactionEntity transaction = TransactionEntity.builder()
                .id("test-transaction-id")
                .accountId(accountId)
                .amount(amount)
                .transactionDate(LocalDateTime.now())
                .build();

        when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(transaction);

        TransactionEntity savedTransaction = transactionService.addTransaction(accountId, amount);

        assertNotNull(savedTransaction);
        assertEquals("test-transaction-id", savedTransaction.getId());
        assertEquals(accountId, savedTransaction.getAccountId());
        assertEquals(amount, savedTransaction.getAmount());
        verify(transactionRepository, times(1)).save(any(TransactionEntity.class));
    }

    @Test
    void testGetTransactions() {
        String accountId = "test-account-id";

        TransactionEntity transaction1 = TransactionEntity.builder()
                .id("tx-1")
                .accountId(accountId)
                .amount(BigDecimal.valueOf(50.00))
                .transactionDate(LocalDateTime.now())
                .build();

        TransactionEntity transaction2 = TransactionEntity.builder()
                .id("tx-2")
                .accountId(accountId)
                .amount(BigDecimal.valueOf(150.00))
                .transactionDate(LocalDateTime.now())
                .build();

        when(transactionRepository.findByAccountId(accountId)).thenReturn(Set.of(transaction1, transaction2));

        Set<TransactionEntity> transactions = transactionService.getTransactions(accountId);

        assertNotNull(transactions);
        assertEquals(2, transactions.size());
        verify(transactionRepository, times(1)).findByAccountId(accountId);
    }
}
