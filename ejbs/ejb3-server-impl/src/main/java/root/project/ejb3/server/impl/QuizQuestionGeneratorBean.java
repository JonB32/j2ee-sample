package root.project.ejb3.server.impl;

import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.Local;
import javax.ejb.Stateless;

import root.project.ejb3.server.api.ILocalQuizQuestionGenerator;
import root.project.ejb3.server.api.entities.LevelQuestion;

@Stateless
@Local
public class QuizQuestionGeneratorBean implements ILocalQuizQuestionGenerator {

	@Override
	public LevelQuestion generateQuestion(int level) {
		long range = (long) Math.pow(10, level);
		long a = ThreadLocalRandom.current().nextLong(range / 10, range + 1);
		long b = ThreadLocalRandom.current().nextLong(range / 10, range + 1);
		LevelQuestion levelQuestion = new LevelQuestion();
		levelQuestion.setQuestion(a + " + " + b + " = ?");
		levelQuestion.setExpectedAnswer(a + b);
		return levelQuestion;
	}
	
	

}
