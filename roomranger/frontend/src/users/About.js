import React, { useState, useEffect } from 'react';

const About = () => {
  const [authors, setAuthors] = useState([]);

  useEffect(() => {
    fetch('/author') 
      .then(response => response.json())
      .then(data => setAuthors(data))
      .catch(error => console.error('Error fetching about us content:', error));
  }, []);
  
  return (
    <div className="author-container">
      <h2>About us</h2>
      <ul>
        {authors.map(author => (
          <li key={author.name}>
            <h3>{author.name}</h3>
            <img src={author.imageUrl} alt={author.name} />
            <p>Description of Features: {author.descriptionOfFeatures}</p>
            <p>Bio: {author.bio}</p>
            <p>LinkedIn: {author.linkedInUrl}</p>
            <p>GitHub: {author.gitHubUrl}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default About;
