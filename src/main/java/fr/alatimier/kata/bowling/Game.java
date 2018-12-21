package fr.alatimier.kata.bowling;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

class Game {

    private static final int FRAME_NB = 10;

    private List<Frame> frames = new LinkedList<>();

    void playFrame(Frame frame) {
        frames.add(frame);
    }

    int getScore() {
        return IntStream
                .range(0, FRAME_NB)
                .reduce(0, (score, index) -> score + getFrameAtIndex(index).getNbPinsDown() + getFrameBonus(index));
    }

    private int getFrameBonus(int frameIndex) {
        Frame frame = getFrameAtIndex(frameIndex);
        if (frame.isStrike()) {
            return getStrikeBonus(frameIndex);
        } else if (frame.isSpare()) {
            return getSpareBonus(frameIndex);
        }
        return 0;
    }

    private int getStrikeBonus(int index) {
        Frame nextFrame = getFrameAtIndex(index + 1);
        if (nextFrame.isStrike()) {
            return nextFrame.getNbPinsDown() + getFrameAtIndex(index + 2).getNbPinsDownAtFirstRoll();
        } else {
            return nextFrame.getNbPinsDown();
        }
    }

    private int getSpareBonus(int index) {
        return getFrameAtIndex(index + 1).getNbPinsDownAtFirstRoll();
    }

    private Frame getFrameAtIndex(int index) {
        if (index + 1 <= frames.size()) {
            return frames.get(index);
        }
        throw new IllegalStateException("Frame " + index + " is missing...");
    }

}
