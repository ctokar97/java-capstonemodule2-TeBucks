BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, accounts, transfers;

CREATE TABLE users (
	user_id serial NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(50),
	role varchar(20),
	CONSTRAINT pk_users PRIMARY KEY (user_id),
	CONSTRAINT uq_username UNIQUE (username)
);

INSERT INTO users (username,password_hash,role) VALUES ('user1','user1','ROLE_USER'); -- 1
INSERT INTO users (username,password_hash,role) VALUES ('user2','user2','ROLE_USER'); -- 2
INSERT INTO users (username,password_hash,role) VALUES ('user3','user3','ROLE_USER'); -- 3


CREATE TABLE accounts(
        account_id serial NOT NULL,
        user_id INTEGER NOT NULL,
        balance DECIMAL(12, 2) DEFAULT(1000.00),
        CONSTRAINT pk_accounts PRIMARY KEY (account_id),
        CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (user_id)
);

INSERT INTO accounts (user_id, balance) VALUES (1, DEFAULT); --1
INSERT INTO accounts (user_id, balance) VALUES (2, 500.00); --2
INSERT INTO accounts (user_id, balance) VALUES (3, 1000.00); --3

CREATE TABLE transfers(
    transfer_id serial NOT NULL,
    transfer_type VARCHAR(25) NOT NULL,
    transfer_status VARCHAR(15) NOT NULL,
    user_from INTEGER NOT NULL,
    user_to INTEGER NOT NULL,
    amount DECIMAL(12, 2) NOT NULL,
    user_id INTEGER NOT NULL,
    CONSTRAINT pk_transfer_id PRIMARY KEY (transfer_id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) references users (user_id),
    CONSTRAINT chk_transfer_status CHECK (transfer_status = 'Pending' OR  transfer_Status = 'Rejected' OR  transfer_status = 'Approved'),
    CONSTRAINT chk_transfer_type CHECK (transfer_type = 'Send' OR transfer_type = 'Request')
);

COMMIT TRANSACTION;
