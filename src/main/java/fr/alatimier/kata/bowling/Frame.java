package fr.alatimier.kata.bowling;

class Frame {

    private static final int TOTAL_PIN = 10;

    private int nbPinsDownAtFirstRoll;
    private int nbPinsDownAtSecondRoll;

    Frame(int nbPinsDownAtFirstRoll, int nbPinsDownAtSecondRoll) {
        this.nbPinsDownAtFirstRoll = nbPinsDownAtFirstRoll;
        this.nbPinsDownAtSecondRoll = nbPinsDownAtSecondRoll;
    }

    boolean isStrike() {
        return nbPinsDownAtFirstRoll == TOTAL_PIN;
    }

    boolean isSpare() {
        return !isStrike() && getNbPinsDown() == TOTAL_PIN;
    }

    int getNbPinsDown() {
        return nbPinsDownAtFirstRoll + nbPinsDownAtSecondRoll;
    }

    int getNbPinsDownAtFirstRoll() {
        return nbPinsDownAtFirstRoll;
    }

}
