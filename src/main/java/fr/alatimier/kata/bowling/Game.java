package fr.alatimier.kata.bowling;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

class Game {

    private static final int FRAME_NB = 10;

    private List<Frame> frames = new LinkedList<>();

    void playFrame(Frame frame) {
        if (frames.size() == FRAME_NB + 2) {
            throw new IllegalArgumentException("Game is over");
        }
        frames.add(frame);
    }

    int getScore() {
        int score = 0;
        int currentIndex = 0;
        for (Frame frame : frames) {
            if (!isBonusRoll(currentIndex)) {
                score += frame.getNbPinsDown();
                if (frame.isStrike()) {
                    score += getStrikeBonus(currentIndex);
                } else if (frame.isSpare()) {
                    score += getSpareBonus(currentIndex);
                }
            }
            currentIndex++;
        }
        return score;
    }

    private boolean isBonusRoll(int currentIndex) {
        return currentIndex >= FRAME_NB;
    }

    private int getStrikeBonus(int index) {
        Optional<Frame> nextFrame = getFrameAtIndex(index + 1);

        if (!nextFrame.isPresent()) {
            throw new IllegalStateException("Frame sequence is erroneous...");
        }

        if (nextFrame.get().isStrike()) {
            Optional<Frame> nextNextFrame = getFrameAtIndex(index + 2);
            if (!nextNextFrame.isPresent()) {
                throw new IllegalStateException("Frame sequence is erroneous...");
            }
            return nextFrame.get().getNbPinsDown() + nextNextFrame.get().getNbPinsDownAtFirstRoll();
        } else {
            return nextFrame.get().getNbPinsDown();
        }

    }

    private int getSpareBonus(int index) {
        Optional<Frame> nextFrame = getFrameAtIndex(index + 1);

        if (!nextFrame.isPresent()) {
            throw new IllegalStateException("Frame sequence is erroneous...");
        }

        return nextFrame.get().getNbPinsDownAtFirstRoll();
    }

    private Optional<Frame> getFrameAtIndex(int index) {
        if (index + 1 <= frames.size()) {
            return Optional.of(frames.get(index));
        }
        return Optional.empty();
    }

}
