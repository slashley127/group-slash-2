import React from 'react';

export const MessageList = ({ messages }) => {
    return (
        <ul>
            {messages.map((msg, index) => (
                <li key={index}>{msg.content}</li>
            ))}
        </ul>
    );
};