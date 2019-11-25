-- SuperRent Tables & Records

-- Drop all existing tables

drop table Branch cascade constraints;
drop table Customer cascade constraints;
drop table VehicleType cascade constraints;
drop table Reservation cascade constraints;
drop table Vehicle cascade constraints;
drop table Rental cascade constraints;
drop table Return cascade constraints;

-- Add new tables

create table Branch(
                       location varchar2(50),
                       city varchar2(20),
                       primary key(location, city));

create table Customer(
                         dlicense varchar2(30) primary key,
                         name varchar2(30),
                         phoneNumber varchar2(20) not null,
                         address varchar2(50));

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

create table Vehicle(
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
                        foreign key(location, city) references Branch,
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
                       odometer float(126),
                       fulltank char(1),
                       value float(126),
                       foreign key(rid) references Rental ON DELETE CASCADE,
                       check (fulltank IN ('Y','N')));


-- Insert values
insert into Branch values ('1234 Main St', 'Vancouver');
insert into Branch values ('23 No.3 Rd', 'Richmond');
insert into Branch values ('251 Creek Rd', 'Sechelt');
insert into Branch values ('1342 W. 22Ave', 'Burnaby');

insert into Customer values ('123', 'James', '6041234567', '1st Grand St');
insert into Customer values ('234', 'Jane', '7781230543', '2nd Granville St');
insert into Customer values ('345', 'Wilson', '1893819021', '3rd Main St');
insert into Customer values ('456', 'Frank', '2194839123', '4th Down Ave');
insert into Customer values ('567', 'Jess', '13701491184', '5th Up St');
insert into Customer values ('789', 'Boss', '1230529102', '6th Left Rd');
insert into Customer values ('890', 'John', '1047197182', '7th Right Ave');
insert into Customer values ('901', 'Jesus', '8791246920', '8th GUI St');
insert into Customer values ('222', 'Dank', '1238791471', '5th Up St');
insert into Customer values ('333', 'Both', '1230521234', '6th Left Rd');
insert into Customer values ('444', 'Snake', '1047197122', '7th Right Ave');
insert into Customer values ('555', 'Quack', '8791246910', '8th GUI St');

insert into VehicleType values ('Ambulance', 'Auto',  1000, 200, 20, 100, 20, 5, 100);
insert into VehicleType values ('Bus', 'Auto', 900, 150, 18, 100, 20, 5, 100);
insert into VehicleType values ('Carriage', 'Manual', 500, 100, 10, 50, 10, 2, 50);
insert into VehicleType values ('Truck', 'Auto', 800, 130, 15, 90, 18, 5, 80);
insert into VehicleType values ('Van', 'Manual', 400, 80, 8, 45, 10, 4, 40);
insert into VehicleType values ('Standard', 'Auto', 500, 80, 10, 50, 10, 2, 80);

insert into Reservation values (123456789, 'Bus', '234', TO_DATE('2019-09-12','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-09-15','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'));
insert into Reservation values (234567890, 'Carriage', '567', TO_DATE('2019-12-06','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-12-12','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'));
insert into Reservation values (345678901, 'Truck', '456', TO_DATE('2019-10-05','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-10-08','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'));
insert into Reservation values (213941629, 'Bus', '890', TO_DATE('2019-12-31','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2020-01-02','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'));
insert into Reservation values (120923819, 'Ambulance', '567', TO_DATE('2019-11-30','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-12-20','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'));
insert into Reservation values (789154810, 'Van', '789', TO_DATE('2019-10-20','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-11-22','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'));
insert into Reservation values (120387410, 'Standard', '901', TO_DATE('2019-11-23','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-12-25','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'));
insert into Reservation values (809120377, 'Carriage', '901', TO_DATE('2018-10-24','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-11-29','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'));
insert into Reservation values (891023812, 'Truck', '890', TO_DATE('2019-09-12','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-09-15','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'));
insert into Reservation values (234567891, 'Carriage', '567', TO_DATE('2019-09-12','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-09-15','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'));
insert into Reservation values (345678902, 'Truck', '456', TO_DATE('2019-09-12','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-09-15','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'));
insert into Reservation values (213941623, 'Bus', '123', TO_DATE('2019-09-12','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2020-09-15','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'));
insert into Reservation values (789154815, 'Van', '789', TO_DATE('2019-09-12','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-09-15','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'));
insert into Reservation values (120387416, 'Standard', '901', TO_DATE('2019-09-12','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-09-15','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'));
insert into Reservation values (213941621, 'Bus', '333', TO_DATE('2019-09-12','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2020-09-15','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'));
insert into Reservation values (789154812, 'Van', '444', TO_DATE('2019-09-12','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-09-15','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'));
insert into Reservation values (120387414, 'Standard', '555', TO_DATE('2019-09-12','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-09-15','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'));

insert into Vehicle values ('D123', 'Honda', 'LX', 2012, 'red', 1000, 'available', 'Carriage', '1234 Main St', 'Vancouver');
insert into Vehicle values ('A234', 'Benz', 'EX', 2018, 'yellow', 500, 'available', 'Van', '1234 Main St', 'Vancouver');
insert into Vehicle values ('B234', 'Volkswagen', 'EX-T', 2013, 'black', 80000, 'maintenance', 'Bus', '1234 Main St', 'Vancouver');
insert into Vehicle values ('E901', 'Benz', 'SI', 2017, 'purple', 5520, 'rented', 'Standard', '23 No.3 Rd', 'Richmond');
insert into Vehicle values ('G214', 'Toyota', 'EX', 2009, 'white', 1200, 'available', 'Carriage', '251 Creek Rd', 'Sechelt');
insert into Vehicle values ('V120', 'Volkswagen', 'EX', 2013, 'black', 8000, 'rented', 'Bus', '1342 W. 22Ave', 'Burnaby');
insert into Vehicle values ('E888', 'Ford', 'LX', 2017, 'purple', 4210, 'rented', 'Standard', '1342 W. 22Ave', 'Burnaby');
insert into Vehicle values ('H902', 'Honda', 'EX-T', 2005, 'grey', 90000, 'available', 'Truck', '1234 Main St', 'Vancouver');
insert into Vehicle values ('N991', 'Benz', 'EX-L', 2018, 'yellow', 5012, 'available', 'Van', '1234 Main St', 'Vancouver');
insert into Vehicle values ('P102', 'Volkswagen', 'EX-L', 2013, 'black', 8340, 'maintenance', 'Bus', '251 Creek Rd', 'Sechelt');
insert into Vehicle values ('E000', 'Benz', 'Si', 2017, 'purple', 10590, 'available', 'Standard', '23 No.3 Rd', 'Richmond');
insert into Vehicle values ('Y819', 'Toyota', 'Touring', 2009, 'white', 12010, 'available', 'Ambulance', '251 Creek Rd', 'Sechelt');
insert into Vehicle values ('L019', 'Volkswagen', 'EX-T', 2013, 'black', 80231, 'available', 'Bus', '23 No.3 Rd', 'Richmond');
insert into Vehicle values ('S716', 'Ford', 'Si', 2017, 'purple', 7600, 'rented', 'Standard', '1342 W. 22Ave', 'Burnaby');
insert into Vehicle values ('E001', 'Benz', 'Si', 2017, 'purple', 10590, 'available', 'Bus', '23 No.3 Rd', 'Richmond');
insert into Vehicle values ('Y812', 'Toyota', 'Touring', 2009, 'white', 12010, 'available', 'Standard', '251 Creek Rd', 'Sechelt');
insert into Vehicle values ('L013', 'Volkswagen', 'EX-T', 2013, 'black', 80231, 'available', 'Van', '23 No.3 Rd', 'Richmond');
insert into Vehicle values ('S714', 'Ford', 'Si', 2017, 'purple', 7600, 'rented', 'Truck', '1342 W. 22Ave', 'Burnaby');

insert into Rental values (98765432, 'L019', '123', TO_DATE('2019-09-12','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-09-15','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), 74000, 'Master Card', '4819129071829990', TO_DATE('2021-10-22','YYYY-MM-DD'), 123456789);
insert into Rental values (78912061, 'G214', '890', TO_DATE('2019-12-06','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-12-12','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), 17733, 'VISA', '1491027491026451', TO_DATE('2022-05-30','YYYY-MM-DD'), 234567890);
insert into Rental values (90172461, 'H902', '890', TO_DATE('2019-10-05','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-10-08','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), 12388, 'VISA', '9731920162830017', TO_DATE('2020-02-28','YYYY-MM-DD'), 345678901);
insert into Rental values (80124742, 'V120', '567', TO_DATE('2019-12-31','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2020-01-02','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), 8000, 'Master Card', '0917829194230124', TO_DATE('2023-05-01','YYYY-MM-DD'), 213941629);
insert into Rental values (12893951, 'Y819', '234', TO_DATE('2019-11-30','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2020-12-20','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), 12000, 'Master Card', '9710475204778122', TO_DATE('2019-12-25','YYYY-MM-DD'), 120923819);
insert into Rental values (12972631, 'N991', '345', TO_DATE('2018-10-20','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-11-22','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), 21300, 'VISA', '9031638204720038', TO_DATE('2021-07-20', 'YYYY-MM-DD'), 789154810);
insert into Rental values (41709411, 'Y819', '123', TO_DATE('2019-08-27','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-09-15','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), 19237, 'VISA', '0983472023478994', TO_DATE('2022-07-12','YYYY-MM-DD'), null);
insert into Rental values (18297300, 'E000', '234', TO_DATE('2019-08-27','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-12-28','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), 13081, 'VISA', '7324962952030498', TO_DATE('2022-07-13','YYYY-MM-DD'), null);
insert into Rental values (41709410, 'P102', '789', TO_DATE('2019-08-27','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-12-21','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), 19221, 'Master Card', '6812949791277393', TO_DATE('2022-07-14','YYYY-MM-DD'), null);
insert into Rental values (41709413, 'G214', '890', TO_DATE('2019-08-27','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-09-15','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), 19291, 'Master Card', '6812949791289011', TO_DATE('2022-07-15','YYYY-MM-DD'), null);
insert into Rental values (89012481, 'E901', '901', TO_DATE('2019-11-23','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-12-25','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), 12309, 'VISA', '1720488012389091', TO_DATE('2020-01-02','YYYY-MM-DD'), null);
insert into Rental values (87912379, 'S716', '234', TO_DATE('2019-09-12','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-09-15','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), 74000, 'Master Card', '4819129071829990', TO_DATE('2021-10-22','YYYY-MM-DD'), null);
insert into Rental values (78912361, 'G214', '890', TO_DATE('2019-09-12','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-12-12','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), 17733, 'VISA', '1491027491026451', TO_DATE('2022-05-30','YYYY-MM-DD'), 120387414);
insert into Rental values (90172061, 'H902', '890', TO_DATE('2019-10-05','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-10-08','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), 12388, 'VISA', '9731920162830017', TO_DATE('2020-02-28','YYYY-MM-DD'), 789154812);
insert into Rental values (80124642, 'V120', '567', TO_DATE('2019-12-31','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2020-01-02','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), 8000, 'Master Card', '0917829194230124', TO_DATE('2023-05-01','YYYY-MM-DD'), 213941621);
insert into Rental values (12893151, 'Y819', '234', TO_DATE('2019-11-30','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2020-12-20','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), 12000, 'Master Card', '9710475204778122', TO_DATE('2019-12-25','YYYY-MM-DD'), null);
insert into Rental values (12972531, 'N991', '345', TO_DATE('2018-10-20','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-11-22','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), 21300, 'VISA', '9031638204720038', TO_DATE('2021-07-20', 'YYYY-MM-DD'), null);
insert into Rental values (41709111, 'Y819', '123', TO_DATE('2019-08-27','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-09-15','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), 19237, 'VISA', '0983472023478994', TO_DATE('2022-07-12','YYYY-MM-DD'), null);
insert into Rental values (18296300, 'E000', '234', TO_DATE('2019-08-27','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-12-28','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), 13081, 'VISA', '7324962952030498', TO_DATE('2022-07-13','YYYY-MM-DD'), null);
insert into Rental values (41709499, 'P102', '789', TO_DATE('2019-08-27','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-12-21','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), 19221, 'Master Card', '6812949791277393', TO_DATE('2022-07-14','YYYY-MM-DD'), null);
insert into Rental values (41709412, 'G214', '890', TO_DATE('2019-08-27','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-09-15','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), 19291, 'Master Card', '6812949791289011', TO_DATE('2022-07-15','YYYY-MM-DD'), null);
insert into Rental values (89011281, 'E901', '901', TO_DATE('2019-11-23','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), TO_DATE('2019-12-25','YYYY-MM-DD'), TO_TIMESTAMP('9:00', 'hh24:mi'), 12309, 'VISA', '1720488012389091', TO_DATE('2020-01-02','YYYY-MM-DD'), null);

insert into Return values (98765432, TO_DATE('2019-09-15','YYYY-MM-DD'), 80000, 'Y', 4120);
insert into Return values (41709410, TO_DATE('2019-09-15','YYYY-MM-DD'), 12340, 'Y', 1200);
insert into Return values (90172461, TO_DATE('2019-10-08','YYYY-MM-DD'), 8500, 'N', 2266);
insert into Return values (12972631, TO_DATE('2019-01-24','YYYY-MM-DD'), 3000, 'N', 2913);
insert into Return values (41709413, TO_DATE('2019-09-15','YYYY-MM-DD'), 1231, 'N', 1230);
insert into Return values (89011281, TO_DATE('2019-09-15','YYYY-MM-DD'), 4321, 'Y', 1290);