create table if not exists `User`
(
    ID         LONG         NOT NULL AUTO_INCREMENT UNIQUE,
    first_name VARCHAR(100) NOT NULL,
    last_name  VARCHAR(100) NOT NULL,
    email      VARCHAR(100) NOT NULL UNIQUE,
    PRIMARY KEY (ID)
);

create table if not exists Employee
(
    ID         LONG         NOT NULL AUTO_INCREMENT UNIQUE,
    first_name VARCHAR(100) NOT NULL,
    last_name  VARCHAR(100) NOT NULL,
    PRIMARY KEY (ID)
);

create table if not exists Request
(
    ID              LONG          NOT NULL AUTO_INCREMENT UNIQUE,
    UserID          LONG DEFAULT NULL,
    message         VARCHAR(4000) NOT NULL,
    date_of_contact DATE          NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (UserID) REFERENCES `User` (ID) ON DELETE CASCADE
);

create table if not exists Incident
(
    ID               LONG NOT NULL AUTO_INCREMENT UNIQUE,
    RequestID        LONG DEFAULT NULL,
    EmployeeID       LONG DEFAULT NULL,
    solution         VARCHAR(4000),
    date_of_solution DATE NOT NULL,
    rating           INT  DEFAULT 10,
    PRIMARY KEY (ID),
    FOREIGN KEY (RequestID) REFERENCES Request (ID) ON DELETE CASCADE,
    FOREIGN KEY (EmployeeID) REFERENCES Employee (ID) ON DELETE CASCADE
);

create table if not exists Incident_for_help
(
    ID         LONG          NOT NULL AUTO_INCREMENT UNIQUE,
    IncidentID LONG DEFAULT NULL,
    message    VARCHAR(4000) NOT NULL,
    solution   VARCHAR(4000) NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (IncidentID) REFERENCES Incident (ID) ON DELETE CASCADE
);
