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
