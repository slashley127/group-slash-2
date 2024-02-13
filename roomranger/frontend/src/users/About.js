import React from 'react';
import './About.css';

const authors = [
  {
    name: "Samata Karka",
    imageUrl: "/images/Sonal.jpeg",
    descriptionOfFeatures: "In Progress",
    bio: "In Progress",
    linkedInUrl: "in progress",
    gitHubUrl: "in progress"
  },
  {
    name: "Francesca",
    imageUrl: "/images/Sonal.jpeg",
    descriptionOfFeatures: "In Progress",
    bio: "In Progress",
    linkedInUrl: "in progress",
    gitHubUrl: "in progress"

  },
  {
    name: "Luna Liu",
    imageUrl: "/images/Sonal.jpeg",
    descriptionOfFeatures: "In Progress",
    bio: "In Progress",
    linkedInUrl: "in progress",
    gitHubUrl: "in progress"
  },
  {
    name: "Sonal",
    imageUrl: "/images/Sonal.jpeg",
    descriptionOfFeatures: "In Progress",
    bio: "In Progress",
    linkedInUrl: "https://www.linkedin.com/in/Sonalsharma1984/",
    gitHubUrl: "https://github.com/Sonal2808"
  }
];

const About = () => {
  return (
    <div className="about-container">
      <h1 className="about-heading">About us </h1>
      <div className="author-container">
        <div className="card-container">
          {authors.map(author => (
            <div key={author.name} className="card h-100">
              <img src={author.imageUrl} alt={author.name} className="card-image" />
              <div className="card-content">
                <h3>{author.name}</h3>
                <p>Description of Features: {author.descriptionOfFeatures}</p>
                <p>Bio: {author.bio}</p>
                <p>LinkedIn: <a href={author.linkedInUrl}>{author.linkedInUrl}</a></p>
                <p>GitHub: <a href={author.gitHubUrl}>{author.gitHubUrl}</a></p>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default About;