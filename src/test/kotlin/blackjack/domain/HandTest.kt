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
    fun `Ace카드는 11로 계산될 수 있다`() {
        val hand = Hand()
        hand.addCard(Card(Shape.CLUB, Denomination.TEN))
        hand.addCard(Card(Shape.CLUB, Denomination.ACE))

        hand.score().value shouldBe 21
    }
}
