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
  date varchar(25) NOT NULL,
  time varchar(25) NOT NULL,
  notes varchar(600),
  client_id integer,
  pet_id integer,  
  FOREIGN KEY (client_id) REFERENCES clients(id)
  FOREIGN KEY (pet_id) REFERENCES pets(id)
);

Alter Table Clients add column email varchar(255);

insert into users values (null, 'admin', 'password', 'SUPER_ADMIN');

CREATE TABLE IF NOT EXISTS AppointmentTime (
  id INTEGER PRIMARY KEY AUTOINCREMENT,  
  time varchar(25) NOT NULL 
);

INSERT INTO AppointmentTime('time') VALUES
('08:00'),
('09:00'),
('10:00'),
('11:00'),
('12:00'),
('13:00'),
('14:00'),
('15:00'),
('16:00'),
('17:00'),
('18:00'),
('19:00'),
('20:00')
