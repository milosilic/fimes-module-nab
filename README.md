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

