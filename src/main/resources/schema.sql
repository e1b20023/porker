CREATE TABLE poker4 (
    id INT NOT NULL PRIMARY KEY,
    num INT NOT NULL,
    suit VARCHAR NOT NULL
);

CREATE TABLE score (
    id IDENTITY,
    hand VARCHAR NOT NULL,
    score INT NOT NULL
);
