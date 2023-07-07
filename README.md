# Train Ticket Reservation System

**About:**

The Train Ticket Reservation System is a web application designed to provide various functionalities related to train ticket booking and information. It allows users to view train schedules, search for trains, check seat availability, obtain train timings, and inquire about fare details. The system also facilitates booking seats online, ensuring a secure and convenient reservation process.

## Key Features:

- **View Trains Schedule**
- **Search Trains**
- **Check Seats Availability**
- **Train Timings**
- **Fare Enquiry**
- **Trains Between Stations**
- **Booking Seats Online**
- **Login and Logout Security**
- **Password Changes**
- **Payment Gateway**
- **Ticket Booking History**

## Admin Access:

The admin of the system has additional privileges, including:

- **Login**: Admin can log in to access the admin panel.
- **Add Trains**: Admin can add new trains to the system.
- **Update Train Details**: Admin can update the details of existing trains.
- **Delete or Cancel Trains**: Admin can delete or cancel trains from the system.
- **View Trains**: Admin can view the list of available trains.
- **Search Trains by Number**: Admin can search for trains using their unique identification numbers.
- **Logout**: Admin can safely log out from the admin panel.

## User Access:

Users of the system have the following access and functionalities:

- **Register new User with profile photo**: Users can create new accounts with profile photos.
- **Login**: Users can log in to access their personalized functionalities.
- **View All Trains**: Users can view the list of all available trains.
- **Check Seats Availability**: Users can check the availability of seats on specific trains.
- **Search Trains**: Users can search for trains based on their preferences.
- **Train Availability and Fare Between Stations**: Users can check train availability and fare details between specific stations.
- **Book Tickets**: Users can book train tickets for their desired travel.
- **View Booking History**: Users can view their booking history, including past ticket reservations.
- **View Profile**: Users can view their profile information.
- **Update Profile**: Users can update their profile details.
- **Change Password**: Users can change their account passwords.
- **Logout**: Users can safely log out from the system.

## Technologies Used:

**Front-End Technologies:**

- JSP & JSTL
- CSS 
- Bootstrap

**Back-End Development:**

- Java Programming language
- Spring Boot
- Spring MVC 
- Spring AOP 
- Spring Data JPA
- Project Lombok
- MySQL

**Project Management Tool:**

- Maven

**Application Logging:**

- Log4j2

**Web Server:**

- Embedded Apache Tomcat Server
  
These technologies are carefully selected to provide a robust, efficient, and user-friendly solution for the Train Ticket Reservation System.




## Create Database Schema and Insert Dummy data for Project settup
```sql
-- Create table train
CREATE TABLE train (
    train_no BIGINT NOT NULL,      -- Unique identifier for the train
    fare DECIMAL(10, 2),           -- Fare for the train
    from_station VARCHAR(50),      -- Source station for the train
    seats_available INTEGER,       -- Number of available seats in the train
    to_station VARCHAR(50),        -- Destination station for the train
    train_name VARCHAR(100),       -- Name of the train
    PRIMARY KEY (train_no)         -- Primary key constraint
) ENGINE=INNODB;

-- Create table user_profiles
CREATE TABLE user_profiles (
    profile_id INTEGER NOT NULL,    -- Unique identifier for the user profile
    address VARCHAR(100),           -- Address of the user
    first_name VARCHAR(50),         -- First name of the user
    image LONGBLOB,                 -- Image data of the user
    last_name VARCHAR(50),          -- Last name of the user
    phone_number VARCHAR(20),       -- Phone number of the user
    PRIMARY KEY (profile_id)        -- Primary key constraint
) ENGINE=INNODB;

-- Create table users
CREATE TABLE users (
    user_id INTEGER NOT NULL,       -- Unique identifier for the user
    PASSWORD VARCHAR(50),           -- Password of the user
    ROLE VARCHAR(50),               -- Role of the user
    session_id VARCHAR(50),         -- Session ID for the user
    username VARCHAR(50),           -- Username of the user
    profile_id INTEGER,             -- Foreign key referencing user_profiles table
    PRIMARY KEY (user_id),          -- Primary key constraint
    CONSTRAINT FK_users_profile FOREIGN KEY (profile_id) REFERENCES user_profiles (profile_id) -- Foreign key constraint
) ENGINE=INNODB;

-- Create table ticket
CREATE TABLE ticket (
    ticket_id BIGINT NOT NULL,      -- Unique identifier for the ticket
    journey_date DATE,              -- Date of the journey
    seat_type VARCHAR(50),          -- Type of seat
    seats_required INTEGER,         -- Number of seats required
    amount DECIMAL(10, 2),          -- Amount for the ticket
    transaction_id VARCHAR(50),     -- Transaction ID for the ticket
    train_no BIGINT,                -- Foreign key referencing train table
    user_id INTEGER,                -- Foreign key referencing users table
    PRIMARY KEY (ticket_id),        -- Primary key constraint
    CONSTRAINT FK_ticket_train FOREIGN KEY (train_no) REFERENCES train (train_no),   -- Foreign key constraint
    CONSTRAINT FK_ticket_user FOREIGN KEY (user_id) REFERENCES users (user_id)      -- Foreign key constraint
) ENGINE=INNODB; 

-- Insert dummy data into train table
INSERT INTO train (train_no, train_name, from_station, to_station, seats_available, fare)
VALUES (10001, 'Rajdhani Express', 'New Delhi', 'Mumbai Central', 250, 1500.00);

INSERT INTO train (train_no, train_name, from_station, to_station, seats_available, fare)
VALUES (10002, 'Shatabdi Express', 'Chennai Central', 'Bengaluru City', 200, 800.50);

-- Insert dummy data into user_profiles table
INSERT INTO user_profiles (profile_id, address, first_name, image, last_name, phone_number)
VALUES (1, '123 Street, City', 'John', null, 'Doe', '1234567890');

-- Insert dummy data into users table
INSERT INTO users (user_id, password, role, session_id, username, profile_id)
VALUES (1, 'password123', 'user', 'session123', 'johndoe', 1);

-- Insert dummy data into ticket table
INSERT INTO ticket (ticket_id, journey_date, seat_type, seats_required, amount, transaction_id, train_no, user_id)
VALUES (1, '2023-01-01', 'AC', 2, 200.00, 'ABC123', 10001, 1);
