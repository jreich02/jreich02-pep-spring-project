package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer>{

    public Account findAccountByAccountId(int accountId);
    public List<Account> findAllAccounts();
    public Account findAccountByUsername(String username);
    public Account findAccountByUsernameAndPassword(String username, String password);
}
