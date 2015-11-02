# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table `ANSWERS` (`name` VARCHAR(254) NOT NULL,`email` VARCHAR(254) NOT NULL,`startQuestion` VARCHAR(254) NOT NULL,`stopQuestion` VARCHAR(254) NOT NULL,`continueQuestion` VARCHAR(254) NOT NULL);
create table `USER` (`name` VARCHAR(254) NOT NULL,`q1` VARCHAR(254) NOT NULL,`q2` VARCHAR(254) NOT NULL,`q3` VARCHAR(254) NOT NULL,`email` VARCHAR(254) NOT NULL);

# --- !Downs

drop table `USER`;
drop table `ANSWERS`;

