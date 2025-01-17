-- Create the database
--CREATE DATABASE advanced_library_management_system;

-- Connect to the database
--\c advanced_library_management_system;

-- Create the books table
CREATE TABLE books (
	id SERIAL PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
	author VARCHAR(255) NOT NULL,
	category VARCHAR(255) NOT NULL,
	number_of_copies INT NOT NULL
);

-- Create the members table
CREATE TABLE members (
	id SERIAL PRIMARY KEY,
	firstName VARCHAR(255) NOT NULL,
	lastName VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL,
	membership_date DATE NOT NULL
);

-- Create the loans table
CREATE TABLE loans (
	id SERIAL PRIMARY KEY,
	memberId INT REFERENCES members(id),
	bookId INT REFERENCES 	books(id),
	dateLoan DATE NOT NULL,
	dateReturnPreview DATE NOT NULL,
	effectiveReturnDate DATE
	);

-- Insertion of 30 books into Books table
INSERT INTO books (title, author, category, number_of_copies) VALUES
('The Great Gatsby', 'F. Scott Fitzgerald', 'Classic', 12),
('To Kill a Mockingbird', 'Harper Lee', 'Classic', 15),
('1984', 'George Orwell', 'Dystopian', 10),
('Pride and Prejudice', 'Jane Austen', 'Romance', 8),
('The Catcher in the Rye', 'J.D. Salinger', 'Classic', 7),
('The Hobbit', 'J.R.R. Tolkien', 'Fantasy', 10),
('Moby Dick', 'Herman Melville', 'Adventure', 5),
('The Lord of the Rings', 'J.R.R. Tolkien', 'Fantasy', 9),
('Jane Eyre', 'Charlotte Bronte', 'Romance', 12),
('Animal Farm', 'George Orwell', 'Political Satire', 13),
('War and Peace', 'Leo Tolstoy', 'Historical', 6),
('The Odyssey', 'Homer', 'Epic', 11),
('Ulysses', 'James Joyce', 'Modernist', 8),
('The Divine Comedy', 'Dante Alighieri', 'Epic', 7),
('Brave New World', 'Aldous Huxley', 'Dystopian', 14),
('Les Misérables', 'Victor Hugo', 'Historical', 10),
('Crime and Punishment', 'Fyodor Dostoevsky', 'Psychological', 9),
('Wuthering Heights', 'Emily Bronte', 'Romance', 8),
('The Iliad', 'Homer', 'Epic', 11),
('The Brothers Karamazov', 'Fyodor Dostoevsky', 'Philosophical', 5),
('Anna Karenina', 'Leo Tolstoy', 'Romance', 12),
('The Metamorphosis', 'Franz Kafka', 'Absurdist', 7),
('One Hundred Years of Solitude', 'Gabriel Garcia Marquez', 'Magic Realism', 9),
('Don Quixote', 'Miguel de Cervantes', 'Adventure', 14),
('The Trial', 'Franz Kafka', 'Absurdist', 8),
('The Picture of Dorian Gray', 'Oscar Wilde', 'Philosophical', 11),
('Catch-22', 'Joseph Heller', 'Satire', 10),
('Great Expectations', 'Charles Dickens', 'Bildungsroman', 7),
('The Grapes of Wrath', 'John Steinbeck', 'Historical', 8),
('The Old Man and the Sea', 'Ernest Hemingway', 'Literary', 6);


-- Insert 30 members in the members Table
INSERT INTO members (firstName, lastName, email, membership_date) VALUES
('Doe', 'John', 'john.doe@example.com', '2023-01-01'),
('Smith', 'Jane', 'jane.smith@example.com', '2023-02-15'),
('Brown', 'Michael', 'michael.brown@example.com', '2023-03-10'),
('Johnson', 'Emily', 'emily.johnson@example.com', '2023-04-05'),
('Williams', 'Christopher', 'christopher.williams@example.com', '2023-05-20'),
('Jones', 'Amanda', 'amanda.jones@example.com', '2023-06-25'),
('Davis', 'Daniel', 'daniel.davis@example.com', '2023-07-15'),
('Garcia', 'Patricia', 'patricia.garcia@example.com', '2023-08-30'),
('Miller', 'Richard', 'richard.miller@example.com', '2023-09-18'),
('Wilson', 'Elizabeth', 'elizabeth.wilson@example.com', '2023-10-01'),
('Moore', 'David', 'david.moore@example.com', '2023-11-11'),
('Taylor', 'Linda', 'linda.taylor@example.com', '2023-12-21'),
('Anderson', 'Joseph', 'joseph.anderson@example.com', '2024-01-12'),
('Thomas', 'Barbara', 'barbara.thomas@example.com', '2024-02-22'),
('Jackson', 'Charles', 'charles.jackson@example.com', '2024-03-01'),
('White', 'Margaret', 'margaret.white@example.com', '2024-04-17'),
('Harris', 'Steven', 'steven.harris@example.com', '2024-05-07'),
('Martin', 'Sarah', 'sarah.martin@example.com', '2024-06-27'),
('Thompson', 'Paul', 'paul.thompson@example.com', '2024-07-03'),
('Garcia', 'Mark', 'mark.garcia@example.com', '2024-08-15'),
('Martinez', 'Nancy', 'nancy.martinez@example.com', '2024-09-25'),
('Robinson', 'Andrew', 'andrew.robinson@example.com', '2024-10-08'),
('Clark', 'Sandra', 'sandra.clark@example.com', '2024-11-12'),
('Rodriguez', 'Joshua', 'joshua.rodriguez@example.com', '2024-12-31'),
('Lewis', 'Karen', 'karen.lewis@example.com', '2025-01-14'),
('Lee', 'Brian', 'brian.lee@example.com', '2025-02-28'),
('Walker', 'Carol', 'carol.walker@example.com', '2025-03-15'),
('Hall', 'Kevin', 'kevin.hall@example.com', '2025-04-05'),
('Allen', 'Laura', 'laura.allen@example.com', '2025-05-20'),
('Young', 'Edward', 'edward.young@example.com', '2025-06-25');

-- Insert 30 loans with different memberId and bookId
INSERT INTO loans (memberId, bookId, dateLoan, dateReturnPreview, effectiveReturnDate) VALUES
(1, 1, '2023-01-10', '2023-01-20', '2023-01-18'),
(2, 3, '2023-02-16', '2023-02-26', '2023-02-25'),
(3, 5, '2023-03-11', '2023-03-21', '2023-03-20'),
(4, 7, '2023-04-06', '2023-04-16', '2023-04-14'),
(5, 9, '2023-05-21', '2023-05-31', '2023-05-30'),
(6, 11, '2023-06-26', '2023-07-06', '2023-07-05'),
(7, 13, '2023-07-16', '2023-07-26', '2023-07-25'),
(8, 15, '2023-08-31', '2023-09-10', '2023-09-09'),
(9, 17, '2023-09-19', '2023-09-29', '2023-09-28'),
(10, 19, '2023-10-02', '2023-10-12', '2023-10-11'),
(11, 21, '2023-11-12', '2023-11-22', '2023-11-21'),
(12, 23, '2023-12-22', '2024-01-01', '2023-12-31'),
(13, 25, '2024-01-13', '2024-01-23', '2024-01-22'),
(14, 27, '2024-02-23', '2024-03-04', '2024-03-03'),
(15, 29, '2024-03-02', '2024-03-12', '2024-03-11'),
(16, 2, '2024-04-18', '2024-04-28', '2024-04-27'),
(17, 4, '2024-05-08', '2024-05-18', '2024-05-17'),
(18, 6, '2024-06-28', '2024-07-08', '2024-07-07'),
(19, 8, '2024-07-04', '2024-07-14', '2024-07-13'),
(20, 10, '2024-08-16', '2024-08-26', '2024-08-25'),
(21, 12, '2024-09-26', '2024-10-06', '2024-10-05'),
(22, 14, '2024-10-09', '2024-10-19', '2024-10-18'),
(23, 16, '2024-11-13', '2024-11-23', '2024-11-22'),
(24, 18, '2024-12-15', '2024-12-25', '2024-12-24'),
(25, 20, '2025-01-03', '2025-01-13', '2025-01-12'),
(26, 22, '2025-02-10', '2025-02-20', '2025-02-19'),
(27, 24, '2025-03-05', '2025-03-15', '2025-03-14'),
(28, 26, '2025-04-18', '2025-04-28', '2025-04-27'),
(29, 28, '2025-05-11', '2025-05-21', '2025-05-20'),
(30, 30, '2025-06-14', '2025-06-24', '2025-06-23');