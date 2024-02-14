import React, { useEffect } from 'react';
import { useState } from 'react';
import { Button } from 'react-bootstrap';

export default function Comments() {
    const [comment, setComment] = useState({
        text: "",
        assignedRoom: "",
        user: "",

    });
    const submitComment = async (e) => {

    }
    const addComment = (value) => {
        const commentCopy = { ...comment };
        commentCopy.text = value;
        setComment(commentCopy);
    }
    useEffect(() => {
        console.log(comment);
    }, [comment]);

    return (
        <div className='mt-5'>
            <textarea style={{ width: "100%", borderRadius: "0.25em" }}
                onChange={(e) => addComment(e.target.value)}>
            </textarea>
            <Button onClick={() => submitComment()}>Post Comment</Button>
        </div>
    )
}
