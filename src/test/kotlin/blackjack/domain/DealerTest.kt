package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러는 16점 이하이면 카드를 받아야한다`() {
        val dealer = Dealer()
        val cards = Cards(
            mutableListOf(
                Card(Denomination.TWO, Suit.CLUBS),
                Card(Denomination.JACK, Suit.HEARTS),
            )
        )

        for (card in cards.cards) {
            dealer.hit(card)
        }

        dealer.canHit shouldBe true
    }

    @Test
    fun `딜러는 17점 이상이면 카드를 받을 수 없다`() {
        val dealer = Dealer()
        val cards = Cards(
            mutableListOf(
                Card(Denomination.JACK, Suit.CLUBS),
                Card(Denomination.JACK, Suit.HEARTS),
            )
        )

        for (card in cards.cards) {
            dealer.hit(card)
        }

        dealer.canHit shouldBe false
    }
}
