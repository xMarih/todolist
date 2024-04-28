DROP TABLE IF EXISTS task;
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


-- -- Inserção de duas tarefas
-- INSERT INTO Task (description, done, deleted, doDate, startDate, deadline, priorities, status)
-- VALUES ('Task 1', false, false, '2023-04-15', '2023-04-01', 10, 'High', ''),
--        ('Task 2', true, false, '2023-05-01', '2023-03-01', 20, 'Low', '');

-- -- Inserção de mais duas tarefas para as queries da classe de repositório
-- INSERT INTO Task (description, done, deleted, doDate, startDate, deadline, priorities, status)
-- VALUES ('Task 3', true, false, NULL, '2023-03-10', NULL, 'Medium', ''),
--        ('Task 4', false, false, '2023-06-10', '2023-04-10', 30, 'Medium', '');

-- INSERT INTO Task (description, done, deleted, doDate, startDate, deadline, priorities, status)
-- VALUES 
-- ('Task 5', false, false, '2023-07-01', '2023-05-01', 15, 'High', 'In Progress'),

-- ('Task 6', true, true, '2023-08-01', '2023-06-01', 25, 'Low', 'Archived'),

-- ('Task 7', false, false, '2023-07-15', '2023-05-15', 20, 'Medium', 'In Progress'),

-- ('Task 8', true, false, '2023-09-01', '2023-07-01', 30, 'High', 'Completed');


