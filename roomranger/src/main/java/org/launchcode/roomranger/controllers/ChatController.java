package org.launchcode.roomranger.controllers;
import org.launchcode.roomranger.models.ChatMessage;
import org.launchcode.roomranger.models.Dto.AuthenticationMessageDTO;
import org.launchcode.roomranger.models.Dto.ChatInputMessageDTO;
import org.launchcode.roomranger.models.Dto.ChatMessageDTO;
import org.launchcode.roomranger.security.JwtUtil;
import org.launchcode.roomranger.service.ChatMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.Payload;
import java.security.Principal;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin("http://localhost:3000")
public class ChatController {
    @Autowired
    private ChatMessageService chatMessageService;


    @Autowired
    private JwtUtil jwtUtil;

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @MessageMapping("/chatauthenticate")
    public void authenticate(Principal principal, @Payload AuthenticationMessageDTO message) {
        // Validate JWT token in the message payload
        String token = message.getToken();
        // Perform token validation
        String username = jwtUtil.extractUsername(token);

    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessageDTO sendMessage(ChatInputMessageDTO chatInputMessageDTO) {
        logger.info("Handling message: {}", chatInputMessageDTO.getText());
        logger.info("Handling token: {}", chatInputMessageDTO.getToken());
        //
        // Validate JWT token in the message payload
        String token = chatInputMessageDTO.getToken();
        // Perform token validation
       String username = jwtUtil.extractUsername(token);

        ChatMessage chatMessageEntity = new ChatMessage();
        chatMessageEntity.setText(chatInputMessageDTO.getText());
        chatMessageEntity.setFromName(username);
        chatMessageService.saveMessage(chatMessageEntity); // Save to database
        ChatMessageDTO chatMessageDTO = new ChatMessageDTO();
        chatMessageDTO.setText(chatInputMessageDTO.getText());
        chatMessageDTO.setFromName(username);
        return chatMessageDTO;
    }

}