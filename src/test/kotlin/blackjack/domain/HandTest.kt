package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Shape
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class HandTest {
    @Test
    fun `카드합을 계산할 수 있다`() {
        val firstCard = Card(Shape.CLUB, Denomination.SEVEN)
        val secondCard = Card(Shape.CLUB, Denomination.JACK)

        val hand = Hand(firstCard, secondCard)

        hand.score shouldBe 17
    }

    @Test
    fun `Ace카드는 1로 계산된다`() {
        val firstCard = Card(Shape.CLUB, Denomination.TWO)
        val secondCard = Card(Shape.CLUB, Denomination.NINE)
        val thirdCard = Card(Shape.CLUB, Denomination.ACE)

        val hand = Hand(firstCard, secondCard, thirdCard)

        hand.score shouldBe 12
    }

    @Test
    fun `Ace카드는 카드의 합이 10 이하일 경우 11로 계산될 수 있다`() {
        val firstCard = Card(Shape.CLUB, Denomination.TEN)
        val secondCard = Card(Shape.CLUB, Denomination.ACE)

        val hand = Hand(firstCard, secondCard)

        hand.score shouldBe 21
    }

    @ParameterizedTest
    @MethodSource("aceProvider")
    fun `Ace카드가 여러장일 경우 장당 1로 계산하지만 11 이하일때만 10을 더한다`(cards: List<Card>, expected: Int) {
        val hand = Hand(cards)
        hand.score shouldBe expected
    }

    companion object {
        @JvmStatic
        fun aceProvider(): List<Arguments> {
            return listOf(
                Arguments.of(listOf(Card(Shape.CLUB, Denomination.ACE)), 11),
                Arguments.of(listOf(Card(Shape.CLUB, Denomination.ACE), Card(Shape.CLUB, Denomination.ACE)), 12),
                Arguments.of(
                    listOf(
                        Card(Shape.CLUB, Denomination.ACE),
                        Card(Shape.CLUB, Denomination.ACE),
                        Card(Shape.CLUB, Denomination.ACE)
                    ),
                    13
                ),
                Arguments.of(
                    listOf(
                        Card(Shape.CLUB, Denomination.ACE),
                        Card(Shape.CLUB, Denomination.ACE),
                        Card(Shape.CLUB, Denomination.ACE),
                        Card(Shape.CLUB, Denomination.ACE)
                    ),
                    14
                ),
            )
        }
    }
}
