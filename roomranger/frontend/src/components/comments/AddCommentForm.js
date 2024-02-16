import React, { useEffect } from 'react';
import { useState } from 'react';
import { Button } from 'react-bootstrap';
import axios from 'axios';
import CommentList from './CommentList';


export default function AddCommentForm({assignRoomId}) {
  const [text, setText] = useState();
  console.log("assigned~~~", typeof(assignRoomId))

  const[comments,setComments] = useState([]);
  const [refreshId, setRefreshId] = useState(Symbol()); 
  const addComment = (value) => {
    setText(value);
  }

  const submitComment = async (e) => {
    try {
      const response = await axios.post(`http://localhost:8080/comments`, {assignRoomId, text});
    } catch (error) {
      console.error('Error posting comments:', error);
    }
  }
  //fetch all comments from the assigned room.
  const loadComments = async (e) =>{
    try{
      const response = await axios.get("http://localhost:8080/comments");
      setComments(response);
    }catch(error){
      console.log("error", error.response.data)
    }
  }
  useEffect(()=>{
    loadComments();
  },[refreshId])
  
  return (
    <div>
      <div className='mt-5'>
        <textarea style={{ width: "100%", borderRadius: "0.5em" }}
          onChange={(e) => addComment(e.target.value)}>
        </textarea>
        <Button onClick={() => submitComment()}>Post Comment</Button>
      </div>
      <div className="mt-5">
        <h2>Comments:</h2>
        {comments.map((comment) => (
          <CommentList key={comment.id} comment={comment} />
        ))}
      
      </div>
    </div>
  )
}
