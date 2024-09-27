DELETE FROM t_projects;
DELETE FROM t_languages_technologies;

INSERT INTO t_projects(name, unique_internal_identifier , start_date , description) VALUES ('Amazing project', 'PRO-123', '2024-8-27'::Date, 'Amazing project by dream team');

INSERT INTO t_languages_technologies(name) VALUES ('JAVA');
