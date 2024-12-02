package blackjack

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class DealerTest : StringSpec({
    "딜러는 생성 시에 카드 2장이 필수다" {
        listOf(
            listOf(
                Card(CardNumber.King, Suit.SPADES),
            ),
            listOf(
                Card(CardNumber.Jack, Suit.SPADES),
                Card(CardNumber.Queen, Suit.SPADES),
                Card(CardNumber.King, Suit.SPADES),
            ),
        ).forEach { initialCards ->
            shouldThrow<IllegalArgumentException> {
                Dealer(initialCards = initialCards)
            }
        }
    }
})
