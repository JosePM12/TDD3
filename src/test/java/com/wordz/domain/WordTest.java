package com.wordz.domain;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static com.wordz.domain.Letter.*;
import static org.assertj.core.api.Assertions.assertThat;

public class WordTest {
    @Test
    public void oneCorrectLetter() {
        var word = new Word("A");
        var score = word.guess("A");
        assertScoreForLetter(score, 0, Letter.CORRECT);

    }
    @Test
    void secondLetterWrongPosition() {
        var word = new Word("AR");
        var score = word.guess("ZA");
        assertScoreForLetter(score, 1,
                Letter.PART_CORRECT);
    }
    @Test
    public void allScoreCombinations() {
        var word = new Word("ARI");
        var score = word.guess("ZAI");
        assertScoreForGuess(score,  INCORRECT,
                PART_CORRECT,
                CORRECT);
    }
    private void assertScoreForLetter(Score score,
                                      int position, Letter expected) {
        assertThat(score.letter(position))
                .isEqualTo(expected);
    }

    private void assertScoreForGuess(Score score,
                                     Letter... expectedScores) {
        for (int position = 0;
             position < expectedScores.length;
             position++) {
            Letter expected =
                    expectedScores[position];
            assertThat(score.letter(position))
                    .isEqualTo(expected);
        }
    }
}

