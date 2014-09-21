CREATE SEQUENCE seq_nab_plan
   INCREMENT 1
   START 1
   MINVALUE 1;
ALTER TABLE seq_nab_plan OWNER TO "fimes-demo";

ALTER TABLE nab_plan
   ALTER COLUMN trenutak_kreiranja SET DEFAULT now();
   
ALTER TABLE nab_javna_nabavka ADD COLUMN id_plan integer NOT NULL;
ALTER TABLE nab_javna_nabavka ADD FOREIGN KEY (id_plan) REFERENCES nab_plan (id_plan) ON UPDATE NO ACTION ON DELETE NO ACTION;

CREATE TABLE xnab_konto
(
   id_konto integer, 
   naziv character varying(512), 
   CONSTRAINT id_konto PRIMARY KEY (id_konto)
) 
WITH (
  OIDS = FALSE
)
;
ALTER TABLE xnab_konto OWNER TO "fimes-demo";
ALTER TABLE xnab_konto ADD COLUMN konto character varying(128);

INSERT INTO xnab_predmet_nabavke VALUES (1, 'usluge održavanja i popravke', false, 2);
INSERT INTO xnab_predmet_nabavke VALUES (2, 'usluge kopnenog saobraćaja , uključujući usluge prevoza u oklopljenim vozilima i kurirske usluge', false, 2);
INSERT INTO xnab_predmet_nabavke VALUES (3, 'usluge vazdušnog prevoza putnika i robe (osim prevoza pošte)', false, 2);
INSERT INTO xnab_predmet_nabavke VALUES (4, 'kopneni i vazdušni prevoz pošte (osim usluga železničkog saobraćaja)', false, 2);
INSERT INTO xnab_predmet_nabavke VALUES (5, 'elektronske komunikacione usluge', false, 2);
INSERT INTO xnab_predmet_nabavke VALUES (6, 'finansijske usluge', false, 2);
INSERT INTO xnab_predmet_nabavke VALUES (7, 'računarske i druge vezane usluge', false, 2);
INSERT INTO xnab_predmet_nabavke VALUES (8, 'usluge istraživanja i razvoja', false, 2);
INSERT INTO xnab_predmet_nabavke VALUES (9, 'usluge računovodstva, revizije i vođenja knjiga', false, 2);
INSERT INTO xnab_predmet_nabavke VALUES (10, 'usluge u oblasti istraživanja tržišta i javnog mnjenja', false, 2);
INSERT INTO xnab_predmet_nabavke VALUES (11, 'usluge menadžmentskog konsaltinga i druge vezane usluge (osim usluga arbitraže, poravnanja i srodnih usluga)', false, 2);
INSERT INTO xnab_predmet_nabavke VALUES (12, 'arhitektonske usluge; inženjerske usluge; usluge urbanističkog planiranja i pejzažne arhitekture;', false, 2);
INSERT INTO xnab_predmet_nabavke VALUES (13, 'reklamne usluge', false, 2);
INSERT INTO xnab_predmet_nabavke VALUES (14, 'usluge čišćenja zgrada i usluge upravljanja imovinom', false, 2);


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

--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

--
-- Data for Name: xnab_jrn; Type: TABLE DATA; Schema: public; Owner: milos
--

COPY xnab_jrn (id_jrn, id_jrn_nadredjena, opis, kod, f_dopunski_recnik, f_dozvoljen_izbor) FROM stdin;
1	\N	Програмски пакети и информациони системи\r\n\r\n	48000000	f	t
2	1	Програмски пакети за индустрију	48100000	f	t
4	3	Програмски пакети за надзор и управљање ваздушним саобраћајем\r\n\r\n	48121000	f	t
6	5	48131000 - Програмски пакет за земаљску подршку авијацији\r\n\r\n	48131000	f	t
7	5	48132000 - Испитни авијацијски програмски пакет\r\n\r\n	48132000	f	t
3	2	Програмски пакети за контролу лета\r\n\r\n	48120000	f	t
5	2	Програмски пакет за земаљску подршку авијацији и испитни авијацијски програмс...\r\n\r\n	48130000	f	t
8	2	48140000 - Програмски пакет за надзор и управљање железничким саобраћајем\r\n\r\n	48140000	f	t
9	2	48150000 - Контролни програмски пакет у индустрији\r\n\r\n	48150000	f	t
11	2	48160000 - Програмски пакет за библиотеке\r\n\r\n	48160000	f	t
12	11	48161000 - Систем за управљање библиотекама\r\n\r\n	48161000	f	t
\.


--
-- PostgreSQL database dump complete
--

ALTER TABLE nab_partija_nabavke ADD COLUMN procenjena_vrednost_bez_pdv numeric(15,2) NOT NULL;
ALTER TABLE nab_partija_nabavke ADD COLUMN procenjena_vrednost_sa_pdv numeric(15,2) NOT NULL;
ALTER TABLE nab_partija_nabavke ADD COLUMN napomena character varying(1024);

CREATE TABLE nab_nabavka_konto_partija
(
   id_nabavka_konto_partija integer, 
   id_nabavka integer, 
   id_konto integer, 
   id_partija_nabavke integer, 
   id_izvor_finansiranja integer, 
   vrednost_bez_ppdv numeric(15,2), 
   vrednost_sa_pdv numeric(15,2), 
   napomena character varying(1024), 
    PRIMARY KEY (id_nabavka_konto_partija), 
    FOREIGN KEY (id_nabavka) REFERENCES nab_javna_nabavka (id_javna_nabavka) ON UPDATE NO ACTION ON DELETE NO ACTION, 
    FOREIGN KEY (id_konto) REFERENCES xnab_konto (id_konto) ON UPDATE NO ACTION ON DELETE NO ACTION, 
    FOREIGN KEY (id_partija_nabavke) REFERENCES nab_partija_nabavke (id_partija_nabavke) ON UPDATE NO ACTION ON DELETE NO ACTION, 
    FOREIGN KEY (id_izvor_finansiranja) REFERENCES xnab_izvor_finansiranja (id_izvor_finansiranja) ON UPDATE NO ACTION ON DELETE NO ACTION
) 
WITH (
  OIDS = FALSE
)
;
ALTER TABLE nab_nabavka_konto_partija OWNER TO "fimes-demo";
COMMENT ON TABLE nab_nabavka_konto_partija
  IS 'planirana vrednost po kontima u finansijskom planu';

ALTER TABLE nab_nabavka_konto_partija RENAME id_nabavka  TO id_javna_nabavka;

ALTER TABLE nab_nabavka_konto_partija RENAME vrednost_bez_ppdv  TO vrednost_bez_pdv;

CREATE SEQUENCE seq_nab_nabavka_konto_partija
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_nab_nabavka_konto_partija
  OWNER TO "fimes-demo";


ALTER TABLE nab_nabavka_konto_partija
   ALTER COLUMN vrednost_bez_pdv SET NOT NULL;
ALTER TABLE nab_nabavka_konto_partija
   ALTER COLUMN vrednost_sa_pdv SET NOT NULL;


CREATE TABLE nab_nabavka_jrn
(
   id_nabavka_jrn integer NOT NULL, 
   id_javna_nabavka integer NOT NULL, 
   id_jrn integer NOT NULL, 
    PRIMARY KEY (id_nabavka_jrn), 
    FOREIGN KEY (id_javna_nabavka) REFERENCES nab_javna_nabavka (id_javna_nabavka) ON UPDATE NO ACTION ON DELETE NO ACTION, 
    FOREIGN KEY (id_jrn) REFERENCES xnab_jrn (id_jrn) ON UPDATE NO ACTION ON DELETE NO ACTION
) 
WITH (
  OIDS = FALSE
)
;
ALTER TABLE nab_nabavka_jrn OWNER TO "fimes-demo";
COMMENT ON TABLE nab_nabavka_jrn
  IS 'veza izmedju  nabavke i šifranika iz opšteg rečnika nabavki';

CREATE SEQUENCE seq_nab_nabavka_jrn
   INCREMENT 1
   START 1
   MINVALUE 1;
ALTER TABLE seq_nab_nabavka_jrn OWNER TO "fimes-demo";


CREATE TABLE xnab_kriterijum
(
   id_kriterijum integer, 
   naziv character varying(128) NOT NULL, 
   f_arhivirano boolean NOT NULL DEFAULT false, 
    PRIMARY KEY (id_kriterijum)
) 
WITH (
  OIDS = FALSE
)
;
ALTER TABLE xnab_kriterijum OWNER TO "fimes-demo";

-- Table: nab_ugovor

-- DROP TABLE nab_ugovor;

CREATE TABLE nab_ugovor
(
  id_ugovor integer NOT NULL,
  id_javna_nabavka integer NOT NULL,
  interni_broj character varying(128),
  datum_zakljucenja date NOT NULL,
  id_poslovni_partner integer,
  broj_ponuda integer,
  id_kriterijum integer,
  jedinicna_cena numeric(15,2),
  troskovi_pripreme numeric(15,2),
  ugovorena_vrednost_bez_pdv numeric(15,2),
  ugovorena_vrednost_sa_pdv numeric(15,2),
  CONSTRAINT nab_ugovor_pkey PRIMARY KEY (id_ugovor ),
  CONSTRAINT nab_ugovor_id_javna_nabavka_fkey FOREIGN KEY (id_javna_nabavka)
      REFERENCES nab_javna_nabavka (id_javna_nabavka) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT nab_ugovor_id_kriterijum_fkey FOREIGN KEY (id_kriterijum)
      REFERENCES xnab_kriterijum (id_kriterijum) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT nab_ugovor_id_poslovni_partner_fkey FOREIGN KEY (id_poslovni_partner)
      REFERENCES pp_poslovni_partner (id_poslovni_partner) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT nab_ugovor_id_poslovni_partner_fkey1 FOREIGN KEY (id_poslovni_partner)
      REFERENCES pp_poslovni_partner (id_poslovni_partner) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE nab_ugovor
  OWNER TO "fimes-demo";

ALTER TABLE nab_ugovor
   ADD COLUMN f_ugovor_izvrsen boolean NOT NULL DEFAULT false;


ALTER TABLE nab_plan
   ALTER COLUMN datum_usvajanja SET DEFAULT NULL;
ALTER TABLE nab_plan
   ALTER COLUMN datum_usvajanja DROP NOT NULL;

ALTER TABLE nab_javna_nabavka
   ALTER COLUMN godina_pokretanja SET NOT NULL;
ALTER TABLE nab_javna_nabavka
   ALTER COLUMN mesec_pokretanja SET NOT NULL;




ALTER TABLE nab_javna_nabavka ADD COLUMN rbr integer;