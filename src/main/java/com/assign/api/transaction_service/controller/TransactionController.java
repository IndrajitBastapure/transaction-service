package com.assign.api.transaction_service.controller;

import com.assign.api.transaction_service.dto.mapper.TransactionResponseDTOMapper;
import com.assign.api.transaction_service.dto.request.TransactionRequest;
import com.assign.api.transaction_service.dto.response.TransactionResponse;
import com.assign.api.transaction_service.dto.response.TransactionResponseSet;
import com.assign.api.transaction_service.entity.TransactionEntity;
import com.assign.api.transaction_service.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transaction-service/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    private final TransactionResponseDTOMapper transactionResponseDTOMapper;

    public TransactionController(TransactionService transactionService, TransactionResponseDTOMapper transactionResponseDTOMapper) {
        this.transactionService = transactionService;
        this.transactionResponseDTOMapper = transactionResponseDTOMapper;
    }
    @Operation(summary = "Create a transaction connected to provided accountId", description = "A new transaction is added to provided accountId.", tags ="create")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful response", content = @Content(schema = @Schema(implementation = Double.class))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/create")
    public TransactionResponse createTransaction(@Validated @RequestBody TransactionRequest transactionRequest) {
        TransactionEntity savedTransaction = transactionService.addTransaction(transactionRequest.getAccountId(), transactionRequest.getAmount());

        return transactionResponseDTOMapper.mapToTransactionResponseDTO(savedTransaction);
    }
    @Operation(summary = "Fetches all account transactions related to provided accountId", description = "Returns all transactions for given accountId", tags ="fetch")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful response", content = @Content(schema = @Schema(implementation = Double.class))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{accountId}")
    public TransactionResponseSet getTransactionsByAccountId(@PathVariable String accountId) {
        Set<TransactionEntity> transactionEntities = transactionService.getTransactions(accountId);

        Set<TransactionResponse> transactionResponses = transactionEntities.stream()
                .map(transactionResponseDTOMapper::mapToTransactionResponseDTO)
                .collect(Collectors.toSet());

        return TransactionResponseSet.builder()
                .transactionResponseSet(transactionResponses)
                .build();
    }
}
