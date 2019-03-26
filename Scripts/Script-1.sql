drop table appointments;

create table appointments (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title NVARCHAR(255),
    date DATE,
    time TIME,
    notes NVARCHAR(600),
    client_id integer,
  	pet_id integer,  
  	FOREIGN KEY (client_id) REFERENCES clients(id)
  	FOREIGN KEY (pet_id) REFERENCES pets(id)
);


INSERT INTO appointments(title, date, time, notes, client_id, pet_id) VALUES ('Vaccines for Scruffy','2019-03-26','09:00','Please apply cream after procedure',6,3);
