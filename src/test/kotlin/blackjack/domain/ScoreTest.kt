package blackjack.domain

import blackjack.domain.Denomination.ACE
import blackjack.domain.Denomination.JACK
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class ScoreTest {

    @ParameterizedTest
    @MethodSource("cardsArguments")
    fun `점수를 계산할 수 있다`(cards: List<Card>, points: Int) {
        val score = Score(cards)

        assertThat(score.value).isEqualTo(points)
    }

    @Test
    fun `20점은 Bust가 아니다`() {
        val score = Score(createCards(JACK, JACK))

        assertThat(score.isBust()).isFalse
    }

    @Test
    fun `21점은 Blackjack이다`() {
        val score = Score(createCards(JACK, JACK, ACE))

        assertThat(score.isBlackjack()).isTrue
    }

    @Test
    fun `22점은 Bust이다`() {
        val score = Score(createCards(JACK, JACK, ACE, ACE))

        assertThat(score.isBust()).isTrue
    }

    companion object {
        @JvmStatic
        fun cardsArguments(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(listOf<Card>(), 0),
                Arguments.of(createCards(ACE), 11),
                Arguments.of(createCards(JACK, Denomination.QUEEN), 20),
                Arguments.of(createCards(JACK, ACE), 21),
                Arguments.of(createCards(JACK, Denomination.QUEEN, ACE), 21),
                Arguments.of(createCards(ACE, JACK, Denomination.QUEEN), 21),
                Arguments.of(createCards(ACE, ACE), 12),
                Arguments.of(createCards(ACE, ACE, ACE), 13),
                Arguments.of(createCards(ACE, ACE, ACE, ACE), 14),
                Arguments.of(createCards(Denomination.SIX, Denomination.FIVE, ACE), 12),
                Arguments.of(createCards(Denomination.FIVE, Denomination.FIVE, ACE), 21),
                Arguments.of(createCards(Denomination.FOUR, Denomination.FIVE, ACE), 20),
                Arguments.of(createCards(JACK, JACK, JACK), 30),
            )
        }

        fun createCards(vararg denominations: Denomination): List<Card> {
            return denominations.map { Card(Suit.DIAMOND, it) }
        }
    }
}
