DELETE FROM t_projects;
DELETE FROM t_languages_technologies;

INSERT INTO t_projects(project_name, project_unique_internal_identifier , project_start_date , project_description) VALUES ('Amazing project', 'PRO-123', '2024-8-27'::Date, 'Amazing project by dream team');

INSERT INTO t_languages_technologies(lang_tech_name) VALUES ('JAVA');
