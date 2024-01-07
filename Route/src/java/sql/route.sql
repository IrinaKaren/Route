CREATE DATABASE route

CREATE TABLE route(
    id serial primary key,
    nom varchar(100),
    cout_reparation decimal
);

CREATE TABLE borne_kilometrique(
    id serial primary key,
    nom varchar(100),
    id_route int references route(id)
);

CREATE TABLE degat(
    id int primary key,
    nom varchar(100)
);

------- fenoina 10 par prioriter pk
CREATE TABLE priorite_esthetique(
    id serial primary key,
    id_pk int references borne_kilometrique(id)
);

------- priorite economique vitaina daholo aloha ny pk anatin'ilay route prioritaire
CREATE TABLE priorite_economique(
    id serial primary key,
    id_route int references route(id)
);

CREATE TABLE situation_routiere(
    id serial primary key,
    date_situation Timestamp,
    id_pk int references borne_kilometrique(id),
    id_degat int references degat(id)
);4

CREATE TABLE construction(
    id serial primary key,
    date_situation Timestamp,
    id_route int references route(id),
    id_pk int references borne_kilometrique(id),
    id_degat int references degat(id),
    cout decimal
);