BEGIN TRANSACTION;


DROP TABLE IF EXISTS card_deck;
DROP TABLE IF EXISTS card;
DROP TABLE IF EXISTS deck;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

CREATE TABLE deck (
        deck_id serial NOT NULL,
        deck_name varchar(50),
        deck_creator_id int NOT NULL,
        deck_creator_name varchar(50),
        deck_tag varchar(200),
        is_public boolean,
        
        CONSTRAINT pk_deck PRIMARY KEY (deck_id),
        CONSTRAINT fk_deck_user FOREIGN KEY (deck_creator_id) REFERENCES users (user_id)
                
);

CREATE TABLE card (
        card_id serial,
        card_creator_id int NOT NULL,
        card_creator varchar(50),
        front_info varchar(200),
        back_info varchar(200), 
        card_tag varchar(200),
        is_public boolean,
       
        CONSTRAINT pk_card PRIMARY KEY (card_id),
        CONSTRAINT fk_card_user FOREIGN KEY (card_creator_id) REFERENCES users (user_id)

);

CREATE TABLE card_deck (

        deck_id int NOT NULL,
        card_id int NOT NULL,
        
        CONSTRAINT pk_card_deck PRIMARY KEY (card_id, deck_id),
        CONSTRAINT fk_card_deck_deck FOREIGN KEY (deck_id) REFERENCES deck (deck_id),
        CONSTRAINT fk_card_deck_card FOREIGN KEY (card_id) REFERENCES card (card_id)
        
);

COMMIT TRANSACTION;
