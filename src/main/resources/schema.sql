CREATE TABLE IF NOT EXISTS clients (
	id integer PRIMARY KEY AUTOINCREMENT,
	name varchar(255),
	phone_number varchar(255),
	address varchar(255)
);

CREATE TABLE IF NOT EXISTS pets (
	id integer PRIMARY KEY AUTOINCREMENT,
	name varchar(255),
	gender varchar(255),
	altered boolean,
	client_id integer,
	FOREIGN KEY (client_id) REFERENCES clients(id)

);


CREATE TABLE IF NOT EXISTS users (
	id integer PRIMARY KEY AUTOINCREMENT,
	username varchar(255),
	encoded_password varchar(255),
	role varchar(255)
);

CREATE TABLE IF NOT EXISTS appointments (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  title varchar(255),
  date date NOT NULL,
  time time NOT NULL,
  notes varchar(600),
  client_id integer,
  pet_id integer,  
  FOREIGN KEY (client_id) REFERENCES clients(id)
  FOREIGN KEY (pet_id) REFERENCES pets(id)
);

insert into users values (null, 'admin', 'password', 'SUPER_ADMIN');
