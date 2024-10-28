package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import com.example.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public List<Message> getAllMessages(){
        return messageRepository.getMessages();
    }

    public List<Message> getMessagesByAccountId(int accountId) {
        return messageRepository.getMessagesByAccountId(accountId);
    }

    public Message getMessageById(int id){
        return messageRepository.findMessageByMessageId(id);
    }

    public Message addMessage(Message message){
        if(message.getMessageText().isEmpty() 
        || message.getMessageText().length() > 255 
        || accountRepository.findAccountByAccountId(message.getPostedBy()) == null){
            return null;
        }
        return messageRepository.save(message);
    }

    public void updateMessage(int messageId, String message){
        Message updatedMessage = messageRepository.findMessageByMessageId(messageId);
        updatedMessage.setMessageText(message);
        messageRepository.save(updatedMessage);
    }
    
    public void deleteMessage(int id){
        messageRepository.deleteById(id);;
    }
}
