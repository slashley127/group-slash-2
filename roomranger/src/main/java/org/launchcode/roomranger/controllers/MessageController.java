package org.launchcode.roomranger.controllers;

import org.launchcode.roomranger.models.ChatMessage;
import org.launchcode.roomranger.models.Dto.ChatMessageDTO;
import org.launchcode.roomranger.service.ChatMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin("http://localhost:3000")
public class MessageController {
    @Autowired
    private ChatMessageService chatMessageService;

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);



    @GetMapping("/messages")
    public ResponseEntity<List<ChatMessageDTO>> getAllMessages() {
        List<ChatMessage> messages = chatMessageService.findAllMessages(); // Assume this service method retrieves all messages
        List<ChatMessageDTO> chatMessageDTOS = convertEntityToDTO(messages);
        logger.info("Messages list size", chatMessageDTOS.size());
        return ResponseEntity.ok(chatMessageDTOS);
    }

    private List<ChatMessageDTO> convertEntityToDTO(List<ChatMessage> messagesEntity) {
        List<ChatMessageDTO>  chatMessageDTOlist = new ArrayList<>();
        for(ChatMessage chatMessageEn:messagesEntity){
            ChatMessageDTO chatMessageDTO = new ChatMessageDTO();
            chatMessageDTO.setText(chatMessageEn.getText());
            chatMessageDTO.setFromName(chatMessageEn.getFromName());
            chatMessageDTOlist.add(chatMessageDTO);
        }
        return  chatMessageDTOlist;
    }

}