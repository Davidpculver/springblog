# USE springblog_db;
#
# INSERT INTO posts (body, title) VALUES ('does this work number 1', 'post 1'), ('does this work number 2', 'post 2'), ('does this work number 3', 'post 3'), ('does this work number 4', 'post 4');




USE springblog_db;

TRUNCATE springblog_db.users;
TRUNCATE springblog_db.ads;
TRUNCATE springblog_db.posts;


INSERT INTO springblog_db.users (username, email, password)
VALUES ('admin', 'admin@email.com', 'password');


INSERT INTO springblog_db.ads (description, title, user_id)
VALUES ('Lorem ipsum dolor sit amet, ex pericula persequeris eam, quo quem saperet cu. Ne epicurei periculis nec. Nostrud fastidii eu vim.',
        '3 Easy Ways To Make LOREM IPSUM Faster', 1),
       ('Lorem ipsum dolor sit amet, ex pericula persequeris eam, quo quem saperet cu. Ne epicurei periculis nec. Nostrud fastidii eu vim.',
        'Get Rid of LOREM IPSUM For Good', 1),
       ('Lorem ipsum dolor sit amet, ex pericula persequeris eam, quo quem saperet cu. Ne epicurei periculis nec. Nostrud fastidii eu vim.',
        '10 Best Practices For LOREM IPSUM', 1),
       ('Lorem ipsum dolor sit amet, ex pericula persequeris eam, quo quem saperet cu. Ne epicurei periculis nec. Nostrud fastidii eu vim.',
        'Use LOREM IPSUM To Make Someone Fall In Love With You', 1),
       ('Lorem ipsum dolor sit amet, ex pericula persequeris eam, quo quem saperet cu. Ne epicurei periculis nec. Nostrud fastidii eu vim.',
        'It''s All About LOREM IPSUM', 1);

INSERT INTO springblog_db.posts (body, title)
VALUES ('Lorem ipsum dolor sit amet, ex pericula persequeris eam, quo quem saperet cu. Ne epicurei periculis nec. Nostrud fastidii eu vim.',
        '3 Easy Ways To Make LOREM IPSUM Faster'),
       ('Lorem ipsum dolor sit amet, ex pericula persequeris eam, quo quem saperet cu. Ne epicurei periculis nec. Nostrud fastidii eu vim.',
        'Get Rid of LOREM IPSUM For Good'),
       ('Lorem ipsum dolor sit amet, ex pericula persequeris eam, quo quem saperet cu. Ne epicurei periculis nec. Nostrud fastidii eu vim.',
        '10 Best Practices For LOREM IPSUM'),
       ('Lorem ipsum dolor sit amet, ex pericula persequeris eam, quo quem saperet cu. Ne epicurei periculis nec. Nostrud fastidii eu vim.',
        'Use LOREM IPSUM To Make Someone Fall In Love With You'),
       ('Lorem ipsum dolor sit amet, ex pericula persequeris eam, quo quem saperet cu. Ne epicurei periculis nec. Nostrud fastidii eu vim.',
        'It''s All About LOREM IPSUM');

INSERT INTO springblog_db.categories (name) VALUES ('lorem'), ('ipsum'), ('dolor'), ('sit'), ('amet');

INSERT INTO springblog_db.ads_categories (ad_id, category_id) VALUES (1,1), (1,2), (2,2), (3,3), (4,4), (5,5);




UPDATE posts SET user_id = 1 WHERE id = 1;
UPDATE posts SET user_id = 1 WHERE id = 2;
UPDATE posts SET user_id = 1 WHERE id = 3;
UPDATE posts SET user_id = 1 WHERE id = 4;
UPDATE posts SET user_id = 1 WHERE id = 5;