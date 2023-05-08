#CREATE DATABASE
CREATE DATABASE travel_mate;
#CREATE DATABASE SERVICE ACCOUNTS
CREATE USER 'user'@'localhost' IDENTIFIED BY 'user';
#DATABASE GRANTS
GRANT SELECT ON travel_mate.* to 'user'@'localhost';
GRANT INSERT ON travel_mate.* to 'user'@'localhost';
GRANT DELETE ON travel_mate.* to 'user'@'localhost';
GRANT UPDATE ON travel_mate.* to 'user'@'localhost';