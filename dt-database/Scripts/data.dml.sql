DELETE FROM t_projects;
DELETE FROM t_languages_technologies;

INSERT INTO t_projects(name, unique_internal_identifier , start_date , description) 
VALUES ('Amazing project', 'PRO-001', '2024-8-27'::Date, 'Amazing project by dream team'),
('Superb project 2', 'PRO-002', '2024-8-27'::Date, 'Superb project by dream team'),
('Magnificent project 3', 'PRO-003', '2024-8-27'::Date, 'Magnificent project by dream team'),
('素晴らしいプロジェクト 4', 'PRO-004', '2024-8-27'::Date, 'ドリームティームに造られた素晴らしいプロジェクト');

INSERT INTO t_languages_technologies(name) 
VALUES ('JAVA'), ('JAVASCRIPT'), ('Kotlin'), ('Swift');

INSERT INTO t_use(project_id, lang_tech_id) 
VALUES (1, 1), (1, 2), (1, 3), (2, 1), (4, 3), (4, 4);