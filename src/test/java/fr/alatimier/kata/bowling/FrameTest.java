package fr.alatimier.kata.bowling;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FrameTest {

    @Test
    void a_frame_with_10_pin_down_at_first_roll_should_be_a_strike() {
        // Given
        Frame frame = new Frame(10, 0);

        // When
        boolean strike = frame.isStrike();
        boolean spare = frame.isSpare();

        // Then
        Assertions.assertTrue(strike);
        Assertions.assertFalse(spare);
    }

    @Test
    void a_frame_with_10_pin_down_at_after_second_roll_should_be_a_spare() {
        // Given
        Frame frame = new Frame(8, 2);

        // When
        boolean strike = frame.isStrike();
        boolean spare = frame.isSpare();

        // Then
        Assertions.assertFalse(strike);
        Assertions.assertTrue(spare);
    }

    @Test
    void a_frame_with_less_than_10_pin_down_after_second_roll_should_not_be_a_strike_nor_spare() {
        // Given
        Frame frame = new Frame(8, 1);

        // When
        boolean strike = frame.isStrike();
        boolean spare = frame.isSpare();

        // Then
        Assertions.assertFalse(strike);
        Assertions.assertFalse(spare);
    }

}