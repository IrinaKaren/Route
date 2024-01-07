CREATE OR REPLACE VIEW v_situation_routiere AS
SELECT 
    r.id as id_route,
    r.nom as route,
    r.cout_reparation as cout_reparation,
    bk.id as idpk,
    bk.nom as pk,
    d.id as id_degat,
    d.nom as degat,
    sr.id as id_situation,
FROM
    route r
JOIN 
    borne_kilometrique bk ON bk.id_route = r.id
JOIN
    situation_routiere sr ON sr.id_pk = bk.id
JOIN 
    degat d ON d.id = sr.id_degat



CREATE OR REPLACE VIEW v_prioriter_esthetique AS
SELECT 
    pe.id as id_prioriter,
    r.id as id_route,
    r.nom as route,
    r.cout_reparation as cout_reparation,
    bk.id as idpk,
    bk.nom as pk,
    d.id as id_degat,
    d.nom as degat,
    sr.id as id_situation,
    sr.date_situation as date_situation
FROM
    route r
JOIN 
    borne_kilometrique bk ON bk.id_route = r.id
JOIN 
    priorite_esthetique pe ON pe.id_pk = bk.id
JOIN
    situation_routiere sr ON sr.id_pk = bk.id
JOIN 
    degat d ON d.id = sr.id_degat
ORDER BY pe.id ASC



CREATE OR REPLACE VIEW v_prioriter_economique AS
SELECT 
    pe.id as id_prioriter,
    r.id as id_route,
    r.nom as route,
    r.cout_reparation as cout_reparation,
    bk.id as idpk,
    bk.nom as pk,
    d.id as id_degat,
    d.nom as degat,
    sr.id as id_situation,
    sr.date_situation as date_situation
FROM
    route r
JOIN 
    borne_kilometrique bk ON bk.id_route = r.id
JOIN 
    priorite_economique pe ON pe.id_route = bk.id_route
JOIN
    situation_routiere sr ON sr.id_pk = bk.id
JOIN 
    degat d ON d.id = sr.id_degat
ORDER BY pe.id ASC
