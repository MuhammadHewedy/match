-- password 12345678 
INSERT INTO "user" (created_by, created_date, modified_by, modified_date, enabled, password, role, username)
VALUES (-1, NOW(), -1, NOW(), TRUE, '$2a$10$53dXuhNlKSbXJ6cvFls/jeG2gemezsdk85OHfWVEmmEVnGuMG7i5S', 'ROLE_SUPER_ADMIN', 'superadmin')
;
