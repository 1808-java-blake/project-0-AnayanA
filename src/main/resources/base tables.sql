CREATE TABLE stan_user(
	user_id SERIAL PRIMARY KEY INTEGER,
	firstname VARCHAR(20),
	lastname VARCHAR(25),
	username VARCHAR(20) NOT NULL UNIQUE,
	pass VARCHAR(25)NOT NULL);
	
CREATE TABLE admin_users(
	admin_id SERIAL PRIMARY KEY INTEGER,
	firstname VARCHAR(20),
	lastname VARCHAR(25),
	username VARCHAR(20)NOT NULL UNIQUE,
	pass VARCHAR(25)NOT NULL);
	
CREATE TABLE accounts(
	account_number PRIMARY KEY INTEGER,
	balance NUMERIC(10,2));
	
CREATE TABLE account_hub(
	user_id FOREIGN KEY INTEGER,
	account_name VARCHAR(20),
	account_number FOREIGN KEY INTEGER);
	
CREATE TABLE trans_his(
	user_id FOREIGN KEY INTEGER NOT NULL,
	account_number FOREIGN KEY INTEGER NOT NULL,
	trans VARCHAR(40)NOT NULL);
	
INSERT INTO admin_users values (0,'1st', 'and only' 'zero', 'zero');