import React, { useState, useEffect } from 'react';
import axios from "axios";
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import {useNavigate } from "react-router-dom";

export default function GroupChatComponent () {
    const [stompClient, setStompClient] = useState(null);
    const [messages, setMessages] = useState([]);
    const [text, setText] = useState('');
    const navigate = useNavigate();

    const jwt = localStorage.getItem('jwt'); // Retrieve the JWT token from local storage
    const authAxios = axios.create({
      baseURL: "http://localhost:8080",
      headers: {
        Authorization: `Bearer ${jwt}`
      }
    });

    useEffect(() => {
        // Fetch all saved messages from the backend on component mount
        const fetchMessages = async () => {
            try {
                const response = await authAxios.get('/messages'); // baseURL is already set in axios instance
                setMessages(response.data); // Correctly access data from Axios response
                console.log(response.data); // Debugging purposes
            } catch (error) {
                console.error("Error fetching messages:", error);
                if (error.response && error.response.status === 403) {
                    navigate('/login');
                }
            }
        };

        // Connect to WebSocket server
        const socket = new SockJS('http://localhost:8080/chat');

        const client = Stomp.over(socket);
        client.connect({ Authorization: `Bearer ${jwt}` }, () => {
            setStompClient(client);
            client.subscribe('/topic/public', (message) => {
                const newMessage = JSON.parse(message.body);
                setMessages(prevMessages => {
                    // Simple check to avoid adding a duplicate message
                    const lastMessage = prevMessages[prevMessages.length - 1];
                    if (lastMessage && lastMessage.text === newMessage.text && lastMessage.fromName === newMessage.fromName) {
                        return prevMessages; // Ignore duplicate
                    }
                    return [...prevMessages, newMessage];
                });
            });
            
        });

       fetchMessages();

        return () => {
            if (stompClient) {
                stompClient.disconnect();
            }
        };
    }, [jwt]);

    // Function to send a chat message
    const sendMessage = (e) => {
        e.preventDefault(); // Prevent form submission/reloading the page
        if (stompClient && text) {
            //const message = { fromName: 'userName',token:`${jwt}`, text:text };
            const message = { token:jwt, text:text };
            console.log("Sending message:", message); // Logging the message for inspection
            stompClient.send("/app/chat.sendMessage", {},JSON.stringify(message));
            setText(''); // Clear message input after sending
        }
    };

    // Handle Enter key press
    const handleKeyPress = (e) => {
        if (e.key === 'Enter' && !e.shiftKey) {
            sendMessage(e);
        }
    };

    return (
        <div>
            <h2>Group Chat</h2>
            <div>
                <input
                    type="text"
                    value={text}
                    onChange={(e) => setText(e.target.value)}
                    onKeyPress={handleKeyPress}
                    placeholder="Type your message here..."
                />
                <button onClick={sendMessage}>Send</button>
            </div>
            <div>
                <h3>Messages</h3>
                {messages.map((msg, index) => (
                    <div key={index}><b>{msg.fromName}:</b> {msg.text}</div>
                ))}
            </div>
        </div>
    );
};