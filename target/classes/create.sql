CREATE TABLE `minecraft-db`.PLAYER (
                                       player_id VARCHAR(50) NOT NULL,
                                       naam VARCHAR(50) NOT NULL,
                                       leeftijd INTEGER NULL,
                                       geslacht VARCHAR(50) NULL,

                                       CONSTRAINT player_pk PRIMARY KEY (player_id)
);

