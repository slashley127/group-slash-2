package org.launchcode.roomranger.controllers;
import org.launchcode.roomranger.models.ChatMessage;
import org.launchcode.roomranger.models.Dto.ChatMessageRequestDTO;
import org.launchcode.roomranger.models.Dto.ChatMessageResponseDTO;
import org.launchcode.roomranger.security.JwtUtil;
import org.launchcode.roomranger.service.ChatMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin("http://localhost:3000")
public class ChatController {
    @Autowired
    private ChatMessageService chatMessageService;


    @Autowired
    private JwtUtil jwtUtil;

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);


    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")//to publish messages to all connected websocket clients
    public ChatMessageResponseDTO sendMessage(ChatMessageRequestDTO chatMessageRequestDTO) {
        logger.info("Handling message: {}", chatMessageRequestDTO.getText());
        logger.info("Handling token: {}", chatMessageRequestDTO.getToken());

        String token = chatMessageRequestDTO.getToken();
        // extract username and perform token validation
        String username = jwtUtil.extractUsername(token);

        ChatMessage chatMessageEntity = new ChatMessage();
        chatMessageEntity.setText(chatMessageRequestDTO.getText());
        chatMessageEntity.setFromName(username);
        chatMessageService.saveMessage(chatMessageEntity); // Save to database
        ChatMessageResponseDTO chatMessageResponseDTO = new ChatMessageResponseDTO();
        chatMessageResponseDTO.setText(chatMessageRequestDTO.getText());
        chatMessageResponseDTO.setFromName(username);
        logger.info("Username: {}", chatMessageResponseDTO.getFromName());
        return chatMessageResponseDTO;
    }

}
