package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.math.BigDecimal

class FinishedTest : FunSpec({

    test("끝난 상태에서 hit 을 하면 예외가 발생한다") {
        val cards = Cards(
            Card.of(Denomination.TWO, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
        )

        val exception = shouldThrow<IllegalStateException> {
            val stay = Stay(cards)

            stay.hit(Card.of(Denomination.THREE, Suit.HEARTS))
        }

        exception.message shouldBe "끝난 상태에서 hit 할 수 없습니다."
    }

    test("끝난 상태에서 stay 을 하면 예외가 발생한다") {
        val cards = Cards(
            Card.of(Denomination.TWO, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
        )

        val exception = shouldThrow<IllegalStateException> {
            val stay = Stay(cards)

            stay.stay()
        }

        exception.message shouldBe "끝난 상태에서 stay 할 수 없습니다."
    }

    test("수익을 반환한다") {
        val cards = Cards(
            Card.of(Denomination.TWO, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
        )
        val stay = Stay(cards)

        stay.calculateProfit(BigDecimal(1000)) shouldBe Profit(BigDecimal(1000))
    }
})
