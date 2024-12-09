package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({
    "Dealer should draw an extra card if total value is 16 or below" {
        val dealer = Dealer()
        dealer.addCard(Card(Rank.THREE, Suit.HEART))
        dealer.addCard(Card(Rank.FIVE, Suit.SPADE))

        dealer.playTurn(Deck())
        dealer.getTotalValue() shouldBeGreaterThan 16
    }

    "Dealer should not draw if total value is 17 or above" {
        val dealer = Dealer()
        dealer.addCard(Card(Rank.NINE, Suit.CLUB))
        dealer.addCard(Card(Rank.EIGHT, Suit.DIAMOND))

        dealer.playTurn(Deck())
        dealer.getTotalValue() shouldBe 17
    }
})
