package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class MessageService {
    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAllMessages();
    }

    public List<Message> getAllMessagesByAccountId(int accountId) {
        return messageRepository.findMessagesByAccountId(accountId);
    }

    public Message getMessageByID(int id){
        return messageRepository.findMessageByMessageId(id);
    }

    public void deleteMessage(int id){
        messageRepository.deleteByMessageId(id);;
    }
}
