use javabase;

create table users(
	userID int not null,
    username varchar(50) not null,
    user_password varchar(30) not null,
    PRIMARY KEY(userID)
);

create table wallet(
	walletID int not null,
    balance float not null,
    PRIMARY KEY(walletID),
    FOREIGN KEY(walletID) REFERENCES users(userID)
);
create table user_transaction (
	transactionID int not null,
    transaction_type varchar(50) not null,
    transaction_date DATETIME not null,
    transaction_amount float not null,
    userID int not null,
    PRIMARY KEY(transactionID),
    FOREIGN KEY(userID) REFERENCES users(userID)
);