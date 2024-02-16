import React, { useState, useEffect } from 'react';
import axios from 'axios';

const CommentList = ({ roomId }) => {
  const [comments, setComments] = useState([]);
  console.log(roomId)

  useEffect(() => {
    const fetchComments = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/comments`, {roomId});
        setComments(response.data);
        console.log("^^^^^", comments)
      } catch (error) {
        console.error('Error fetching comments:', error.response.data);
      }
    };

    fetchComments();
  }, [roomId]);

  return (
    <div>
      <h3>Comments</h3>
      <ul>
        {comments.map((comment) => (
          <li key={comment.id}>
            <strong>{comment.user}:</strong> {comment.text}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default CommentList;
