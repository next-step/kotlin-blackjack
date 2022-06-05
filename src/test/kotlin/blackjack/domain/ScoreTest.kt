package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ScoreTest {
    @ParameterizedTest
    @CsvSource(
        "1, 4, true",
        "19, 20, true",
        "22, 15, true",
        "24, 31, false"
    )
    fun `player can draw a card if the score is less than 21`(origin: Int, alternative: Int, expected: Boolean) {
        val score = Score(origin, alternative)

        val result = score.couldGetMoreCard()

        assertThat(result).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "1, 4, 4",
        "19, 21, 21",
        "20, 22, 20"
    )
    fun `the highest score between origin and alternative is a score close to 21 and less than 21`(
        origin: Int,
        alternative: Int,
        expected: Int
    ) {
        val score = Score(origin, alternative)

        val result = score.max()

        assertThat(result).isEqualTo(expected)
    }
}
