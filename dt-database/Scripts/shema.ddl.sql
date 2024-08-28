DROP TABLE IF EXISTS t_use;
DROP TABLE IF EXISTS t_languages_technologies;
DROP TABLE IF EXISTS t_projects;

CREATE TABLE t_projects(
  	project_id INT GENERATED ALWAYS AS IDENTITY,
   	project_name VARCHAR(200) NOT NULL,
   	project_unique_internal_identifier VARCHAR(100) NOT NULL,
   	project_start_date DATE NOT NULL,
   	project_description VARCHAR(5000),
   	CONSTRAINT t_projects_ukey UNIQUE(project_unique_internal_identifier),
   	CONSTRAINT t_projects_pkey PRIMARY KEY(project_id)
);

CREATE TABLE t_languages_technologies(
    lang_tech_id INT GENERATED ALWAYS AS IDENTITY,
   	lang_tech_name VARCHAR(50) NOT NULL,
   	CONSTRAINT t_languages_technologies_ukey UNIQUE(lang_tech_name),
   	CONSTRAINT t_languages_technologies_pkey PRIMARY KEY(lang_tech_id)
);

CREATE TABLE t_use(
    --use_id INT GENERATED ALWAYS AS IDENTITY,
    project_id INT,
    lang_tech_id INT,
    --CONSTRAINT t_use_pkey PRIMARY KEY(use_id),
    CONSTRAINT t_use_pkey PRIMARY KEY(project_id, lang_tech_id),
	CONSTRAINT t_use_ukey UNIQUE(project_id, lang_tech_id),
	CONSTRAINT t_use_porject_id_fkey FOREIGN KEY(project_id) REFERENCES t_projects(project_id),
	CONSTRAINT t_use_lang_tech_id_fkey FOREIGN KEY(lang_tech_id) REFERENCES t_languages_technologies(lang_tech_id)
);