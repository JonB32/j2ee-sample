package root.project.ejb3.server.api;

import root.project.ejb3.server.api.entities.LevelQuestion;

public interface ILocalQuizQuestionGenerator {
	LevelQuestion generateQuestion(int level);
}
