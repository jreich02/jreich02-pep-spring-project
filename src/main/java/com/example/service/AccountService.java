package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts(){
        return accountRepository.findAllAccounts();
    }

    public Account getAccountByAccountId(int id){
        return accountRepository.findAccountByAccountId(id);
    }

    public Account getAccountByUsername(String username){
        return accountRepository.findAccountByUsername(username);
    }

    public Account getAccountByUsernameAndPassword(String username, String password){
        return accountRepository.findAccountByUsernameAndPassword(username, password);
    }
    public Account addAccount(Account account){

        if(accountRepository.findAccountByUsername(account.getUsername()) != null || account.getUsername().isEmpty() || account.getPassword().length() < 4){
            return null;
        }
        return accountRepository.save(account);
    }

    
}
