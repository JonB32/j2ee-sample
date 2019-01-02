package root.project.ejb3.server.impl;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Singleton;

import root.project.ejb3.server.api.IPlayedQuizzesCounter;
import root.project.ejb3.server.api.IRemotePlayedQuizzesCounter;

@Singleton
@Remote(IRemotePlayedQuizzesCounter.class)
@LocalBean
public class PlayedQuizzesCounterBean implements IPlayedQuizzesCounter {

	long playedQuizzesNumber = 0;
	
	@Override
	public void increment() {
		playedQuizzesNumber++;
	}

	@Override
	public long getNumber() {
		return playedQuizzesNumber;
	}

}
