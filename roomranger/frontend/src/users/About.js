import React, { useState, useEffect } from 'react';
import axios from 'axios';
import "bootstrap/dist/css/bootstrap.min.css";
import './About.css'; // Import your CSS file for additional styling

const About = () => {
  const [authors, setAuthors] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    axios.get("http://localhost:8080/about")
      .then(response => {
        setAuthors(response.data);
        setLoading(false);
      })
      .catch(error => {
        setError(error.message);
        setLoading(false);
      });
  }, []);
  
  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;
  
  return (
    <div className="author-container">
      <h2>About us</h2>
      <div className="card-container">
        {authors.map(author => (
          <div key={author.name} className="card">
            <img src={author.imageUrl} alt={author.name} className="card-image" />
            <div className="card-content">
              <h3>{author.name}</h3>
              <p>Description of Features: {author.descriptionOfFeatures}</p>
              <p>Bio: {author.bio}</p>
              <p>LinkedIn: {author.linkedInUrl}</p>
              <p>GitHub: {author.gitHubUrl}</p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default About;
