CREATE TABLE product (
                         sku VARCHAR(255) PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         description TEXT NOT NULL,
                         price DOUBLE NOT NULL,
                         stock INT NOT NULL
);
