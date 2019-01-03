package root.project.ejb3.local.client;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.NamingException;

import root.project.ejb3.server.api.IRemoteQuiz;
import root.project.ejb3.server.api.IRemotePlayedQuizzesCounter;
import root.project.jndi.lookup.ejb3.LookerUp;

@ManagedBean(name = "quizManagedBean")
@SessionScoped
public class QuizManagedBean {
	
	private String playerName;
	private int score = 0;
	private String question = "";
	private int answer;
	
	// Not DI'ing it
	private IRemoteQuiz quizProxy;
	
	// Both of the following JNDO names are possible
	//@EJB(mappedName="ejb:/ejb3-server-war//PlayedQuizzesCounterBean!root.project.ejb3.server.api.IRemotePlayedQuizzesCounter")
	@EJB(mappedName="java:global/ear-1.0/ejb3-server-war/PlayedQuizzesCounterBean!root.project.ejb3.server.api.IRemotePlayedQuizzesCounter")
	private IRemotePlayedQuizzesCounter playedQuizzesCounterProxy;
	
	@PostConstruct
	public void setup() {
		System.out.println("Setting up after creating the JSF managed bean.");
	}
	
	@PreDestroy
	public void cleanUp() {
		System.out.println("Cleaning up before destroying the JSF managed bean.");
		quizProxy.end();
	}
	
	public String start() throws NamingException {
		
		playedQuizzesCounterProxy.increment();
		
		if(quizProxy != null) {
			quizProxy.end();
			quizProxy = null;
			System.out.println("Refreshing Quizz!");
		}
		
		//--- EJB Lookup in Same EAR (Such configuration could be externalized in a file for example)
		String ejbsServerAddress = "127.0.0.1";
		int ebjsServerPort = 8080;
		String earName = "ear-1.0";
		String moduleName = "ejb3-server-war";
		String deploymentDistinctName = "";
		String beanName = "QuizBean";
		String interfaceQualifiedName = IRemoteQuiz.class.getName();
		
		// No password required <= Component deployed in the same container
		LookerUp wildf9Lookerup = new LookerUp(ejbsServerAddress, ebjsServerPort);
		
		// We could instead use the following method by giving the exact JNDI name :
		// quizProxy = (IRemoteQuiz) wildf9Lookerup.findSessionBean("ejb:ear-1.0/ejb3-server-war//QuizBean!root.project.ejb3.server.api.IRemoteQuiz?stateful");
		quizProxy = (IRemoteQuiz) wildf9Lookerup.findRemoteSessionBean(LookerUp.SessionBeanType.STATEFUL, earName, moduleName, deploymentDistinctName, beanName, interfaceQualifiedName);
		
		quizProxy.begin(playerName);
		setQuestion(quizProxy.generateQuestionAndAnswer());
		
		return "quiz.xhtml";
	}
	
	public String verifyAnswer() {
		
		if(quizProxy == null) {
			System.out.println("quizProxy is null");
			return "index.xhtml";
		}
		
		boolean correct = quizProxy.verifyAnswerAndReward(answer);
		
		setScore(quizProxy.getScore());
		
		if(!correct) {
			System.out.println("Failed Quizz!");
			quizProxy.end();
			quizProxy = null;
			return "end.xhtml";
		} else {
			setQuestion(quizProxy.generateQuestionAndAnswer());
			return "quiz.xhtml";
		}
	}
	
	public long getPlayedQuizzesNumber() {
		return playedQuizzesCounterProxy.getNumber();
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

}
