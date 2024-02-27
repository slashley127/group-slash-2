package org.launchcode.roomranger.service;


import org.launchcode.roomranger.data.ChatMessageRepository;
import org.launchcode.roomranger.models.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChatMessageService {
    private static final Logger logger = LoggerFactory.getLogger(ChatMessageService.class);
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public void saveMessage(ChatMessage chatMessage) {
        chatMessageRepository.save(chatMessage);
    }


    @Transactional(readOnly = true)
    public List<ChatMessage> findAllMessages() {
        List<ChatMessage> messages = chatMessageRepository.findAll();
        if(messages!=null){
            logger.info("Messages list", messages.size());
        }
        else {
            logger.info("Null messsges from db");
        }


        return messages;
    }

}