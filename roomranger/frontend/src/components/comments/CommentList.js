import React, { useState, useEffect } from 'react';
import './comments.css'

const CommentList = ({ comment }) => {
const theDate = comment.createdDate.slice(0,10);
const theTime = comment.createdDate.slice(11,16)

  return (
    <div className="card">
      <div className="comment-text">{comment.text}</div>
      <div className="comment-user">{theDate} {theTime}</div>
    </div>
  );
};

export default CommentList;
