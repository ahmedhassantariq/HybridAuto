create database hybrid_autoDB

--Car
create table Car (
car_ID int primary key identity(1,1) not null,
car_make varchar(20) not null,
car_model varchar(15) not null,
car_year int not null 
)

--Product Category
create table Product (
product_ID int primary key identity(1,1),
product_Category varchar(20) not null,
car_ID int foreign key references car(car_ID) not null
)
alter table Product add constraint UK01_product unique(product_Category,car_ID)

--Stock
create table stock (
stock_ID int primary key identity(1,1),
product_ID int foreign key references Product(product_ID) not null,
serial_number varchar(50) unique not null,
cost money,
[comments] varchar(200),
created_datetime datetime,
condition varchar(10),
display bit
)

--Customer
create table Customer(
customer_ID smallint primary key identity(1,1),
phone varchar(15) unique null,
first_Name varchar(20) not null,
middle_Name varchar(20) null,
last_Name varchar(20) null,
areaCode varchar(5) null,
[address] varchar(255) null,
)

--Order
create table [Order] (
order_ID int primary key,
customer_ID smallint foreign key references [Customer](customer_ID) not null,
total money,
created_datetime datetime
)

--Order Details
create table Order_Details(
order_ID int foreign key references [Order](order_ID),
stock_ID int foreign key references Stock(stock_ID)
)

--Bills
create table Bills (
bill_ID smallint primary key identity(1,1),
bill_Type varchar(15) not null,
amount money,
created_Datetime date
)


