package com.fintech.bannkingapp.repo;

import com.fintech.bannkingapp.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction , String > {
}
