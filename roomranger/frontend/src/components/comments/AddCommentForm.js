import React from 'react';
import { useState } from 'react';
import { Button } from 'react-bootstrap';
import axios from 'axios';

export default function AddCommentForm({roomId}) {
  const [text, setText] = useState();

  const addComment = (value) => {
    setText(value);
  }

  const submitComment = async (e) => {
    try {
      console.log("^^^^", roomId, text);
      const response = await axios.post(`http://localhost:8080/comments`, {roomId, text});
    } catch (error) {
      console.error('Error posting comments:', error);
    }
  }
  
  return (
    <div>
      <div className='mt-4'>
        <textarea style={{ width: "100%", borderRadius: "0.25em" }}
          onChange={(e) => addComment(e.target.value)}>
        </textarea>
        <Button onClick={() => submitComment()}>Post Comment</Button>
      </div>
    </div>
  )
}
