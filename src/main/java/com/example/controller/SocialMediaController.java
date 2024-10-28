package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.*;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    
    private AccountService accountService;
    private MessageService messageService;
    private ObjectMapper mapper;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService, ObjectMapper mapper){
        this.accountService = accountService;
        this.messageService = messageService;
        this.mapper = mapper;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> registerHandler(@RequestBody Account account){
        try {
            Account newAccount = accountService.addAccount(account);
            if (newAccount != null) {
                String response = mapper.writeValueAsString(newAccount);
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.status(409).build();
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing JSON");
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> loginHandler(@RequestBody Account account){
        try{
            Account loginAccount = accountService.getAccountLogin(account);
            if(loginAccount != null){
                String response = mapper.writeValueAsString(loginAccount);
                return ResponseEntity.ok().body(response);
            }
            return ResponseEntity.status(401).build();
        }catch(JsonProcessingException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing JSON");
        }
        

    }

    @PostMapping(value = "/messages")
    public ResponseEntity<String> newMessageHandler(@RequestBody Message message) {
        try{
            Message newMessage = messageService.addMessage(message);
            if(newMessage != null){
                String response = mapper.writeValueAsString(newMessage);
                return ResponseEntity.ok().body(response);
            }
            return ResponseEntity.status(400).build();

        }catch(JsonProcessingException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing JSON");
        }
    }

    @GetMapping("/messages")
    public ResponseEntity<String> getAllMessagesHandler(){
        try{
            List<Message> messages = messageService.getAllMessages();
            String response = mapper.writeValueAsString(messages);
            return ResponseEntity.ok().body(response);
        }catch(JsonProcessingException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing JSON");
        }
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<String> getMessageByIdHandler(@PathVariable("messageId") int messageId){
        try{
            Message message = messageService.getMessageById(messageId);
            if(message != null){
                String response = mapper.writeValueAsString(message);
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.ok().build();
        }catch(JsonProcessingException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing JSON");
        }
        
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<String> DeleteMessageByIdHandler(@PathVariable("messageId") int messageId){
        try{
        if(messageService.getMessageById(messageId) != null){
            messageService.deleteMessage(messageId);
            return ResponseEntity.ok("1");
        }
        return ResponseEntity.ok().build();
        }catch(Exception e){
            return ResponseEntity.ok().build();
        }
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<String> updateMessageHandler(@PathVariable("messageId") int messageId, @RequestBody Message message){
        try{
            if(messageService.getMessageById(messageId) == null || message.getMessageText().isEmpty() || message.getMessageText().length() > 255){
                return ResponseEntity.status(400).build();
            }

            String updatedMessage = mapper.writeValueAsString(message);
            messageService.updateMessage(messageId, updatedMessage);
            return ResponseEntity.ok("1");

        }catch(JsonProcessingException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing JSON");
        }
    }


    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<String> getUserMessagesHandler(@PathVariable("accountId") int accountId){
        try{
            if(accountService.findAccountByAccountId(accountId) != null){
                List<Message> messages = messageService.getMessagesByAccountId(accountId);
                String response = mapper.writeValueAsString(messages);
                return ResponseEntity.ok().body(response);
            }
            return ResponseEntity.ok().build();
        }catch(JsonProcessingException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing JSON");
        }
        
    }
    
}
