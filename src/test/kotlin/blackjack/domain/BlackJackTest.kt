package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class BlackJackTest : FunSpec({

    test("카드 2장이면서 BlackJack 상태일 때 수익 배수를 반환한다") {
        val cards = Cards(
            Card.of(Denomination.ACE, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
        )
        BlackJack(cards).profitMultiple() shouldBe 1.5.toBigDecimal()
    }

    test("카드 3장 이상이면서 BlackJack 상태일 때 수익 배수를 반환한다") {
        val cards = Cards(
            Card.of(Denomination.EIGHT, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
            Card.of(Denomination.THREE, Suit.SPADES),
        )
        BlackJack(cards).profitMultiple() shouldBe 1.toBigDecimal()
    }
})
