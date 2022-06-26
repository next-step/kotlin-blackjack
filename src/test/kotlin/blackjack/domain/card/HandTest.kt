package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class HandTest {
    @Test
    fun `카드를 추가한다`() {
        val hand = Hand()
        hand.add(Card.of(ACE, Suit.SPADE))
        assertAll({
            assertThat(hand.value2).hasSize(1)
            assertThat(hand.value2[0].suit).isEqualTo(Suit.SPADE)
            assertThat(hand.value2[0].rank).isEqualTo(ACE)
        })
    }

    @ParameterizedTest
    @MethodSource("calculateCardSource")
    fun `카드의 합을 계산한다`(cards: List<Card>, score: Int) {
        val hand = Hand()
        cards.forEach { hand.add(it) }

        assertThat(hand.getScore()).isEqualTo(score)
    }

    companion object {
        @JvmStatic
        private fun calculateCardSource(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(listOf(Card.of(JACK, Suit.SPADE), Card.of(ACE, Suit.SPADE)), 21),
                Arguments.of(listOf(Card.of(JACK, Suit.SPADE), Card.of(ACE, Suit.SPADE), Card.of(QUEEN, Suit.SPADE)), 21),
                Arguments.of(listOf(Card.of(SEVEN, Suit.SPADE), Card.of(SIX, Suit.SPADE), Card.of(EIGHT, Suit.SPADE)), 21),
                Arguments.of(listOf(Card.of(SEVEN, Suit.SPADE), Card.of(SIX, Suit.SPADE), Card.of(TEN, Suit.SPADE)), 23),
                Arguments.of(listOf(Card.of(ACE, Suit.SPADE), Card.of(ACE, Suit.HEART), Card.of(ACE, Suit.CLUB)), 13),
            )
        }
    }
}
