package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
import java.math.BigDecimal

class RunningTest : FunSpec({

    test("hit 다음 Running 을 반환한다") {
        val cards = Cards(
            Card.of(Denomination.TWO, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
        )
        val running = Running(cards)
        val card = Card.of(Denomination.SEVEN, Suit.CLUBS)

        val state = running.hit(card)

        state should instanceOf<Running>()
        state.cards shouldBe cards + card
    }

    test("hit 다음 BlackJack 을 반환한다") {
        val cards = Cards(
            Card.of(Denomination.TWO, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
        )
        val running = Running(cards)
        val card = Card.of(Denomination.NINE, Suit.CLUBS)

        val state = running.hit(card)

        state should instanceOf<BlackJack>()
        state.cards shouldBe cards + card
    }

    test("hit 다음 Burst 를 반환한다") {
        val cards = Cards(
            Card.of(Denomination.TWO, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
        )
        val running = Running(cards)
        val card = Card.of(Denomination.QUEEN, Suit.CLUBS)

        val state = running.hit(card)

        state should instanceOf<Burst>()
        state.cards shouldBe cards + card
    }

    test("stay 상태를 반환한다") {
        val cards = Cards(
            Card.of(Denomination.TWO, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
        )
        val running = Running(cards)

        val state = running.stay()

        state should instanceOf<Stay>()
        state.cards shouldBe cards
    }

    test("Running 상태에서 수익을 계산하면 예외가 발생한다") {
        val exception = shouldThrow<IllegalStateException> {
            val cards = Cards(
                Card.of(Denomination.TWO, Suit.SPADES),
                Card.of(Denomination.JACK, Suit.SPADES),
            )
            val running = Running(cards)
            running.calculateProfit(BigDecimal(1000))
        }

        exception.message shouldBe "끝난 상태에서만 수익을 계산할 수 있습니다."
    }
})
