import React, { useEffect } from 'react';
import { useState } from 'react';
import { Button, Card } from 'react-bootstrap';
import axios from 'axios';
import CommentList from './CommentList';


export default function AddCommentForm({ assignRoomId }) {
  const [text, setText] = useState("");
  const [comments, setComments] = useState([]);
  const [refreshId, setRefreshId] = useState(Symbol());
  const addComment = (value) => {
    setText(value);
  }

  const submitComment = async (e) => {
    try {
      const response = await axios.post(`http://localhost:8080/comments`, { assignRoomId, text });
      setRefreshId(Symbol());
      console.log("~~before", text);
      setText("");
      console.log("~~after", text);
    } catch (error) {
      console.error('Error posting comments:', error);
    }
  }
  //fetch all comments from the assigned room.
  const loadComments = async (e) => {
    try {
      const response = await axios.get('http://localhost:8080/comments', {
        params: {
          assignedRoomId: assignRoomId
        }
      });
      setComments(response.data);
      // console.log("comments", comments)
    } catch (error) {
      console.log("error", error)
    }
  }
  // This useEffect will run whenever 'text' changes
  useEffect(() => {
    console.log("Text after clearing:", text);  // Log text after it's reset
  }, [text]);

  // render the comments after each post
  useEffect(() => {
    loadComments();
  }, [refreshId])

  return (
    <div>
      <div className='mt-5'>
        <textarea style={{ width: "100%", borderRadius: "0.5em" }} value={text}
          onChange={(e) => addComment(e.target.value)}>
        </textarea>
        <Button variant="outline-primary" onClick={() => submitComment()} disabled={!text}>Post Comment</Button>
      </div>
      <div className="mt-5">
        <h2>Comments</h2>
        {/* sort the comments table by id desc */}
        <div style={{ height: '300px', overflowY: 'scroll', textAlign: 'left' }}>
          {comments.sort((a, b) => b.id - a.id).map((comment) => (
            <CommentList key={comment.id} comment={comment} />
          ))}
        </div>
      </div>
    </div>
  )
}
