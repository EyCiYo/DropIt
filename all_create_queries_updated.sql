CREATE TABLE tbl_UserProfile (
UserID VARCHAR(13) NOT NULL,
FullName VARCHAR(50) NOT NULL,
Address VARCHAR(100) NOT NULL,
Email VARCHAR(50) PRIMARY KEY,
MobileNumber VARCHAR(20) NOT NULL,
Password VARCHAR(50) NOT NULL,
Email_Subscription BOOLEAN,
SMS_Subscription BOOLEAN);


INSERT into tbl_UserProfile values('User_bsh356sh','Aloha','Alohas House, Andheri, 689678','aloha@gmail.com','9846789099','Aloha@2024',true,false);
INSERT into tbl_UserProfile values('User_mljf32ah','Sumesh','Sumesh House, Delhi, 600988','sumesh@gmail.com','8112536788','Sumesh@2024',false,false);


CREATE TABLE tbl_Admin(
Email VARCHAR(50) PRIMARY KEY,
Name VARCHAR(50) NOT NULL,
Password VARCHAR(50) NOT NULL);

INSERT into tbl_Admin values('admin@gmail.com','Admin','Admin@2024');


CREATE TABLE tbl_Booking (
Booking_ID VARCHAR(13) PRIMARY KEY,
Sender_Name varchar(50) not null,
Sender_Email VARCHAR(50) NOT NULL,
Sender_address varchar(100) not null,
Sender_mobile varchar(10) not null,
Rec_Name VARCHAR(50) NOT NULL,
Rec_Address VARCHAR(100) NOT NULL,
Rec_Mobile VARCHAR(20) NOT NULL,
Rec_Email VARCHAR(40) NOT NULL,
Par_Length DECIMAL(10,2) NOT NULL,
Par_Height DECIMAL(10,2) NOT NULL,
Par_Width DECIMAL(10,2) NOT NULL,		
Par_Weight_Gram DECIMAL(10, 2) NOT NULL,
Par_Contents_Description VARCHAR(200) NOT NULL,
Par_Shipping_Speed VARCHAR(20) NOT NULL,
Par_Packing_Type VARCHAR(20) NOT NULL,
Par_PickupTime DATE NOT NULL,
Par_DropoffTime DATE NOT NULL,
Par_BookingTime DATE NOT NULL,
Par_Cost DECIMAL(10, 2) NOT NULL, 
Par_Status VARCHAR(20) NOT NULL);


 
 
