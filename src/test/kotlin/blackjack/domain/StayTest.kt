package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.math.BigDecimal

class StayTest : FunSpec({

    test("Stay 상태의 수익률을 반환한다") {
        val cards = Cards(
            Card.of(Denomination.TWO, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
        )
        Stay(cards).profitRate() shouldBe BigDecimal(1)
    }
})
