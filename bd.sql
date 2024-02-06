CREATE TABLE IF NOT EXISTS employees(
    id_employees SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    dni VARCHAR(15) NOT NULL,
    telephone VARCHAR(20),
    status INT
);

CREATE TABLE IF NOT EXISTS roles(
    id_roles SERIAL PRIMARY KEY,
    name VARCHAR(25) NOT NULL UNIQUE,
    status INT
);

SELECT * FROM roles;

SELECT * FROM users;
SELECT * FROM employees;

CREATE TABLE IF NOT EXISTS users(
    id_users SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(250) NOT NULL,
    password_changed INT,
    status INT,
    employees_id_employees INT,
    roles_id_roles INT,
    FOREIGN KEY (employees_id_employees) REFERENCES employees(id_employees),
    FOREIGN KEY (roles_id_roles) REFERENCES roles(id_roles)
);

CREATE TABLE IF NOT EXISTS products(
    id_products SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(250),
    price DECIMAL(10,2) NOT NULL,
    stockable INT,
    status INT,
    user_create VARCHAR(45),
      date_create TIMESTAMP,
      user_modif VARCHAR(45),
      date_modif TIMESTAMP,
      user_delete VARCHAR(45),
      date_delete TIMESTAMP
);

CREATE TABLE IF NOT EXISTS tables(
    id_tables SERIAL PRIMARY KEY,
    number int NOT NULL UNIQUE ,
    status int,
    user_create VARCHAR(45),
      date_create TIMESTAMP,
      user_modif VARCHAR(45),
      date_modif TIMESTAMP,
      user_delete VARCHAR(45),
      date_delete TIMESTAMP
);

SELECT * FROM products;

DROP TABLE products;

--DROP TABLE users;
--DROP TABLE roles;
--DROP TABLE employees;


INSERT INTO roles(name,status) VALUES ('Admin',1);
INSERT INTO roles(name,status) VALUES ('Mozo',1);
INSERT INTO roles(name,status) VALUES ('Cocina',1);

UPDATE roles SET status = 1 WHERE id_roles = 3;

CREATE TABLE IF NOT EXISTS tables(
    id_tables SERIAL PRIMARY KEY,
    number int NOT NULL UNIQUE,
    status int,
     user_create VARCHAR(45),
      date_create TIMESTAMP,
      user_modif VARCHAR(45),
      date_modif TIMESTAMP,
      user_delete VARCHAR(45),
      date_delete TIMESTAMP
);
INSERT INTO tables(number,status) VALUES (1,1);
CREATE TABLE IF NOT EXISTS orders(
    id_orders SERIAL PRIMARY KEY,
    tables_id_tables int,
    employees_id_employees int,
    subtotal DECIMAL(10,2),
    total DECIMAL(10,2),
    status int,
    name VARCHAR(100) ,
    lastname VARCHAR(100),
    dni VARCHAR(8),
    business_name VARCHAR(100),
    trade_name VARCHAR(100),
    ruc VARCHAR(11),
    user_create VARCHAR(45),
      date_create TIMESTAMP,
      user_modif VARCHAR(45),
      date_modif TIMESTAMP,
      user_delete VARCHAR(45),
      date_delete TIMESTAMP,
    FOREIGN KEY (employees_id_employees) REFERENCES employees(id_employees),
    FOREIGN KEY (tables_id_tables) REFERENCES tables(id_tables)
);

CREATE TABLE IF NOT EXISTS orders_detail(
  id_orders_detail SERIAL PRIMARY KEY,
    orders_id_orders int,
    products_id_products int,
    unit_price DECIMAL(10,2),
    amount int,
    subtotal DECIMAL(10,2),
    FOREIGN KEY (orders_id_orders) REFERENCES orders(id_orders),
    FOREIGN KEY (products_id_products) REFERENCES products(id_products)
);

DROP TABLE orders_detail;
DROP TABLE orders;

SELECT * FROM orders;
SELECT * FROM products;

SELECT * FROM orders_detail;