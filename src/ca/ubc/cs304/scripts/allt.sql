-- SuperRent Tables & Records

-- Drop all existing tables

drop table Customer cascade constraints;
drop table VehicleType cascade constraints;
drop table Reservation cascade constraints;
drop table Vehicle cascade constraints;
drop table Rental cascade constraints;
drop table Return cascade constraints;

-- Add new tables

create table Customer(
dlicense varchar2(30) primary key,
name varchar2(30),
phoneNumber varchar2(20) not null,
address varchar2(50),
numPoints number(9,0));

create table VehicleType(
vtname varchar2(50) primary key,
features varchar2(100),
wrate float(100),
drate float(50),
hrate float(50),
wirate float(100),
dirate float(50),
hirate float(50),
krate float(20));

create table Reservation(
confNo number(9,0) primary key,
vtname varchar2(30),
dlicense varchar2(30),
fromDate date,
fromTime timestamp,
toDate date,
toTime timestamp,
foreign key(dlicense) references Customer,
foreign key(vtname) references VehicleType);

create Table Vehicle(
vlicense varchar2(10) primary key,
make varchar2(10),
model varchar2(10),
year integer,
color varchar2(50),
odometer float(126),
status varchar2(11),
vtname varchar2(30),
location varchar2(50),
city varchar2(20),
foreign key(vtname) references VehicleType,
check (status IN ('rented', 'maintenance', 'available')));

create table Rental(
rid number(9,0) primary key,
vlicense varchar2(10),
dlicense varchar2(30),
fromDate date,
fromTime timestamp,
toDate date,
toTime timestamp,
odometer float(126),
cardName varchar2(50),
cardNo varchar2(19),
ExpDate date,
confNo number(9,0),
foreign key(dlicense) references Customer,
foreign key(vlicense) references Vehicle,
foreign key(confNo) references Reservation);

create table Return(
rid number(9,0) primary key,
rdate date,
time timestamp,
odometer float(126),
fulltank char(1),
value float(126),
foreign key(rid) references Rental ON DELETE CASCADE,
check (fulltank IN ('Y','N')));
