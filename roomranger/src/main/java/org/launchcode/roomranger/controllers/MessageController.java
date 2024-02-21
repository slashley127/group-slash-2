package org.launchcode.roomranger.controllers;

import org.launchcode.roomranger.models.ChatMessage;
import org.launchcode.roomranger.models.Dto.ChatMessageResponseDTO;
import org.launchcode.roomranger.service.ChatMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<ChatMessageResponseDTO>> getAllMessages() {
        List<ChatMessage> messages = chatMessageService.findAllMessages(); // Assume this service method retrieves all messages
        List<ChatMessageResponseDTO> chatMessageResponseDTOS = convertEntityToDTO(messages);
        logger.info("Messages list size", chatMessageResponseDTOS.size());
        return ResponseEntity.ok(chatMessageResponseDTOS);
    }

    private List<ChatMessageResponseDTO> convertEntityToDTO(List<ChatMessage> messagesEntity) {
        List<ChatMessageResponseDTO>  chatMessageDTOlist = new ArrayList<>();
        for(ChatMessage chatMessageEn:messagesEntity){
            ChatMessageResponseDTO chatMessageResponseDTO = new ChatMessageResponseDTO();
            chatMessageResponseDTO.setText(chatMessageEn.getText());
            chatMessageResponseDTO.setFromName(chatMessageEn.getFromName());
            chatMessageDTOlist.add(chatMessageResponseDTO);
        }
        return  chatMessageDTOlist;
    }

}