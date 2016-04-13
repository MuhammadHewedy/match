-- password 12345678 
INSERT INTO user (`created_by`, `created_date`, `modified_by`, `modified_date`, `enabled`, `password`, `role`, `username`) 
  SELECT -1, NOW(), -1, NOW(), TRUE, '$2a$10$53dXuhNlKSbXJ6cvFls/jeG2gemezsdk85OHfWVEmmEVnGuMG7i5S', 'ROLE_SUPER_ADMIN', 'superadmin' FROM DUAL
WHERE NOT EXISTS 
  (SELECT id FROM user WHERE username='superadmin');
