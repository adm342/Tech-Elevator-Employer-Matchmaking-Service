BEGIN TRANSACTION;

INSERT INTO te_program (program)
    VALUES ('NLR'), ('Cincinnati'), ('Cleveland');

INSERT INTO pathway (campus)
    VALUES ('NLR'), ('Cincinnati'), ('Cleveland');

INSERT INTO users (username,password_hash,role)
    VALUES ('admin','$2a$10$6OywcqvFAXmMe0te1vKSl.01Ste6AJGi4kMhFSDN.RgG.wgw110Q6','ROLE_ADMIN'),
        ('george','$2a$10$6OywcqvFAXmMe0te1vKSl.01Ste6AJGi4kMhFSDN.RgG.wgw110Q6','ROLE_USER,ROLE_STUDENT'),
        ('company','$2a$10$6OywcqvFAXmMe0te1vKSl.01Ste6AJGi4kMhFSDN.RgG.wgw110Q6','ROLE_USER,ROLE_EMPLOYER'),
        ('jerry','$2a$10$6OywcqvFAXmMe0te1vKSl.01Ste6AJGi4kMhFSDN.RgG.wgw110Q6','ROLE_USER,ROLE_STUDENT'),
        ('elaine','$2a$10$6OywcqvFAXmMe0te1vKSl.01Ste6AJGi4kMhFSDN.RgG.wgw110Q6','ROLE_USER,ROLE_STUDENT'),
        ('cosmo','$2a$10$6OywcqvFAXmMe0te1vKSl.01Ste6AJGi4kMhFSDN.RgG.wgw110Q6','ROLE_USER,ROLE_STUDENT'),
        ('francis','$2a$10$6OywcqvFAXmMe0te1vKSl.01Ste6AJGi4kMhFSDN.RgG.wgw110Q6','ROLE_USER,ROLE_STUDENT');

INSERT INTO student (user_id, first_name, last_name, email, linkedin_url, resume_url, te_program_id, pathway_id)
    VALUES (2, 'george', 'costanza', 'george@george.george', 'george.com/george', 'george.com/resume', 1, 1),
        (4, 'jerry', 'seinfeld', 'jerry@jerry.jerry', 'jerry.com/jerry', 'jerry.com/resume', 1, 1),
        (5, 'elaine', 'benes', 'elaine@elaine.elaine', 'elaine.com/elaine', 'elaine.com/resume', 1, 1),
        (6, 'cosmo', 'kramer', 'cosmo@cosmo.cosmo', 'cosmo.com/cosmo', 'cosmo.com/resume', 1, 1),
        (7, 'francis', 'newman', 'francis@francis.francis', 'francis.com/francis', 'francis.com/resume', 1, 1);

INSERT INTO company (name, website_url, description, position_url, youtube_url, image_url, num_of_teams)
    VALUES ('Vandelay Industries', 'VandelayInd.com', 'Here at Vandelay no soup for you', 'VandelayJobs.com', 'Youtube/Nosoup.com', 'here would be a picture of soup but no soup for you', 2),
        ('Pendant Publishing', 'test', 'test', 'test', 'test', 'test', 1),
        ('Kramerica Industries', 'test', 'test', 'test', 'test', 'test', 1),
        ('Top of the Muffin to You', 'test', 'test', 'test', 'test', 'test', 1),
        ('J. Peterman Catalog', 'test', 'test', 'test', 'test', 'test', 1);

INSERT INTO employer (user_id, first_name, last_name, company_id, company_team, title, email,
    linkedin_url, is_point_person)
    VALUES (3, 'susan', 'ross', 1, 1, 'Recruiter', 'susanross@email.com', 'linked', true);

 INSERT INTO employer (first_name, last_name, company_id, company_team, title, email,
     linkedin_url, is_point_person)
     VALUES ('george', 'steinbrenner', 2, 1, 'Recruiter', 'georgesteinbrenner@email.com', 'linked', true),
        ('jacopo', 'peterman', 3, 1, 'Recruiter', 'jacopopeterman@email.com', 'linked', true),
        ('david', 'puddy', 4, 1, 'Recruiter', 'davidpuddy@email.com', 'linked', true),
        ('tim', 'whatley', 5, 1, 'Recruiter', 'timwhatley@email.com', 'linked', true),
        ('lloyd', 'braun', 1, 2, 'Recruiter', '@email.com', 'linked', false);

INSERT INTO employer_availability (employer_id, date_available, start_time, end_time)
    VALUES (1, '2023-05-05', '09:00:00', '17:00:00');

INSERT INTO matchmaking (pathway_id, num_of_sessions, start_time, num_of_slots, slot_length, slot_break_length,
    extend_break_slot, extend_break_length, rating_method)
    VALUES (1, 2, '09:00:00', 8, 25, 5, 4, 40, 'Rank Top 4');

INSERT INTO matchmaking_session (matchmaking_id, mm_session_date, mm_session_number)
    VALUES (1, '2023-05-05', 1),
        (1, '2023-05-10', 2);

INSERT INTO pathway_company (pathway_id, company_id, company_team, matchmaking_id, mm_session_id)
    VALUES (1, 1, 1, 1, 1),
        (1, 2, 1, 1, 2),
        (1, 3, 1, 1, 2),
        (1, 4, 1, 1, 2),
        (1, 5, 1, 1, 2),
        (1, 1, 2, 1, 2);

INSERT INTO interview (matchmaking_id, mm_session_id, student_id, employer_id, slot, location)
    VALUES (1, 1, 1, 1, 1, 'zoom.com/...'),
        (1, 1, 2, 1, 2, 'zoom.com/...'),
        (1, 1, 3, 1, 3, 'zoom.com/...'),
        (1, 1, 4, 1, 4, 'zoom.com/...'),
        (1, 1, 5, 1, 5, 'zoom.com/...'),
        (1, 2, 1, 2, 1, 'zoom.com/...'),
        (1, 2, 2, 3, 1, 'zoom.com/...'),
        (1, 2, 3, 4, 1, 'zoom.com/...'),
        (1, 2, 4, 5, 1, 'zoom.com/...'),
        (1, 2, 5, 6, 1, 'zoom.com/...'),
        (1, 1, 1, 3, 4, 'zoom.com/...'),
        (1, 2, 1, 4, 3, 'zoom.com/...');

COMMIT TRANSACTION;
