package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Shape
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

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
}
