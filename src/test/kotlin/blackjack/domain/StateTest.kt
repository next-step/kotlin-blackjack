package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf

class StateTest : FunSpec({

    test("State 생성시 점수가 21 미만이면 Running 상태 생성") {
        val cards = Cards(
            Card.of(Denomination.TWO, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
        )
        State.of(cards) should instanceOf<Running>()
    }

    test("State 생성시 점수가 21 이면 BlackJack 상태 생성") {
        val cards = Cards(
            Card.of(Denomination.ACE, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
        )
        State.of(cards) should instanceOf<BlackJack>()
    }

    test("점수를 반환한다") {
        val cards = Cards(
            Card.of(Denomination.ACE, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
        )
        val state = State.of(cards)

        state.calculateScore() shouldBe Score(21)
    }
})
