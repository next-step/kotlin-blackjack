package blackjack

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class DealerTest : FunSpec({
    test("오픈한 카드를 반환한다") {
        val cards = Cards(
            Card.of(Denomination.ACE, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
        )
        val dealer = Dealer(cards)

        dealer.openedCards() shouldBe Cards(Card.of(Denomination.ACE, Suit.SPADES))
    }

    context("Hit 을 해야하는지 반환한다") {
        data class ShouldHit(val cards: Cards, val expected: Boolean)

        withData(
            ShouldHit(
                Cards(
                    Card.of(Denomination.ACE, Suit.SPADES),
                    Card.of(Denomination.FIVE, Suit.SPADES),
                ),
                true
            ),
            ShouldHit(
                Cards(
                    Card.of(Denomination.ACE, Suit.SPADES),
                    Card.of(Denomination.SIX, Suit.SPADES),
                ),
                false
            ),
        ) { (cards, expected) ->
            val dealer = Dealer(cards)
            dealer.shouldHit() shouldBe expected
        }
    }
})
