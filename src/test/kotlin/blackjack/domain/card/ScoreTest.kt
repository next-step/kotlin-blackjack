package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ScoreTest {

    @MethodSource("cardsWithAceAndTotalScore")
    @ParameterizedTest
    fun `ACE 를 포함한 총점계산`(cards: List<Card>, expectedScore: Int) {
        // then
        assertThat(Score.from(cards)).isEqualTo(expectedScore)
    }

    companion object {
        private fun cardsOf(vararg denominations: String): List<Card> {
            return denominations.map { Card.spadeOf(it) }
        }

        @JvmStatic
        fun cardsWithAceAndTotalScore(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(cardsOf("A", "A", "A", "A", "9"), 13),
                Arguments.of(cardsOf("A", "A", "A", "A"), 14),
                Arguments.of(cardsOf("A", "A", "A", "8"), 21),
                Arguments.of(cardsOf("A", "A", "9", "10"), 21),
                Arguments.of(cardsOf("A", "10", "10"), 21),
                Arguments.of(cardsOf("A", "10"), 21),
                Arguments.of(cardsOf("A", "A", "10"), 12),
                Arguments.of(cardsOf("A", "A", "10", "10"), 22),
                Arguments.of(cardsOf("A", "10", "10", "10"), 31)
            )
        }
    }
}
