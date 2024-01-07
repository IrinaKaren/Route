INSERT INTO route (nom,cout_reparation) 
VALUES
('RN1',1000),
('RN2',1500),
('RN3',2800);

INSERT INTO borne_kilometrique (nom,id_route)
VALUES
('PK1',1),('PK2',1),('PK3',1),('PK4',1),('PK5',1),
('PK6',2),('PK7',2),('PK8',2),('PK9',2),('PK10',2),
('PK11',3),('PK12',3),('PK13',3),('PK14',3),('PK15',3);

INSERT INTO degat (id,nom)
VALUES
(1,'tres endommagé'),(2,'endommagé'),(3,'un peu endommagé'),(4,'très troué'),(5,'troué'),
(6,'un peu troué'),(7,'tres sale'),(8,'sale'),(9,'un peu sale'),(10,'bon etat');

INSERT INTO priorite_esthetique (id_pk)
VALUES
(60),(59),(58),(57),(50),(49),
(53),(52),(51),(48),(56),(54),
(47),(46),(55);

INSERT INTO priorite_economique (id_route)
VALUES
(2),(1),(3);

------ SItuation par defaut ----------------------------------------------------
INSERT INTO situation_routiere (id_pk,id_degat)
VALUES
(46,10),(47,10),(48,10),(49,10),(50,10),
(51,10),(52,10),(53,10),(54,10),(55,10),
(56,10),(57,10),(58,10),(59,10),(60,10);

update situation_routiere 
set id_degat = 5 where id > 5


