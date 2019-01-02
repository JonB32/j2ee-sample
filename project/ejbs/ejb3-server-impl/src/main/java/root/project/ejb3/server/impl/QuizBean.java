package root.project.ejb3.server.impl;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import root.project.ejb3.server.api.ILocalQuiz;
import root.project.ejb3.server.api.ILocalQuizQuestionGenerator;
import root.project.ejb3.server.api.IQuiz;
import root.project.ejb3.server.api.IRemoteQuiz;
import root.project.ejb3.server.api.entities.LevelQuestion;

@Stateful
@Remote(IRemoteQuiz.class)
@Local(ILocalQuiz.class)
public class QuizBean implements IQuiz {
	
	@EJB
	public ILocalQuizQuestionGenerator levelQuestionGenerator;
	
	private String playerName;
	private String askedQuestion;
	private long expectedAnswer;
	private int score = 0;
	private int level = 1;

	@Override
	public void begin(String userName) {
		this.playerName = userName;
	}

	@Override
	public String generateQuestionAndAnswer() {
		LevelQuestion levelQuestion = levelQuestionGenerator.generateQuestion(level);
		askedQuestion = levelQuestion.getQuestion();
		expectedAnswer = levelQuestion.getExpectedAnswer();
		return askedQuestion;
	}

	@Override
	public boolean verifyAnswerAndReward(int result) {
		if(expectedAnswer == result) {
			score++;
			level++;
			return true;
		}
		return false;
	}

	@Remove
	@Override
	public void end() {
		System.out.println("QuizBean instance will be removed...");
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

}
