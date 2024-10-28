package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer>{
    Account findAccountByAccountId(int accountId);
    Account findAccountByUsername(String username);
    Account findAccountByUsernameAndPassword(String username, String password);
}
