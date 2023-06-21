package blackjack

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CardsTest : FunSpec({

    test("카드를 더한다.") {
        val cards = Cards(Card(Denomination.ACE, Suit.SPADES))
        val card = Card(Denomination.JACK, Suit.SPADES)

        cards + card shouldBe Cards(
            Card(Denomination.ACE, Suit.SPADES),
            Card(Denomination.JACK, Suit.SPADES),
        )
    }
})
