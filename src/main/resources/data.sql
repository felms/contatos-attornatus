/*
create table person
(
    id IDENTITY NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    date_of_birth TIMESTAMP NOT NULL
);

create table address
(
    id IDENTITY NOT NULL PRIMARY KEY,
    street VARCHAR(100) NOT NULL,
    zipcode VARCHAR(20) NOT NULL,
    number INT NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(20) NOT NULL,
    type VARCHAR(15),
    person_id INT NOT NULL,

    CONSTRAINT FK_PERSON
    FOREIGN KEY (person_id)
    REFERENCES person(id)

);
*/

INSERT INTO person (id, name, date_of_birth) VALUES (1, 'Machado de Asis', '1839-06-21');
INSERT INTO person (id, name, date_of_birth) VALUES (2, 'Brandon Sanderson', '1975-12-19');
INSERT INTO person (id, name, date_of_birth) VALUES (3, 'Andy Weir', '1972-06-16');

INSERT INTO address (id, street, zipcode, number, city, state, type, person_id) VALUES (1, 'Rua Afonso Cavalcanti', '20211-110', 455, 'Rio de Janeiro', 'Rio de Janeiro', 'MAIN_ADDRESS', 1);

INSERT INTO address (id, street, zipcode, number, city, state, type, person_id) VALUES (2, 'K Street', '68508', 1445, 'Lincoln', 'Nebraske', 'MAIN_ADDRESS', 2);
INSERT INTO address (id, street, zipcode, number, city, state, type, person_id) VALUES (3, 'Farnam Street', '68183', 1819, 'Omaha', 'Nebraske', 'SECONDARY_ADDRESS', 2);

INSERT INTO address (id, street, zipcode, number, city, state, type, person_id) VALUES (4, 'Russell Boulevard', '95616', 23, 'Davis', 'California', 'MAIN_ADDRESS', 3);
INSERT INTO address (id, street, zipcode, number, city, state, type, person_id) VALUES (5, '10th St', '95814', 1315, 'Sacramento', 'California', 'SECONDARY_ADDRESS', 3);

