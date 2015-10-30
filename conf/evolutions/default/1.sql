# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table "ANSWERS" ("name" VARCHAR NOT NULL,"email" VARCHAR NOT NULL,"startQuestion" VARCHAR NOT NULL,"stopQuestion" VARCHAR NOT NULL,"continueQuestion" VARCHAR NOT NULL);
create table "USER" ("name" VARCHAR NOT NULL,"q1" VARCHAR NOT NULL,"q2" VARCHAR NOT NULL,"q3" VARCHAR NOT NULL,"email" VARCHAR NOT NULL);

# --- !Downs

drop table "USER";
drop table "ANSWERS";

