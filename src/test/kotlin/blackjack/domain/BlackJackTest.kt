package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class BlackJackTest : FunSpec({

    test("BlackJack 상태의 수익률을 반환한다") {
        val cards = Cards(
            Card.of(Denomination.ACE, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
        )
        BlackJack(cards).profitRate() shouldBe 1.5.toBigDecimal()
    }
})
