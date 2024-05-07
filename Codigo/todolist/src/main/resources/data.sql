DROP TABLE IF EXISTS Task;

CREATE TABLE Task (
    id NUMBER PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(255),
    done BOOLEAN,
    deleted BOOLEAN,
    doDate DATE,
    startDate DATE,
    deadline NUMBER,
    priorities VARCHAR(50),
    status VARCHAR(50)
);

-- INSERT INTO Task (description, done, deleted, doDate, startDate, deadline, priorities, status)
-- VALUES 
--     ('Task 10', false, false, '2023-04-15', '2023-04-01', 10, NULL, NULL),
--     ('Task 20', false, false, '2023-05-01', '2023-03-01', 20, NULL, NULL),
--     ('Task 30', false, false, NULL, '2023-03-10', NULL, NULL, NULL),
--     ('Task 40', false, false, '2023-06-10', '2023-04-10', 30, NULL, NULL),
--     ('Task 50', false, false, '2023-07-01', '2023-05-01', 15, NULL, NULL),
--     ('Task 60', false, false, '2023-08-01', '2023-06-01', 25, NULL, NULL),
--     ('Task 70', false, false, '2023-07-15', '2023-05-15', 20, NULL, NULL),
--     ('Task 80', false, false, '2023-09-01', '2023-07-01', 30, NULL, NULL);
