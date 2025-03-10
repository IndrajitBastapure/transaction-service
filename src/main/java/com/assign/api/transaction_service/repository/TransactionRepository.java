package com.assign.api.transaction_service.repository;

import com.assign.api.transaction_service.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {
    Set<TransactionEntity> findByAccountId(String accountId);
}
