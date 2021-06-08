create table if not exists User (
  id integer primary key auto_increment,
  name varchar(50) not null,
  pass_token varchar(48) not null
);