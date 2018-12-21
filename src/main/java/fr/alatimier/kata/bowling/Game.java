package fr.alatimier.kata.bowling;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

class Game {

    private List<Frame> frames = new LinkedList<>();

    void playFrame(Frame frame) {
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
        return currentIndex >= 10;
    }

    private int getStrikeBonus(int index) {
        Optional<Frame> nextFrame = getFrameAtIndex(index + 1);
        Optional<Frame> nextNextFrame = getFrameAtIndex(index + 2);

        if (!nextFrame.isPresent()) {
            throw new IllegalStateException("Frame sequence is erroneous...");
        }

        if (nextFrame.get().isStrike()) {
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
