import React, { useState, useEffect } from 'react';
import './comments.css'
import { useAuth } from '../security/AuthContext';

const CommentList = ({ comment }) => {
const theDate = comment.createdDate.slice(0,10);
const theTime = comment.createdDate.slice(11,16);
const createdBy = comment.createdBy;

  return (
    <div className="card">
      <div className="comment-text">{comment.text}</div>
      <div className="comment-user">{createdBy}: {theDate} {theTime}</div>
    </div>
  );
};

export default CommentList;
