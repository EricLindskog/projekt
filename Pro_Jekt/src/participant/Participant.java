package participant;

import die.Die;

public abstract class Participant {
	private int score;

	int getScore() {
		return score;
	}

	void setScore(int score) {
		this.score = score;
	}
	public abstract Die[] DicesToReroll(Die[] dice);
}
