
create table city(
	cityId integer primary key auto_increment,
	cityName varchar(20) not null
);

create table area(
	areaId integer primary key auto_increment,
	areaName varchar(30) not null,
	cityId integer references city(cityId)
);


create table location(
  	locationId integer primary key auto_increment, 
  	title varchar(30) ,
	category varchar(30),
 	description varchar(255),
 	timings varchar(150),
	contactNumber varchar(25),
	address varchar(200),
	active boolean,
	cityId integer references city(cityId),
	areaId integer references area(areaId),
	latitude double,
	longitude double
);

create table users (
	userId integer primary key auto_increment,
	firstname varchar(20),
	lastname  varchar(20),
	gender	  varchar(10),
	contact	  varchar(18),
	email	  varchar(30),
	password  varchar(255),
	age		 integer,
	profilePicPath varchar(120)
);



create table offers(
   offerId integer primary key auto_increment,
   title varchar(200),
   description varchar(200),
   active boolean,
   startDate date, 
   endDate date,
   locationId integer references location(locationId),
   foodType varchar(50)
);

alter table location add foodType varchar(50);

alter table offers add offerPicPath varchar(120);


