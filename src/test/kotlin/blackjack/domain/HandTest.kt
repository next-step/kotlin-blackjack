package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class HandTest {
    @Test
    fun `카드합을 계산할 수 있다`() {
        val hand = Hand()
        hand.addCard(Card(Shape.CLUB, Denomination.SEVEN))
        hand.addCard(Card(Shape.CLUB, Denomination.JACK))

        hand.score().value shouldBe 17
    }

    @Test
    fun `기존 카드의 합계가 11 이상이면 Ace카드는 1로 계산된다`() {
        val hand = Hand()
        hand.addCard(Card(Shape.CLUB, Denomination.TWO))
        hand.addCard(Card(Shape.CLUB, Denomination.NINE))
        hand.addCard(Card(Shape.CLUB, Denomination.ACE))

        hand.score().value shouldBe 12
    }

    @Test
    fun `Ace카드는 11로 계산될 수 있다`() {
        val hand = Hand()
        hand.addCard(Card(Shape.CLUB, Denomination.TEN))
        hand.addCard(Card(Shape.CLUB, Denomination.ACE))

        hand.score().value shouldBe 21
    }
}
