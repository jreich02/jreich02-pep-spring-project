package com.example.repository;

import com.example.entity.Account;
import com.example.entity.Message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Account, Integer>{
    
    public Message findMessageByMessageId(int messageId);
    public List<Message> findAllMessages();
    public List<Message> findMessagesByAccountId(int accountId);
    public void deleteByMessageId(int messageId);

}
