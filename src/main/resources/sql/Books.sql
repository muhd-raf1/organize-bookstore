CREATE TABLE books(
    id VARCHAR(5) NOT NULL PRIMARY KEY,
    id_categori VARCHAR(10) NOT NULL,
    title VARCHAR(100) NOT NULL,
    pages INT,
    author VARCHAR(50) NOT NULL,
    publisher VARCHAR(50) NOT NULL,
    description VARCHAR(100),
    year_of_publish TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_idCategory_books FOREIGN KEY (id_categori) REFERENCES category(id)
);