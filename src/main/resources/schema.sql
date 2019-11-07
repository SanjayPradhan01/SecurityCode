DROP TABLE IF EXISTS student;

create table student
(
   id integer IDENTITY not null,
   name varchar(255) not null,
   passport varchar(255) not null,
   primary key(id)
);

create table book
(
   bookid integer IDENTITY not null,
   studentsid integer not null,
   bookname varchar(255) not null,
   primary key(bookid),
   FOREIGN KEY (studentsid) REFERENCES student(id) ON DELETE CASCADE
);


create table user
(
   username varchar2(255) not null,
   password varchar2(255) not null,
   primary key(username)
);