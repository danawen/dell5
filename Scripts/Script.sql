INSERT INTO appointments
(title, date, time, notes, client_id, pet_id)
VALUES('Rabbies Vaccination for Adobe','2019-03-22','10:00', 'Rabbies', 3, 2);

update appointments
set title = 'Chewy for Worms'
where id = 6

delete from appointments
where id = 6


select * from appointments

select * from pets
select * from clients

SELECT a.title, a.date, a.time,a.notes,p.name, c.name FROM appointments a join clients c on a.client_id = c.id join pets p on a.pet_id = p.id where time = '08:00'




update appointments
set date = '2019-03-23'
where id = 1

delete from appointments

select * from users

insert into users
(username,encoded_password,role)
VALUES('danny','iamapet','READ_ONLY')


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