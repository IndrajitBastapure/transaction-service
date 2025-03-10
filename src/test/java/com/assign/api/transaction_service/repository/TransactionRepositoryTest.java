package com.assign.api.transaction_service.repository;

import com.assign.api.transaction_service.entity.TransactionEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionRepositoryTest {
    @Mock
    private TransactionRepository transactionRepository;

    @Test
    public void testFindByAccountId() {

        String accountId = "account-123";
        TransactionEntity transaction1 = TransactionEntity.builder()
                .id("txn-1")
                .accountId(accountId)
                .amount(new BigDecimal("100.00"))
                .transactionDate(LocalDateTime.now())
                .build();

        TransactionEntity transaction2 = TransactionEntity.builder()
                .id("txn-2")
                .accountId(accountId)
                .amount(new BigDecimal("200.00"))
                .transactionDate(LocalDateTime.now())
                .build();

        Set<TransactionEntity> mockTransactions = new HashSet<>(Arrays.asList(transaction1, transaction2));

        when(transactionRepository.findByAccountId(accountId)).thenReturn(mockTransactions);

        Set<TransactionEntity> transactions = transactionRepository.findByAccountId(accountId);

        assertNotNull(transactions);
        assertEquals(2, transactions.size());
        assertTrue(transactions.contains(transaction1));
        assertTrue(transactions.contains(transaction2));

        verify(transactionRepository, times(1)).findByAccountId(accountId);
    }
}
