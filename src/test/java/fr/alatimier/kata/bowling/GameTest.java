package fr.alatimier.kata.bowling;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

class GameTest {

    private Game game;

    @BeforeEach
    void init() {
        game = new Game();
    }

    @Test
    void the_score_of_a_game_without_strike_nor_spare_should_be_a_simple_sum() {
        // Given
        playManyFrames(10, 1, 1);

        // When
        int score = game.getScore();

        // Then
        Assertions.assertEquals(20, score);
    }

    @Test
    void the_score_of_a_strike_should_be_the_number_of_pins_down_plus_the_number_of_pins_down_of_the_two_next_rolls() {
        // Given
        playStrike();
        playManyFrames(9, 1, 1);

        // When
        int score = game.getScore();

        // Then
        Assertions.assertEquals(30, score);
    }

    @Test
    void the_score_of_a_spare_should_be_the_number_of_pins_down_plus_the_number_of_pins_down_of_the_next_rolls() {
        // Given
        playSpare();
        playManyFrames(9, 1, 1);

        // When
        int score = game.getScore();

        // Then
        Assertions.assertEquals(29, score);
    }

    @Test
    void a_spare_on_frames_ten_should_grant_one_bonus_roll() {
        // Given
        playManyFrames(9, 1, 1);
        playSpare();
        playFrame(2, 0);

        // When
        int score = game.getScore();

        // Then
        Assertions.assertEquals(30, score);
    }

    @Test
    void a_strike_on_frames_ten_should_grant_two_bonus_rolls() {
        // Given
        playManyFrames(9, 1, 1);
        playStrike();
        playFrame(1, 1);

        // When
        int score = game.getScore();

        // Then
        Assertions.assertEquals(30, score);
    }

    @Test
    void a_strike_in_bonus_rolls_should_not_be_handled_as_strike() {
        // Given
        playManyFrames(9, 1, 1);
        playStrike();
        playStrike();
        playStrike();

        // When
        int score = game.getScore();

        // Then
        Assertions.assertEquals(48, score);
    }

    @Test
    void a_spare_in_bonus_rolls_should_not_be_handled_as_spare() {
        // Given
        playManyFrames(9, 1, 1);
        playStrike();
        playSpare();

        // When
        int score = game.getScore();

        // Then
        Assertions.assertEquals(38, score);
    }

    @Test
    void highest_score_should_be_300() {
        // Given
        playManyFrames(12, 10, 0);

        // When
        int score = game.getScore();

        // Then
        Assertions.assertEquals(300, score);
    }

    private void playStrike() {
        playFrame(10, 0);
    }

    private void playSpare() {
        playFrame(5, 5);
    }

    private void playManyFrames(int nbFrames, int firstRoll, int secondRoll) {
        IntStream.rangeClosed(1, nbFrames).forEach(i -> playFrame(firstRoll, secondRoll));
    }

    private void playFrame(int firstRoll, int secondRoll) {
        game.playFrame(new Frame(firstRoll, secondRoll));
    }

}