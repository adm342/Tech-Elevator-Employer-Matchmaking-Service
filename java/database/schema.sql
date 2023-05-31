BEGIN TRANSACTION;

DROP TABLE IF EXISTS interview, rating, pathway_company, matchmaking_session, matchmaking, employer_availability,
    employer, company, student, users, te_program, pathway;

CREATE TABLE interview (
    interview_id SERIAL,
    matchmaking_id int NOT NULL,
    mm_session_id int,
    student_id int NOT NULL,
    employer_id int NOT NULL,
    slot int NOT NULL,
    location varchar (64),

    CONSTRAINT PK_interview PRIMARY KEY (interview_id)
);

CREATE TABLE rating (
    rating_id SERIAL,
    matchmaking_id int NOT NULL,
    student_id int NOT NULL,
    company_id int NOT NULL,
    rating int,

    CONSTRAINT PK_rating PRIMARY KEY (rating_id)
);

CREATE TABLE pathway_company (
    pathway_company_id SERIAL,
    pathway_id int NOT NULL,
    company_id int NOT NULL,
    company_team int,
    matchmaking_id int,
    mm_session_id int,

    CONSTRAINT PK_pathway_company PRIMARY KEY (pathway_company_id)
);

CREATE TABLE matchmaking_session (
    mm_session_id SERIAL,
    matchmaking_id int NOT NULL,
    mm_session_date date NOT NULL,
    mm_session_number int NOT NULL,

    CONSTRAINT PK_matchmaking_session PRIMARY KEY (mm_session_id)
);

CREATE TABLE matchmaking (
	matchmaking_id SERIAL,
	pathway_id int NOT NULL,
	num_of_sessions int NOT NULL,
	start_time time NOT NULL,
	num_of_slots int NOT NULL,
	slot_length int NOT NULL,
	slot_break_length int,
	extend_break_slot int,
	extend_break_length int,
    rating_method varchar (250) NOT NULL,

    CONSTRAINT PK_matchmaking PRIMARY KEY (matchmaking_id)
);

CREATE TABLE employer_availability (
    employer_availability_id SERIAL,
    employer_id int NOT NULL,
    date_available date NOT NULL,
    start_time time NOT NULL,
    end_time time NOT NULL,

    CONSTRAINT PK_employer_availability PRIMARY KEY (employer_availability_id)
);

CREATE TABLE employer (
    employer_id SERIAL,
    user_id int,
    first_name varchar (64) NOT NULL,
    last_name varchar (64) NOT NULL,
    company_id int NOT NULL,
    company_team int NOT NULL,
    title varchar (64) NOT NULL,
    email varchar (96) NOT NULL UNIQUE,
    linkedin_url varchar (96),
    is_point_person boolean NOT NULL,

    CONSTRAINT PK_employer PRIMARY KEY (employer_id)
);

CREATE TABLE company (
	company_id SERIAL,
	name varchar (64) NOT NULL,
	website_url varchar (96),
	description varchar (256),
	position_url varchar (96),
	youtube_url varchar (96),
	image_url varchar (96),
    num_of_teams int,

	CONSTRAINT PK_company PRIMARY KEY (company_id)
);

CREATE TABLE student (
    student_id SERIAL,
    user_id int NOT NULL,
    first_name varchar (64) NOT NULL,
    last_name varchar (64) NOT NULL,
    email varchar (96) NOT NULL UNIQUE,
    linkedin_url varchar (96),
    resume_url varchar (96),
    te_program_id int NOT NULL,
    pathway_id int NOT NULL,

    CONSTRAINT PK_student PRIMARY KEY (student_id)
);

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar (50) NOT NULL,

	CONSTRAINT PK_users PRIMARY KEY (user_id)
);

CREATE TABLE te_program (
    te_program_id SERIAL,
    program varchar (64) NOT NULL,

    CONSTRAINT PK_te_program PRIMARY KEY (te_program_id)
);

CREATE TABLE pathway (
	pathway_id SERIAL,
	campus varchar (64) NOT NULL,

	CONSTRAINT PK_pathway PRIMARY KEY (pathway_id)
);

ALTER TABLE interview
    ADD CONSTRAINT FK_interview_matchmaking FOREIGN KEY (matchmaking_id) REFERENCES matchmaking (matchmaking_id),
    ADD CONSTRAINT FK_interview_matchmaking_session FOREIGN KEY (mm_session_id) REFERENCES matchmaking_session (mm_session_id),
    ADD CONSTRAINT FK_interview_student FOREIGN KEY (student_id) REFERENCES student (student_id),
    ADD CONSTRAINT FK_interview_employer FOREIGN KEY (employer_id) REFERENCES employer (employer_id);

ALTER TABLE rating
    ADD CONSTRAINT FK_rating_matchmaking FOREIGN KEY (matchmaking_id) REFERENCES matchmaking (matchmaking_id),
    ADD CONSTRAINT FK_rating_student FOREIGN KEY (student_id) REFERENCES student (student_id),
    ADD CONSTRAINT FK_rating_company FOREIGN KEY (company_id) REFERENCES company (company_id);

ALTER TABLE pathway_company
    ADD CONSTRAINT FK_pathway_company_pathway FOREIGN KEY (pathway_id) REFERENCES pathway (pathway_id),
    ADD CONSTRAINT FK_pathway_company_company FOREIGN KEY (company_id) REFERENCES company (company_id),
    ADD CONSTRAINT FK_pathway_company_matchmaking FOREIGN KEY (matchmaking_id) REFERENCES matchmaking (matchmaking_id),
    ADD CONSTRAINT FK_pathway_company_matchmaking_session FOREIGN KEY (mm_session_id) REFERENCES matchmaking_session (mm_session_id);

ALTER TABLE matchmaking_session
    ADD CONSTRAINT FK_matchmaking_session_matchmaking FOREIGN KEY (matchmaking_id) REFERENCES matchmaking (matchmaking_id);

ALTER TABLE matchmaking
    ADD CONSTRAINT FK_matchmaking_pathway FOREIGN KEY (pathway_id) REFERENCES pathway (pathway_id);

ALTER TABLE employer_availability
    ADD CONSTRAINT FK_employer_availability_employer FOREIGN KEY (employer_id) REFERENCES employer (employer_id);

ALTER TABLE employer
    ADD CONSTRAINT FK_employer_company FOREIGN KEY (company_id) REFERENCES company (company_id);

ALTER TABLE student
    ADD CONSTRAINT FK_student_users FOREIGN KEY (user_id) REFERENCES users (user_id),
    ADD CONSTRAINT FK_student_te_program FOREIGN KEY (te_program_id) REFERENCES te_program (te_program_id),
    ADD CONSTRAINT FK_student_pathway FOREIGN KEY (pathway_id) REFERENCES pathway (pathway_id);


COMMIT TRANSACTION;
