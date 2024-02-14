import React, { useState } from 'react';
import { Container, Row, Col, Table, Form, Button } from 'react-bootstrap';

const RoomAttendantHomepage = () => {
  const [rooms, setRooms] = useState([
    {
      id: 1,
      roomNumber: '101',
      roomType: 'Single',
      status: 'started',
      tasks: ['Clean bathroom', 'Change bed sheets'],
      guest: 'John Doe',
      dates: '2024-02-13',
      notes: 'Extra towels needed',
    },
    {
      id: 2,
      roomNumber: '102',
      roomType: 'Double',
      status: 'completed',
      tasks: ['Vacuum floor', 'Restock minibar'],
      guest: 'Jane Smith',
      dates: '2024-02-13',
      notes: 'No special requests',
    },
    // Add more room objects as needed
  ]);

  return (
    <Container className="mt-4">
      <h1>Room Attendant Dashboard</h1>
      <Table striped bordered hover className="mt-4">
        <thead>
          <tr>
            <th>Room Number</th>
            <th>Room Type</th>
            <th>Status</th>
            <th>Tasks</th>
            <th>Guest</th>
            <th>Dates</th>
            <th>Notes</th>
          </tr>
        </thead>
        <tbody>
          {rooms.map((room) => (
            <tr key={room.id}>
              <td>{room.roomNumber}</td>
              <td>{room.roomType}</td>
              <td>{room.status}</td>
              <td>
                <ul>
                  {room.tasks.map((task, index) => (
                    <li key={index}>{task}</li>
                  ))}
                </ul>
              </td>
              <td>{room.guest}</td>
              <td>{room.dates}</td>
              <td>{room.notes}</td>
            </tr>
          ))}
        </tbody>
      </Table>
      <Form className="mt-4">
        <Row>
          <Col>
            <Form.Control placeholder="Room Number" />
          </Col>
          <Col>
            <Form.Control as="select">
              <option>Single</option>
              <option>Double</option>
              {/* Add more room types here */}
            </Form.Control>
          </Col>
          <Col>
            <Form.Control as="select">
              <option>Started</option>
              <option>Completed</option>
            </Form.Control>
          </Col>
          <Col>
            <Form.Control placeholder="Tasks" />
          </Col>
          <Col>
            <Form.Control placeholder="Guest" />
          </Col>
          <Col>
            <Form.Control type="date" />
          </Col>
          <Col>
            <Form.Control placeholder="Notes" />
          </Col>
          <Col>
            <Button variant="primary" type="submit">
              Add Room
            </Button>
          </Col>
        </Row>
      </Form>
    </Container>
  );
};

export default RoomAttendantHomepage;
