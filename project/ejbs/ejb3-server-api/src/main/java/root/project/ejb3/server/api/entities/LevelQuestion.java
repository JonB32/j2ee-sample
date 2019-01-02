package root.project.ejb3.server.api.entities;

public class LevelQuestion {
	
	private String question;
	private long expectedAnswer;
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public long getExpectedAnswer() {
		return expectedAnswer;
	}
	public void setExpectedAnswer(long expectedAnswer) {
		this.expectedAnswer = expectedAnswer;
	}
}
