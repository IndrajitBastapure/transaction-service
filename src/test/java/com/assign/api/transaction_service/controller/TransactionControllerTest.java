package com.assign.api.transaction_service.controller;

import com.assign.api.transaction_service.dto.mapper.TransactionResponseDTOMapper;
import com.assign.api.transaction_service.dto.request.TransactionRequest;
import com.assign.api.transaction_service.dto.response.TransactionResponse;
import com.assign.api.transaction_service.dto.response.TransactionResponseSet;
import com.assign.api.transaction_service.entity.TransactionEntity;
import com.assign.api.transaction_service.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionControllerTest {
    @Mock
    private TransactionService transactionService;
    @Mock
    private TransactionResponseDTOMapper transactionResponseDTOMapper;

    @InjectMocks
    private TransactionController transactionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTransaction() {
        TransactionRequest request = new TransactionRequest("test-account-id", BigDecimal.valueOf(200.00));

        TransactionEntity savedEntity = TransactionEntity.builder()
                .id("tx-123")
                .accountId("test-account-id")
                .amount(BigDecimal.valueOf(200.00))
                .transactionDate(LocalDateTime.now())
                .build();

        TransactionResponse response = new TransactionResponse(
                "tx-123",
                "test-account-id",
                BigDecimal.valueOf(200.00),
                savedEntity.getTransactionDate()
        );

        when(transactionService.addTransaction(request.getAccountId(), request.getAmount())).thenReturn(savedEntity);
        when(transactionResponseDTOMapper.mapToTransactionResponseDTO(savedEntity)).thenReturn(response);

        TransactionResponse result = transactionController.createTransaction(request);

        assertNotNull(result);
        assertEquals("tx-123", result.getId());
        assertEquals("test-account-id", result.getAccountId());
        assertEquals(BigDecimal.valueOf(200.00), result.getAmount());
        verify(transactionService, times(1)).addTransaction(anyString(), any(BigDecimal.class));
        verify(transactionResponseDTOMapper, times(1)).mapToTransactionResponseDTO(savedEntity);
    }

    @Test
    void testGetTransactionsByAccountId() {
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

        TransactionResponse response1 = new TransactionResponse("tx-1", accountId, transaction1.getAmount(), transaction1.getTransactionDate());
        TransactionResponse response2 = new TransactionResponse("tx-2", accountId, transaction2.getAmount(), transaction2.getTransactionDate());

        when(transactionService.getTransactions(accountId)).thenReturn(Set.of(transaction1, transaction2));
        when(transactionResponseDTOMapper.mapToTransactionResponseDTO(transaction1)).thenReturn(response1);
        when(transactionResponseDTOMapper.mapToTransactionResponseDTO(transaction2)).thenReturn(response2);

        TransactionResponseSet result = transactionController.getTransactionsByAccountId(accountId);
        Set<TransactionResponse> transactionResponses = result.getTransactionResponseSet();

        assertNotNull(result);
        assertEquals(2, transactionResponses.size());
        assertTrue(transactionResponses.contains(response1));
        assertTrue(transactionResponses.contains(response2));

        verify(transactionService, times(1)).getTransactions(accountId);
        verify(transactionResponseDTOMapper, times(1)).mapToTransactionResponseDTO(transaction1);
        verify(transactionResponseDTOMapper, times(1)).mapToTransactionResponseDTO(transaction2);
    }
}
