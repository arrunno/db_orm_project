-- DB ORM project SQLs

CREATE TABLE IF NOT EXISTS public.exam_answers
(
    id bigint NOT NULL,
    examid character varying(255) COLLATE pg_catalog."default",
    isanswercorrect boolean NOT NULL,
    questionnumber integer,
    useranswer integer,
    examtakeinfoid bigint,
    CONSTRAINT exam_answers_pkey PRIMARY KEY (id),
    CONSTRAINT fk1lmxfocbfc9odfjbtafd60f5s FOREIGN KEY (examtakeinfoid)
        REFERENCES public.exam_takes_info (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

	
CREATE TABLE IF NOT EXISTS public.exam_takes_info
(
    id bigint NOT NULL,
    examid character varying(255) COLLATE pg_catalog."default",
    examinfo character varying(255) COLLATE pg_catalog."default",
    examtakedate date,
    numberofcorrectanswers integer NOT NULL,
    totalexamquestions integer NOT NULL,
    useremail character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT exam_takes_info_pkey PRIMARY KEY (id),
    CONSTRAINT ukfumgtsmdy9o64kewngb7382vu UNIQUE (examid, useremail, examtakedate)
)


CREATE TABLE IF NOT EXISTS public.question_answers
(
    id bigint NOT NULL,
    answernumber integer,
    examid character varying(255) COLLATE pg_catalog."default",
    isanswercorrect boolean NOT NULL,
    questionanswer character varying(255) COLLATE pg_catalog."default",
    questionnumber integer,
    CONSTRAINT question_answers_pkey PRIMARY KEY (id)
)

CREATE TABLE IF NOT EXISTS public.questions
(
    id bigint NOT NULL,
    examid character varying(255) COLLATE pg_catalog."default",
    question character varying(255) COLLATE pg_catalog."default",
    questionnumber integer,
    CONSTRAINT questions_pkey PRIMARY KEY (id),
    CONSTRAINT uk9q9ttgpgkgngrk9wuvc1988dy UNIQUE (examid, questionnumber)
)



	