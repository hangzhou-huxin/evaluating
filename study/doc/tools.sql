ALTER TABLE escape_question_options ADD CONSTRAINT fk_escape_question_options_question_id
	FOREIGN KEY(question_id) REFERENCES escape_questions(id) ON DELETE CASCADE;