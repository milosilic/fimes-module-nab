CREATE TABLE xnab_osnov_izuzeca
(
   id_osnov_izuzeca integer NOT NULL, 
   naziv character varying(128) NOT NULL, 
   f_arhivirano boolean NOT NULL DEFAULT false, 
    PRIMARY KEY (id_osnov_izuzeca)
) 
WITH (
  OIDS = FALSE
)
;
ALTER TABLE xnab_osnov_izuzeca OWNER TO "fimes-demo";

CREATE TABLE xnab_izvor_finansiranja
(
   id_izvor_finansiranja integer NOT NULL, 
   naziv character varying(256) NOT NULL, 
   f_arhivirano boolean NOT NULL DEFAULT false, 
    PRIMARY KEY (id_izvor_finansiranja)
) 
WITH (
  OIDS = FALSE
)
;
ALTER TABLE xnab_izvor_finansiranja OWNER TO "fimes-demo";

CREATE SEQUENCE seq_nab_procena_po_godini
   INCREMENT 1
   START 1
   MINVALUE 1;
ALTER TABLE seq_nab_procena_po_godini OWNER TO "fimes-demo";
