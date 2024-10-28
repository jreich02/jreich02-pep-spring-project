package com.example.repository;

import com.example.entity.Message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer>{
    
    Message findMessageByMessageId(Integer messageId);

    @Query("SELECT m FROM Message m")
    List<Message> getMessages();

    @Query("SELECT m FROM Message m WHERE m.postedBy = :accountId")
    List<Message> getMessagesByAccountId(int accountId);

    void deleteById(int id);

}
