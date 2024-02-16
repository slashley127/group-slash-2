import React, { useState, useEffect } from 'react';
import axios from 'axios';

const CommentList = ({ comment }) => {

  return (
    <div className="comment">
      <div className="comment-user">{comment.createdDate}</div>
      <div className="comment-text">{comment.text}</div>
    </div>
  );
};

export default CommentList;
