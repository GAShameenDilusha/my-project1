CREATE TABLE church (
                        church_no VARCHAR(35) PRIMARY KEY,
                        A INT(100) NOT NULL ,
                        B INT(100) NOT NULL ,
                        C INT(100) NOT NULL ,
                        D INT(100) NOT NULL
);






INSERT INTO church VALUES ('P1',100,100,100,100);
INSERT INTO church VALUES ('P2',110,110,110,110);
INSERT INTO church VALUES ('P3',120,120,120,120);






CREATE TABLE registration (
                              church_no VARCHAR(35),
                              division_no VARCHAR(25) NOT NULL ,
                              family_no VARCHAR(20) NOT NULL ,
                              father_id VARCHAR(15) NOT NULL ,
                              mother_id VARCHAR(15) NOT NULL ,
                              father_name VARCHAR(30) NOT NULL,
                              mother_name VARCHAR(40) NOT NULL,
                              address VARCHAR(70) NOT NULL,
                              tel VARCHAR(15) NOT NULL,
                              date DATE NOT NULL,
                              PRIMARY KEY (division_no,family_no, father_id, mother_id),
                              FOREIGN KEY(church_no) REFERENCES church(church_no) ON DELETE CASCADE ON UPDATE CASCADE
);










ALTER TABLE registration
    ADD INDEX idx_family_no (family_no);



CREATE TABLE children (
                          family_no VARCHAR(20),
                          child_id VARCHAR(30) PRIMARY KEY,
                          child_name VARCHAR(40) NOT NULL,
                          birthday DATE NOT NULL,
                          complimentary_date DATE NOT NULL,
                          date DATE NOT NULL,
                          FOREIGN KEY (family_no) REFERENCES registration(family_no) ON DELETE CASCADE ON UPDATE CASCADE
);







CREATE TABLE father(
                       church_no VARCHAR(30),
                       church_father_id VARCHAR(25) PRIMARY KEY,
                       name VARCHAR(40) NOT NULL ,
                       start_date DATE NOT NULL ,
                       leave_date DATE NOT NULL ,
                       FOREIGN KEY(church_no) REFERENCES church(church_no) ON DELETE CASCADE ON UPDATE CASCADE
);







CREATE TABLE vehicle(
                        church_father_id VARCHAR(25),
                        date DATE NOT NULL ,
                        category VARCHAR(45) NOT NULL ,
                        discription VARCHAR(100) NOT NULL ,
                        FOREIGN KEY(church_father_id) REFERENCES father(church_father_id) ON DELETE CASCADE ON UPDATE CASCADE
);









CREATE TABLE payment(
                        church_no VARCHAR(30),
                        family_no VARCHAR(25),
                        division_no VARCHAR(35),
                        fee DECIMAL(10,2) NOT NULL ,
                        date DATE NOT NULL ,
                        FOREIGN KEY(church_no) REFERENCES church(church_no) ON DELETE CASCADE ON UPDATE CASCADE ,
                        FOREIGN KEY(family_no) REFERENCES registration(family_no) ON DELETE CASCADE ON UPDATE CASCADE ,
                        FOREIGN KEY(division_no) REFERENCES registration(division_no) ON DELETE CASCADE ON UPDATE CASCADE
);









CREATE TABLE event(
                      familY_no VARCHAR(20),
                      event_name VARCHAR(100) NOT NULL ,
                      date DATE NOT NULL ,
                      time VARCHAR(30) NOT NULL ,
                      discription VARCHAR(100) NOT NULL ,
                      estimated_budget DECIMAL(10,2) NOT NULL ,
                      cost DECIMAL(10,2)  NOT NULL ,
                      FOREIGN KEY(family_no) REFERENCES registration(family_no) ON DELETE CASCADE ON UPDATE CASCADE
);








CREATE TABLE attendence(
                           family_no VARCHAR(30),
                           purpose VARCHAR(100) NOT NULL ,
                           arrange_time VARCHAR(50) NOT NULL ,
                           leave_time VARCHAR(50) NOT NULL ,
                           date DATE NOT NULL ,
                           FOREIGN KEY(family_no) REFERENCES registration(family_no) ON DELETE CASCADE ON UPDATE CASCADE
);








CREATE TABLE visit(
                      family_no VARCHAR(25),
                      church_father_id VARCHAR(30),
                      date DATE NOT NULL ,
                      time VARCHAR(30) NOT NULL ,
                      discription VARCHAR(100) NOT NULL ,
                      FOREIGN KEY(family_no) REFERENCES registration(family_no) ON DELETE CASCADE ON UPDATE CASCADE,
                      FOREIGN KEY(church_father_id) REFERENCES father(church_father_id) ON DELETE CASCADE ON UPDATE CASCADE
);



CREATE TABLE user (
                      username VARCHAR(255) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL
);
