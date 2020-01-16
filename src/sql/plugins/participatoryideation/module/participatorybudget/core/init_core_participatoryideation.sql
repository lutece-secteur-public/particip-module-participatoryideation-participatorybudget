INSERT INTO workflow_state VALUES (150, 'ExportedToBudget', 'Exported as project in participatorybudget plugin', 100, 0, 0, NULL, 10);

INSERT INTO workflow_action VALUES (150, 'ExportToBudget', 'Export proposal to participatorybudget plugin as new project.', 100, 101, 150, 1, 0, 0, 10, 0);
INSERT INTO workflow_action VALUES (151, 'Reinit', 'Return to draft state. ', 100, 150, 100, 3, 0, 0, 1, 0);
