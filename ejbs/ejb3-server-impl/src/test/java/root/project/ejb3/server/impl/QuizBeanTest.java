package root.project.ejb3.server.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import org.junit.Before;
import org.junit.Test;

import root.project.ejb3.server.api.ILocalQuizQuestionGenerator;
import root.project.ejb3.server.api.entities.LevelQuestion;

public class QuizBeanTest {
	String mockQuestion;
	int mockAnswer;
	QuizBean quizProxy;

    @Before
    public void setUp() {
    	quizProxy = new QuizBean();
    	quizProxy.levelQuestionGenerator = mock(ILocalQuizQuestionGenerator.class);
		mockQuestion = "2 + 4 = ?";
		mockAnswer = 6;
		
    	LevelQuestion levelQuestion = new LevelQuestion();
    	levelQuestion.setQuestion(mockQuestion);
    	levelQuestion.setExpectedAnswer(mockAnswer);
		
		when(quizProxy.levelQuestionGenerator.generateQuestion(1)).thenReturn(levelQuestion);
    }
    
	@Test
	public void testPlayerName() {
		String expectedName = "Jon";
		quizProxy.begin(expectedName);
		assertEquals(quizProxy.getPlayerName(), expectedName);
	}
	
	@Test
	public void testGenerateQuestionAndAnswer() {
		String actualQuestion = quizProxy.generateQuestionAndAnswer();
		
		assertEquals(actualQuestion, mockQuestion);
	}
	
	@Test
	public void testVerifyAnswerAndReward() {
		quizProxy.generateQuestionAndAnswer();
		
		assertEquals(quizProxy.verifyAnswerAndReward(6), true);
	}
	
	@Test
	public void testScore() {
		quizProxy.generateQuestionAndAnswer();
		quizProxy.verifyAnswerAndReward(6);
		
		assertEquals(quizProxy.getScore(), 1);
	}
	
	@Test
	public void testComponentGenerateQuestionAndVerifySuccess() {
		String actualQuestion = quizProxy.generateQuestionAndAnswer();
		
		assertEquals(actualQuestion, mockQuestion);
		
		assertEquals(quizProxy.verifyAnswerAndReward(6), true);
		
		assertEquals(quizProxy.getScore(), 1);
	}
}
