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
Sender_Email VARCHAR(50) NOT NULL,
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
Par_Status VARCHAR(20) NOT NULL,
FOREIGN KEY (Sender_Email) REFERENCES tbl_UserProfile(Email));

INSERT INTO tbl_Booking (
    Booking_ID, Sender_Email, Rec_Name, Rec_Address, Rec_Mobile, Rec_Email,
    Par_Length, Par_Height, Par_Width, Par_Weight_Gram, Par_Contents_Description,
    Par_Shipping_Speed, Par_Packing_Type, Par_PickupTime, Par_DropoffTime,
    Par_BookingTime, Par_Cost, Par_Status
) VALUES
('BKNO_a1B2c3D4', 'aloha@gmail.com', 'John Doe', '123 Main St, Mumbai, 400001', '9876543210', 'john.doe@example.com',
 25.50, 15.75, 10.00, 1200.00, 'Clothing',
 'Standard', 'Standars', '2024-10-18', '2024-10-20',
 '2024-10-17', 1500.00, 'Booked'),

('BKNO_f5G6h7I8', 'sumesh@gmail.com', 'Jane Smith', '456 Elm St, Delhi, 110001', '9988776655', 'jane.smith@example.com',
 30.00, 20.00, 15.00, 2000.00, 'Electronics',
 'Express', 'Fragile', '2024-10-19', '2024-10-21',
 '2024-10-17', 2500.00, 'In Transit');
 
 UPDATE TBL_BOOKING SET PAR_STATUS ='Booked' where booking_id = 'BKNO_a1B2c3D4';
 UPDATE TBL_BOOKING SET PAR_STATUS ='In Transit' where booking_id = 'BKNO_f5G6h7I8';
 
