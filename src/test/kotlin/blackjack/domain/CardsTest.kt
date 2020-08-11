package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class CardsTest {

    @Test
    fun `카드들의 총점 계산`() {
        // given
        val cards = Cards.denominationsOf("7", "10")

        // then
        assertThat(cards.sumScores()).isEqualTo(17)
    }

    @MethodSource("cardsWithAceAndTotalScore")
    @ParameterizedTest
    fun `ACE 를 포함한 총점계산`(cards: Cards, expectedScore: Int) {
        // then
        assertThat(cards.sumScores()).isEqualTo(expectedScore)
    }

    companion object {
        @JvmStatic
        fun cardsWithAceAndTotalScore(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(Cards.denominationsOf("A", "A", "A", "A", "9"), 13),
                Arguments.of(Cards.denominationsOf("A", "A", "A", "A"), 14),
                Arguments.of(Cards.denominationsOf("A", "A", "A", "8"), 21),
                Arguments.of(Cards.denominationsOf("A", "A", "9", "10"), 21),
                Arguments.of(Cards.denominationsOf("A", "10", "10"), 21),
                Arguments.of(Cards.denominationsOf("A", "10"), 21),
                Arguments.of(Cards.denominationsOf("A", "A", "10"), 12),
                Arguments.of(Cards.denominationsOf("A", "A", "10", "10"), 22),
                Arguments.of(Cards.denominationsOf("A", "10", "10", "10"), 31)
            )
        }
    }
}
